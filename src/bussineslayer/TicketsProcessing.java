package bussineslayer;

import domainmodel.*;

import java.util.ArrayList;

import dataaccesslayer.*;

public class TicketsProcessing {
	TicketsDAO td = new TicketsDAO();
	ArrayList<Ticket> tickets;
	
	public Object[][] getTickets(String event)
	{
		tickets = td.getTickets();
		Object[][] data = new Object[nrTickets(event)][];
		
		int i=0;
		for(Ticket temp:tickets)
		{
			if (temp.getEvent().equals(event))
			{
				data[i++] = new Object[]{temp.getEvent(), temp.getRow(), temp.getNr()};
			}
		}
		
		return data;
		
	}
	
	public int addTicket(Ticket t)
	{
		int succed = 0;
		EventsProcessing ep = new EventsProcessing();
		
		ep.refreshEvents();
		
		Event e = ep.getEvent(t.getEvent());
		
		if (e.getTicketsNr()<1)
		{
			succed = 1;
		} else
		{
			for(Ticket temp: tickets)
			{
				if ((temp.getRow() == t.getRow()) && (temp.getNr() == t.getNr()))
					succed = 2;
			}
		}
		if (succed == 0) 
		{
			td.addTicket(t);
			tickets = td.getTickets();
		}
		return succed;
	}
	
	private int nrTickets(String event)
	{
		int nr = 0;
		
		for (Ticket temp:tickets)
		{
			if (temp.getEvent().equals(event))
			{
				nr++;
			}
		}
		
		return nr;
	}
}

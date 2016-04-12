package bussineslayer;

import java.util.ArrayList;

import domainmodel.*;
import dataaccesslayer.*;

public class EventsProcessing {
	EventsDAO ed = new EventsDAO();
	ArrayList<Event> events;
	
	public EventsProcessing()
	{
		
	}
	
	public Object[][] readEvents()
	{
		events = ed.readEvents();
			
		Object[][] data = new Object[events.size()][];
		int i = 0;
		for (Event temp: events)
		{
			data[i++] = new Object[]{temp.getTitle(), temp.getDirectedBy(), temp.getCast(), temp.getPremiereDate().toString(), temp.getTicketsNr()}; 
		}
		return data;
	}
	
	public void addEvent(Event e)
	{
		ed.addEvent(e);
	}
	
	public void updateEvent(Event e)
	{
		ed.updateEvent(e);
	}
	
	public void deleteEvent(Event e)
	{
		ed.deleteEvent(e);
	}
	
	public String[] getEventsList()
	{
		events = ed.readEvents();
		
		String[] list = new String[events.size()+1];
		list[0] = "--none--";
		int i = 1;
		for (Event temp: events)
		{
			list[i++] = temp.getTitle();
		}
		
		return list;
	}
	
	public String[] getEventsList2()
	{
		events = ed.readEvents();
		
		String[] list = new String[events.size()];
		int i =0;
		for (Event temp: events)
		{
			list[i++] = temp.getTitle();
		}
		
		return list;
	}
	
	public Event getEvent(String title)
	{
		Event e = null;
		for (Event temp: events)
		{
			if (temp.getTitle().equals(title)) e = temp;
		}
		return e;
	}
	
	public void refreshEvents()
	{
		events = ed.readEvents();
	}
}

package domainmodel;

public class Ticket {
	private String event;
	private int row;
	private int nr;
	
	public Ticket(String event, int row, int nr)
	{
		this.event = event;
		this.row = row;
		this.nr = nr;
	}
	
	public String getEvent()
	{
		return event;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getNr()
	{
		return nr;
	}
}
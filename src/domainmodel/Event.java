package domainmodel;

public class Event {
	private String title;
	private String directedBy;
	private String cast;
	private String premiereDate;
	private int ticketsNr;
	
	public Event(String title, String dirBy, String cast, String premDate, int tNr)
	{
		this.title = title;
		this.directedBy = dirBy;
		this.cast = cast;
		this.premiereDate = premDate;
		this.ticketsNr = tNr;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getDirectedBy()
	{
		return directedBy;
	}
	
	public String getCast()
	{
		return cast;
	}
	
	public String getPremiereDate()
	{
		return premiereDate;
	}
	
	public int getTicketsNr()
	{
		return ticketsNr;
	}
	
	public String toString()
	{
		return title + " directedBy: " + directedBy + " cast: " + cast 
				+ " premiere: " + premiereDate + " tickets: " + ticketsNr;
	}
}

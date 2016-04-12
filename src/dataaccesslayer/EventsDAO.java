package dataaccesslayer;

import java.sql.*;
import java.util.ArrayList;
import domainmodel.*;

public class EventsDAO {
	static final String DB_URL = "jdbc:mysql://localhost/teatru?useSSL=false";
	static final String USER = "root";
	static final String PASS = "catalin";
	
	
	public EventsDAO()
	{
		
	}
	
	public ArrayList<Event> readEvents()
	{
		Connection conn = null;
		
		ArrayList<Event> events = new ArrayList<Event>();
		try {

		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    
		    String sql = "SELECT * FROM Events;";
		    
		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    while (result.next()){
		        String title = result.getString("title");
		        String directedBy = result.getString("directedBy");
		        String cast = result.getString("cast");
		        String pDate = result.getString("premiereDate");
		        int nrTickets = result.getInt("ticketsNr");
		        
		        events.add(new Event(title,directedBy,cast, pDate, nrTickets));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}
	
	public void addEvent(Event e)
	{
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "INSERT INTO Events VALUES ('"+ e.getTitle() + "','" + e.getDirectedBy() + "','"
			+ e.getCast() + "','" + e.getPremiereDate() +"','" + e.getTicketsNr() + "');";
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
	
	public void updateEvent(Event e)
	{
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "UPDATE Events SET directedBy='" + e.getDirectedBy() + "', cast='" + e.getCast() + "', premiereDate='" + e.getPremiereDate()
					+ "', ticketsNr='" + e.getTicketsNr() + "' WHERE Title='" + e.getTitle() + "';";
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
	
	public void deleteEvent(Event e)
	{
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "DELETE FROM Events WHERE Title = '" + e.getTitle() + "';";
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
}

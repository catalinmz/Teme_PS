package dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domainmodel.*;

public class TicketsDAO {
	static final String DB_URL = "jdbc:mysql://localhost/teatru?useSSL=false";
	static final String USER = "root";
	static final String PASS = "catalin";
	
	public TicketsDAO()
	{
	}
	
	public void addTicket(Ticket t)
	{
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "INSERT INTO Tickets (title,row,nr) VALUES ('" + t.getEvent() + 
					"'," + t.getRow() + "," + t.getNr() + ");";
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
			
			sql = "SELECT ticketsNr FROM Events WHERE title='" + t.getEvent() + "';";
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			if (result.next())
			{
				int nr = result.getInt("ticketsNr");
				nr = nr-1;
				sql = "UPDATE Events SET ticketsNr="+ nr +" WHERE title='" + t.getEvent() + "';";
				statement = conn.createStatement();
				statement.executeUpdate(sql);
			}
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
	
	public ArrayList<Ticket> getTickets()
	{
		Connection conn = null;
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		try {

		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    
		    String sql = "SELECT title,row,nr FROM Tickets;";
		    
		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    while (result.next()){
		        String event = result.getString("title");
		        int row = result.getInt("row");
		        int nr = result.getInt("nr");
		        tickets.add(new Ticket(event, row, nr));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tickets;
	}
}

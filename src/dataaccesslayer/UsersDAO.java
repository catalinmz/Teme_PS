package dataaccesslayer;

import java.sql.*;
import domainmodel.*;

public class UsersDAO {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/teatru?useSSL=false";
	
	static final String USER = "root";
	static final String PASS = "catalin";
	
	public User login(String username, String password)
	{
		 Connection conn = null;
		 
		 User u = null;
		 try {
			Class.forName(JDBC_DRIVER);
		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    
		    String sql = "SELECT admin_role, name FROM Users"
		    		+ " WHERE username ='" + username + "' AND password='" + password + "';";
		    
		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    if (result.next()){
		        Boolean admin = result.getBoolean("admin_role");
		        String name = result.getString("name");
		        if (admin)
		        	u = new Administrator(username,password,name);
		        else u = new Employee(username,password,name);
		    }
		    
		    conn.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return u;	 
	}
	
	public void createAccount(User u)
	{
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "INSERT INTO Users VALUES ('"+ u.getUserName() + "','" + u.getPassword() + "',false,'"
			+ ((Employee) u).getName() +  "');";
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
	
	public boolean updateUser(String username, String password)
	{
		boolean succed = true;
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "UPDATE Users SET password='" + password +"' WHERE username='"+username+"';";
			Statement statement = conn.createStatement();
			if (statement.executeUpdate(sql) == 0) succed = false;;
		} catch (SQLException e1) {
			e1.printStackTrace();
			succed = false;
		} 
		
		return succed;
	}
}

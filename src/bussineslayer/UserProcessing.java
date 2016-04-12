package bussineslayer;

import java.math.BigInteger;
import java.security.*;
import domainmodel.*;
import dataaccesslayer.*;

public class UserProcessing {
	
	UsersDAO ud = new UsersDAO();
	
	public UserProcessing()
	{
		
	}
	
	public User login(String username, String password)
	{
		return ud.login(username,MD5Encryption(password));
	}
	
	public void createEmployeeAccount(String name, String username, String password)
	{
		User u = new Employee(username,MD5Encryption(password),name);
		ud.createAccount(u);
	}
	
	public String MD5Encryption(String passwordToHash)
	{
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
       return generatedPassword;
	}

	  public String generatePassword(String username) {
		SecureRandom random = new SecureRandom();
	    String pass = new BigInteger(50, random).toString(32);
	    
	    boolean result = ud.updateUser(username,MD5Encryption(pass));
	    
	    if (result)
	    	return pass;
	    else
	    	return "";
	  }
}

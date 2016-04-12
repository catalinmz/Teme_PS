package domainmodel;

public class Administrator extends User{
	private String name;
	
	public Administrator(String username, String password, String name)
	{
		super(username,password);
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}

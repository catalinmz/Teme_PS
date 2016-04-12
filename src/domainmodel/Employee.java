package domainmodel;

public class Employee extends User{
	private String name;
	
	public Employee(String username, String password, String name)
	{
		super(username,password);
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}

package proj.android.zyl.finance_pro.model;

public class Tb_pwd
{
	private String username;
	private String password;
	
	public Tb_pwd(){
		super();
	}

	public Tb_pwd(String username,String password)
	{
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
		
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
}

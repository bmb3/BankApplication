 package com.dxc.pojos;

public class BankAdmin 
{
	private String admin_id;
	private String password;
	
	public BankAdmin()
	{
		
	}

	public BankAdmin(String admin_id, String password) {
		super();
		admin_id = admin_id;
		password = password;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}

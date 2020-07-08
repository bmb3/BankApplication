package com.dxc.pojos;

public class Usersdetails 
{
  private int Accountno;
  private String Uname;
  private double Accbalance;
  private int userid;
  private String password;
  
  public Usersdetails()
  {
	  
  }

public Usersdetails(int accountno, String uname, double accbalance, int userid, String password) {
	super();
	Accountno = accountno;
	Uname = uname;
	Accbalance = accbalance;
	this.userid = userid;
	this.password = password;
}

public int getAccountno() {
	return Accountno;
}

public void setAccountno(int accountno) {
	Accountno = accountno;
}

public String getUname() {
	return Uname;
}

public void setUname(String uname) {
	Uname = uname;
}

public double getAccbalance() {
	return Accbalance;
}

public void setAccbalance(double accbalance) {
	Accbalance = accbalance;
}

public int getUserid() {
	return userid;
}

public void setUserid(int userid) {
	this.userid = userid;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}


  
}

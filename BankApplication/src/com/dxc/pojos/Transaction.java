package com.dxc.pojos;

public class Transaction
{
 private int Accountno;
 private String transction;
 private double transferbal;
 
 public Transaction()
 {
	 
 }

public Transaction(int accountno, String transction, double transferbal) {
	super();
	Accountno = accountno;
	this.transction = transction;
	this.transferbal = transferbal;
}

public int getAccountno() {
	return Accountno;
}

public void setAccountno(int accountno) {
	Accountno = accountno;
}

public String getTransction() {
	return transction;
}

public void setTransction(String transction) {
	this.transction = transction;
}

public double getTransferbal() {
	return transferbal;
}

public void setTransferbal(double transferbal) {
	this.transferbal = transferbal;
}
 
}

package com.dxc.dao;

import java.util.List;

import com.dxc.pojos.Transaction;

public interface IUserDao 
{
	boolean passwordcheck(int userid, String password);
	
	boolean DepositeAmount(int accountno, double accbalance);

	int getAccNo(int userid, String password);

	boolean WithdrawAmount(int accountno, double accbalance);

	double checkbalance(int accountno);

	List<Transaction> TransactionDetails(int accountno);

	void PasswordChange(int accountno,String password);

	boolean checkpassword(int accno, String password);

	boolean transfer(int accno, int tAccno, double tbal);


}

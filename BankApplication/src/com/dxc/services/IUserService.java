package com.dxc.services;

import java.util.List;

import com.dxc.pojos.Transaction;

public interface IUserService {

	boolean passwordcheck(int userid, String password);

	boolean DepositeAmount(int accountno, double accbalance);

	int getAccNo(int userid, String password);

	boolean WithdrawAmount(int accountno, double accbalance);

	double checkbalance(int accno);

	List<Transaction> TransactionDetails(int accno);

	void PasswordChange(int accountno, String password);

	boolean checkpassword(int accno, String password);

	boolean transfer(int accno, int tAccno, double tbal);


}

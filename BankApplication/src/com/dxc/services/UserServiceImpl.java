package com.dxc.services;

import java.util.List;

import com.dxc.dao.IUserDao;
import com.dxc.dao.UserDaoImpl;
import com.dxc.pojos.Transaction;

public class UserServiceImpl implements IUserService 
{
 IUserDao dao=new UserDaoImpl();
 
	@Override
	public boolean passwordcheck(int userid, String password) {
	
		return dao.passwordcheck(userid,password);
	}

	@Override
	public boolean DepositeAmount(int accountno, double accbalance) {
		
		return dao.DepositeAmount(accountno, accbalance);
		
	}

	@Override
	public int getAccNo(int userid, String password) {
		
		return dao.getAccNo(userid,password);
	}

	@Override
	public boolean WithdrawAmount(int accountno, double accbalance) {
	 return dao.WithdrawAmount(accountno, accbalance);
		
	}

	@Override
	public double checkbalance(int accountno) {
		return dao.checkbalance(accountno);
	}

	@Override
	public List<Transaction> TransactionDetails(int accountno) {
	
		return dao.TransactionDetails(accountno);
	}

	@Override
	public void PasswordChange(int accountno, String password) {
		dao.PasswordChange(accountno,password);
		
	}

	@Override
	public boolean checkpassword(int accno, String password) {
		
		return dao.checkpassword(accno,password);
	}

	@Override
	public boolean transfer(int accno, int tAccno, double tbal) {
		
		return dao.transfer(accno,tAccno,tbal);
	}

	

	

}

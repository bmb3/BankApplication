package com.dxc.services;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.pojos.BankAdmin;
import com.dxc.pojos.Usersdetails;

public class AdminServiceImpl  implements IAdminService{

	IAdminDao dao=new AdminDaoImpl();
	@Override
	public boolean passwordcheck(String admin_id, String password) {
		return dao.passwordcheck(admin_id,password);
	}
	@Override
	public void Addusers(Usersdetails us) {
		dao.Addusers(us);
		
	}
	@Override
	public Usersdetails findusers(int accountno) 
	{
		return dao.findusers(accountno);
	}
	@Override
	public boolean searchUsers(int accountno) {

		return dao.searchUsers(accountno);
	}
	@Override
	public void updateusers(Usersdetails user) {
	
	  dao.updateusers(user);	
	}
	@Override
	public double getbalance(int accountno) {
	
		return dao.getbalance(accountno);
	}
	@Override
	public void removeuser(int accountno) {
		
		dao.removeuser(accountno);
	}
	@Override
	public List<Usersdetails> getAllUsers() {

		return dao.getAllUsers();
	}

}

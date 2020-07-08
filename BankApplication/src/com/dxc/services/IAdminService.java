package com.dxc.services;

import java.util.List;

import com.dxc.pojos.BankAdmin;
import com.dxc.pojos.Usersdetails;

public interface IAdminService
{
	public boolean passwordcheck(String admin_id, String password);

	public void Addusers(Usersdetails us);

	public Usersdetails findusers( int accountno);

	public boolean searchUsers(int accountno);

	public void updateusers(Usersdetails user);

	public double getbalance(int accountno);

	public void removeuser(int accountno);

	public List<Usersdetails> getAllUsers();


}

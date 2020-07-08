package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.BankAdmin;
import com.dxc.pojos.Usersdetails;

public class AdminDaoImpl implements IAdminDao 
{
private static Connection conn;
	
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded...");
			
		 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapplication", "root", "Bmb@1905$");
			System.out.println("connected to database....");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	@Override
	public boolean passwordcheck(String admin_id, String password) {
		try {
			PreparedStatement pstmt=conn.prepareStatement("select admin_id,password from admin where admin_id=? and password=?");
					pstmt.setString(1, admin_id);
			        pstmt.setString(2,password);
			        
			        ResultSet rs=pstmt.executeQuery();
			        if(rs.next()) {
			        	return true;
			        }
			        else
			        {
			        	return false;
			        }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public void Addusers(Usersdetails us) 
	{
		try {
			PreparedStatement pstmt=conn.prepareStatement("insert into usersdetails values(?,?,?,?,?)");
			pstmt.setInt(1, us.getAccountno());
			pstmt.setString(2, us.getUname());
			pstmt.setDouble(3, us.getAccbalance());
			pstmt.setInt(4, us.getUserid());
			pstmt.setString(5, us.getPassword());
			
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public Usersdetails findusers(int accountno) 
	{
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from usersdetails");
			while(rs.next())
			{
				if(accountno==rs.getInt(1))
				{
					return new Usersdetails(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getString(5));
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null ;
	}


	@Override
	public boolean searchUsers(int accountno) 
	{
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from usersdetails");
			while(rs.next())
			{
				if(accountno==rs.getInt(1))
				{
					return true;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public void updateusers(Usersdetails user) 
	{
		try {
			PreparedStatement pstmt=conn.prepareStatement("update usersdetails set Uname=?, Accbalance=?, userid=?, password=? where Accountno=?");
			pstmt.setString(1, user.getUname());
			pstmt.setDouble(2, user.getAccbalance());
			pstmt.setInt(3, user.getUserid());
			pstmt.setString(4, user.getPassword());
			pstmt.setInt(5, user.getAccountno());
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public double getbalance(int accountno) 
	{
		double Accbalance=0;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from usersdetails");
			while(rs.next())
			{
				if(accountno==rs.getInt(1))
				{
					Accbalance=rs.getDouble(3);
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Accbalance;
	}


	@Override
	public void removeuser(int accountno) 
	{
		try {
			PreparedStatement pstmt=conn.prepareStatement("delete from usersdetails where Accountno=?");
			pstmt.setInt(1, accountno);
			pstmt.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public List<Usersdetails> getAllUsers() 
	{
		List<Usersdetails> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from usersdetails");
			while(rs.next())
			{
				list.add(new Usersdetails(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getString(5)));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	
	}

}

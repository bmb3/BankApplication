package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.Transaction;
import com.dxc.pojos.Usersdetails;

public class UserDaoImpl implements IUserDao {
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
		public boolean passwordcheck(int userid, String password) {
			try {
				PreparedStatement pstmt=conn.prepareStatement("select userid,password from usersdetails where userid=? and password=?");
						pstmt.setInt(1, userid);
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
		public boolean DepositeAmount(int accountno, double accbalance) 
		{
			double balance=0;
			try 
			{
				
				PreparedStatement pstmt=conn.prepareStatement("select * from usersdetails where Accountno=?");
				pstmt.setInt(1,accountno);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				
					balance=rs.getDouble(3);
				
					balance=balance+accbalance;
					
				
				pstmt.close();
				PreparedStatement pstmt1=conn.prepareStatement("update usersdetails set Accbalance=? where Accountno=?");
				pstmt1.setDouble(1,balance);
				pstmt1.setInt(2, accountno);
				pstmt1.executeUpdate();
				
				PreparedStatement pstmt2=conn.prepareStatement("insert into transctiondetails values(?,?,?)");
				pstmt2.setInt(1, accountno);
				pstmt2.setString(2, "deposite");
				pstmt2.setDouble(3, accbalance);
				pstmt2.execute();
				return true;

			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return false;
			
		}
		
		@Override
		public boolean WithdrawAmount(int accountno, double accbalance) 
		{
			 if(accbalance>this.checkbalance(accountno))
             {
            	 return false;
             }
			double balance=0;
			try 
			{
				
				PreparedStatement pstmt=conn.prepareStatement("select * from usersdetails where Accountno=?");
				pstmt.setInt(1,accountno);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				
					balance=rs.getDouble(3);
					balance=balance-accbalance;
					
				
				pstmt.close();
				PreparedStatement pstmt1=conn.prepareStatement("update usersdetails set Accbalance=? where Accountno=?");
				pstmt1.setDouble(1,balance);
				pstmt1.setInt(2, accountno);
				pstmt1.executeUpdate();
				
				PreparedStatement pstmt2=conn.prepareStatement("insert into transctiondetails values(?,?,?)");
				pstmt2.setInt(1, accountno);
				pstmt2.setString(2, "withdraw");
				pstmt2.setDouble(3, accbalance);
				pstmt2.execute();
				return true;

			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		
		
		
		@Override
		public double checkbalance(int accno)
		{
			double Accbalance=0;
			Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery("select * from usersdetails");
				while(rs.next())
				{
					if(accno==rs.getInt(1))
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
		public List<Transaction> TransactionDetails(int accno) {
			List<Transaction> list=new ArrayList<>();
			try {
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery("select * from transctiondetails");
				while(rs.next())
				{
					if(accno==rs.getInt(1))
					{
					list.add(new Transaction(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return list;
			
		}
		
		@Override
		public void PasswordChange(int accountno, String password)
		{
		  PreparedStatement pstmt5;
		try {
			pstmt5 = conn.prepareStatement("update usersdetails set password=? where Accountno=?");
			pstmt5.setString(1, password);
			pstmt5.setInt(2,accountno);
			pstmt5.executeUpdate();
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
		}
		
	
		@Override
		public int getAccNo(int userid, String password) {
			int accno=0;
			
			try {
				PreparedStatement pstmt=conn.prepareStatement("select Accountno from usersdetails where userid=? && password=?");
				pstmt.setInt(1, userid);
				pstmt.setString(2, password);
				 ResultSet rs=pstmt.executeQuery();
				 rs.next();
				 accno=rs.getInt(1);
				 
				 
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return accno;
		}


		@Override
		public boolean checkpassword(int accno, String password)
		{
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from usersdetails ");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				if(accno==rs.getInt(1) && password.equals(rs.getString(5)))
				{
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			return false;
		}


		@Override
		public boolean transfer(int accno, int tAccno, double tbal) {
             if(tbal>this.checkbalance(accno))
             {
            	 return false;
             }
             this.WithdrawAmount(accno,tbal);
             this.DepositeAmount(tAccno, tbal);
             try {
				PreparedStatement pstmt2=conn.prepareStatement("insert into transctiondetails values(?,?,?)");
				pstmt2.setInt(1, accno);
				pstmt2.setString(2, "transfer");
				pstmt2.setDouble(3, tbal);
				pstmt2.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
				

			return false;
		}


		

	}



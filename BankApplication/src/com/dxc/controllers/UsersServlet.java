package com.dxc.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.Transaction;
import com.dxc.pojos.Usersdetails;
import com.dxc.services.IUserService;
import com.dxc.services.UserServiceImpl;



@WebServlet("/Users")

public class UsersServlet extends HttpServlet 
{
	private int accno;
	public void init()
	{
		accno=0;
	}
	IUserService userservice =  new UserServiceImpl();
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			  String action="";
			  String message="";
			  String temp=request.getParameter("btn");
			  
			  if(temp!=null)
				  action=request.getParameter("btn");
			  HttpSession session=request.getSession();
			  
			  if(action.equals("login"))
			  {
				  int userid=Integer.parseInt(request.getParameter("userid"));
				  String password=request.getParameter("password");
				  accno=userservice.getAccNo(userid,password);
				  
				  boolean b = userservice.passwordcheck(userid,password);
				  
				  if(b == true) {
					  //message = "User Login Successfull...!!!!!";
					  //session.setAttribute("message", message);
					  //response.sendRedirect("view.jsp");
					  response.sendRedirect("UsersOptions.jsp");
				  }  
				 else
				  {
					  message = "User Login Failed..!!!!!";
					  session.setAttribute("message", message);
					  response.sendRedirect("view.jsp");
					  
				  }
			  }
		else if(action.equals("deposite")) 
		{
			int Accountno=Integer.parseInt(request.getParameter("Accountno"));
			double Accbalance = Double.parseDouble(request.getParameter("Accbalance"));
			userservice.DepositeAmount(Accountno,Accbalance);
			
		       message = "Amount Deposited Successfully...!!!!!";
			  session.setAttribute("message", message);
			  response.sendRedirect("view.jsp");
		}
			  
			  
		else if(action.equals("transferamount"))
		{
			String password=request.getParameter("password");
		    boolean b=userservice.checkpassword(accno,password);
		    if(b==true)
		    {
		    	int tAccno= Integer.parseInt(request.getParameter("tAccno"));
		    	double tbal=Double.parseDouble(request.getParameter("tbal"));
		    	
		    	boolean b1=userservice.transfer(accno,tAccno,tbal);
		    	 if(b1==true)
		    	 {
		    		 message = "Amount Transfered Successfully...!!!!!";
					  session.setAttribute("message", message);
					  response.sendRedirect("view.jsp"); 
		    	 }
		    	 else
		    	 {
		    		 response.getWriter().println("Insuffiecent Balance...");
		    	 }
		    }
		    else
		    {
		    	response.getWriter().println("password is incorrect...");
		    	
		    }
		}
			  
	     		  
		else if(action.equals("withdraw")) 
		{
			
			String password=request.getParameter("password");
			boolean b=userservice.checkpassword(accno, password);
			if(b==true)
			{
				
			
			double Accbalance = Double.parseDouble(request.getParameter("Accbalance"));
			boolean b2=userservice.WithdrawAmount(accno,Accbalance);
			
			if(b2==true)
			{
			  message = "Amount Withdraw Successfully...!!!!!";
			  session.setAttribute("message", message);
			  response.sendRedirect("view.jsp");
			}
			else
			{
				  message = "Sorry Insuffiecent Balance...!!!!!";
				  session.setAttribute("message", message);
				  response.sendRedirect("view.jsp");
			}
			}
			else
			{
				response.getWriter().println("password is incorrect");
			}
		}
			
			  
			  
			  
		else if(action.equals("checkbalance"))
		{
			int Accountno=Integer.parseInt(request.getParameter("Accountno"));
			double b=userservice.checkbalance(Accountno);
			try {
				PrintWriter pwt=response.getWriter();
				 pwt.println("Account balance is = " + b);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			  
			  
		else if(action.equals("passwordchange"))
		{
			String cpassword=request.getParameter("cpassword");
			String password=request.getParameter("password");
			if(password.equals(cpassword))
			{
				
			userservice.PasswordChange(accno,password);
			
			  message = "Password Change Successfully...!!!!!";
			  session.setAttribute("message", message);
			  response.sendRedirect("view.jsp");
			}
			else
			{
				response.getWriter().println("password mismatch re-enter password");
			}
		}
			  
		else 
		{
			int accountno=Integer.parseInt(request.getParameter("Accountno"));
			List<Transaction> list=userservice.TransactionDetails(accountno);
			session.setAttribute("list", list);
			response.sendRedirect("TransactionDetails.jsp");
	}
}
}

	


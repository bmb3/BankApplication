package com.dxc.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dxc.pojos.Usersdetails;
import com.dxc.services.AdminServiceImpl;
import com.dxc.services.IAdminService;


@WebServlet("/Admin")
public class AdminServlet extends HttpServlet
{
	private static final Object Accountno = null;
	IAdminService adminservice=new AdminServiceImpl();
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
		  String admin_id=request.getParameter("admin_id");
		  String password=request.getParameter("password");
		  
		  boolean bo = 	adminservice.passwordcheck(admin_id,password);
		  
		  if(bo == true) {
			  message = "Admin Login Successfull...!!!!!";
			  //session.setAttribute("message", message);
			  response.sendRedirect("AdminOptions.jsp");
		  }
		  else
		  {
			  message = "Admin Login Failed..!!!!!";
			  session.setAttribute("message", message);
			  response.sendRedirect("view.jsp");
			  
		  }
	  }
	  
	  
	  else if(action.equals("AddUsers"))
	  {
		  int Accountno=Integer.parseInt(request.getParameter("Accountno"));
			String Uname=request.getParameter("Uname");
			double Accbalance=Double.parseDouble(request.getParameter("Accbalance"));
			int userid=Integer.parseInt(request.getParameter("userid"));
			String password=request.getParameter("password");
			
		Usersdetails us=new Usersdetails(Accountno, Uname, Accbalance,userid, password );
		adminservice.Addusers (us);
		 message = "User Added Successfully...!!!!!";
		 session.setAttribute("message", message);
		 response.sendRedirect("view.jsp");
	  }
	  
	  
	  else if(action.equals("findusers"))
	  {
		  int Accountno=Integer.parseInt(request.getParameter("Accountno"));
			session.setAttribute("Accountno", Accountno);
			Usersdetails findStatus=adminservice.findusers(Accountno);
			session.setAttribute("findStatus", findStatus);
			response.sendRedirect("showusers.jsp");
			
	   }
	  
	  
	  else if(action.equals("search"))
		{
			int Accountno=Integer.parseInt(request.getParameter("Accountno"));
			session.setAttribute("Accountno", Accountno);
			boolean search=adminservice.searchUsers(Accountno);
			if(search==true)
			{
				response.sendRedirect("updateusers2.jsp");
			}
			else
			{
				message="User not found...!!!";
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
			}
		}
	  
	  
		else if(action.equals("updateusers"))
		{
			int Accountno=(int)session.getAttribute("Accountno");
			String Uname=request.getParameter("Uname");
			double Accbalance=Double.parseDouble(request.getParameter("Accbalance"));
			int userid=Integer.parseInt(request.getParameter("userid"));
			String password=request.getParameter("password");
			
		    Usersdetails user=new Usersdetails(Accountno, Uname, Accbalance, userid, password );
	
			adminservice.updateusers(user);
			message="UserDetails Successfully Updated...!!!!";
			session.setAttribute("message", message);
			response.sendRedirect("view.jsp");
		}
	  
	  
		else if(action.equals("showbalance"))
		{
			int Accountno=Integer.parseInt(request.getParameter("Accountno"));
			double b=adminservice.getbalance(Accountno);
			try {
				PrintWriter pwt=response.getWriter();
				 pwt.println("Account balance is = " + b);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	  
	  
		else if(action.equals("delete"))
		{
			int Accountno=Integer.parseInt(request.getParameter("Accountno"));
			adminservice.removeuser(Accountno);
			message="UserAccount Successfully  Removed...!!!!";
			session.setAttribute("message", message);
			response.sendRedirect("view.jsp");
		}
	  
	  
		else
		{
			List<Usersdetails> list=adminservice.getAllUsers();
			session.setAttribute("list", list);
			response.sendRedirect("showaccounts.jsp");
		}
	  
}
}
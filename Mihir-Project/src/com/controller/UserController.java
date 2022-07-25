package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.dao.UserDao;
import com.service.Services;


@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("action");
		System.out.println(action);
		if(action.equalsIgnoreCase("register"))
		{
			boolean flag = UserDao.checkEmail(request.getParameter("email"));
			
			if(flag ==true)
			{
				request.setAttribute("msg", "Email Id Already Registered");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			
			if(request.getParameter("password").equals(request.getParameter("cpassword")))
			{
				User u = new User();			
				u.setFname(request.getParameter("fname"));
				u.setLname(request.getParameter("lname"));
				u.setEmail(request.getParameter("email"));
				u.setMobile(Long.parseLong(request.getParameter("mobile")));
				u.setPassword(request.getParameter("password"));
				u.setCpassword(request.getParameter("cpassword"));
				u.setGender(request.getParameter("gender"));
				u.setAddress(request.getParameter("address"));
				u.setUsertype(request.getParameter("usertype"));
				UserDao.registerUser(u);
				request.setAttribute("msg", "Registration Successful!");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Password and Confirm Password Does not Matched");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("login"))
		{
			System.out.println("Login Called");
			User u = UserDao.checkLogin(request.getParameter("email"), request.getParameter("password"));
			
			if(u==null)
			{
				System.out.println("if Called");
				System.out.println(u);
				request.setAttribute("msg", "Invalid Email/Passowrd");
				request.getRequestDispatcher("login.jsp").forward(request, response); 
			}
			else
			{
				
				if(u.getUsertype().equals("user"))
				{
					
					HttpSession session = request.getSession();
					session.setAttribute("u",u);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else
				{
					
					HttpSession session = request.getSession();
					session.setAttribute("u",u);
					request.getRequestDispatcher("seller_index.jsp").forward(request, response);					
				}
				
				
				
				
			}			
		}
		else if(action.equalsIgnoreCase("change password"))
		{
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("u");
			
			if(u.getUsertype().equals("user"))
			{
				if(u.getPassword().equals(request.getParameter("old_password")))
				{
					if(request.getParameter("new_password").equals(request.getParameter("new_cpassword")))
					{
						UserDao.changePassword(u.getEmail(), request.getParameter("new_password"));
						response.sendRedirect("logout.jsp");
					}
					else
					{
						request.setAttribute("msg", "New Password and Confirm New Password Does not Match");
						request.getRequestDispatcher("changepassword.jsp").forward(request, response);
					}
				}
				else
				{
					request.setAttribute("msg", "Old Password is Incorrect");
					request.getRequestDispatcher("changepassword.jsp").forward(request, response);
				}
			}
			else
			{
				if(u.getPassword().equals(request.getParameter("old_password")))
				{
					if(request.getParameter("new_password").equals(request.getParameter("new_cpassword")))
					{
						UserDao.changePassword(u.getEmail(), request.getParameter("new_password"));
						response.sendRedirect("logout.jsp");
					}
					else
					{
						request.setAttribute("msg", "New Password and Confirm New Password Does not Match");
						request.getRequestDispatcher("seller_changepassword.jsp").forward(request, response);
					}
				}
				else
				{
					request.setAttribute("msg", "Old Password is Incorrect");
					request.getRequestDispatcher("seller_changepassword.jsp").forward(request, response);
				}
				
			}
			
			
		}
		else if(action.equalsIgnoreCase("update profile"))
		{
			User u = new User();
			u.setFname(request.getParameter("fname"));
			u.setLname(request.getParameter("lname"));
			u.setEmail(request.getParameter("email"));
			u.setMobile(Long.parseLong(request.getParameter("mobile")));
			u.setAddress(request.getParameter("address"));
			u.setUsertype(request.getParameter("usertype"));
			UserDao.updateProfile(u);
			HttpSession session = request.getSession();
			session.setAttribute("u", u);
			if(u.getUsertype().equals("user"))
			{
				request.getRequestDispatcher("profile.jsp").forward(request, response);				
			}
			else
			{
				request.getRequestDispatcher("seller_profile.jsp").forward(request, response);
			}
			
					
		}
		else if(action.equalsIgnoreCase("send otp"))
		{
			String email = request.getParameter("email");
			boolean flag = UserDao.checkEmail(email);
			
			if(flag == true)
			{
				int min = 1000;
				int max = 9999;
				int otp = (int)(Math.random()*(max-min+1)+min);
				Services.sendMail(email, otp);
				request.setAttribute("email", email);
				request.setAttribute("otp", otp);
				request.getRequestDispatcher("otp.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "Email Id not Registered.");
				request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
			}
		}
		
		else if(action.equalsIgnoreCase("verify otp"))
		{
			String email= request.getParameter("email");
			int otp1 = Integer.parseInt(request.getParameter("otp1"));
			int otp2 = Integer.parseInt(request.getParameter("otp2"));
			
			if(otp1==otp2)
			{
				request.setAttribute("email", email);
				request.getRequestDispatcher("newpassword.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("email", email);
				request.setAttribute("otp", otp1);
				request.setAttribute("msg", "Invalid OTP");
				request.getRequestDispatcher("otp.jsp").forward(request, response);
			}
			
		}
		else if(action.equalsIgnoreCase("update password"))
		{
			String email = request.getParameter("email");
			String np = request.getParameter("new_password");
			String cnp = request.getParameter("new_cpassword");
			
			if(np.equals(cnp))
			{
				UserDao.changePassword(email, np);
				response.sendRedirect("login.jsp");
				
			}
			else
			{
				request.setAttribute("email", email);
				request.setAttribute("msg", "New & Confirm New Password Does not match.");
				request.getRequestDispatcher("newpassword.jsp").forward(request, response);
				
			}
			
		}
		
	}

}

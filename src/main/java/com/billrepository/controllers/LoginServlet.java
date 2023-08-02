// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.billrepository.entities.User;
import com.billrepository.managers.UserManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String uniqueCode = request.getParameter("uniqueCode");
		String password = request.getParameter("password");
		
		User user = UserManager.getInstance().loginCheck(uniqueCode,password);
		if(user == null) {
			String message = "Invalid credentials";
			request.setAttribute("message", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("user",user);
			
			List<User> dealers = UserManager.getInstance().getDealersList(user);
			session.setAttribute("myDealers",dealers);
			
			List<User> distributors = UserManager.getInstance().fetchDistributorsList(uniqueCode);
			session.setAttribute("myDistributors",distributors);
			
			response.sendRedirect("index2.jsp");
		}
	}

}

// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.controllers;

import java.io.IOException;

import com.billrepository.entities.Relationship;
import com.billrepository.entities.User;
import com.billrepository.managers.RelationshipManager;
import com.billrepository.managers.UserManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.PageContext;

public class VerifyDealerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dealerCode = request.getParameter("dealerCode");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Relationship relationship = RelationshipManager.getInstance().createRelationship(dealerCode,user.getUniqueCode());
		boolean relationshipExists = RelationshipManager.getInstance().isRelationshipExists(relationship);
		if(!relationshipExists) {
			String msg = "Wrong code or dealer has not made you as a distributor";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("enterCode.jsp").forward(request, response);
		}else {
			if(UserManager.getInstance().isUserExists(dealerCode)) {
				User dealer = UserManager.getInstance().fetchUser(dealerCode);
				request.setAttribute("dealer", dealer);
				request.getRequestDispatcher("dealerDetails.jsp").forward(request, response);
			}else {
				String msg = "Dealer doesn't exists";
				request.setAttribute("message", msg);
				request.getRequestDispatcher("enterCode.jsp").forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}

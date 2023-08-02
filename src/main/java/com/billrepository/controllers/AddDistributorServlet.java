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

public class AddDistributorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String distributorCode = request.getParameter("distributorCode");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(distributorCode.equals(user.getUniqueCode())) {
			String msg = "Invalid distributor code";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("addDistributor.jsp").forward(request, response);
			return;
		}
		boolean userExists = UserManager.getInstance().isUserExists(distributorCode);
		if(!userExists) {
			String msg = "Distributor does not exists OR the code is wrong";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("addDistributor.jsp").forward(request, response);
			return;
		}else {
			//HttpSession session = request.getSession();
			//user = (User) session.getAttribute("user");
			String dealerCode = user.getUniqueCode();
			Relationship relationship = RelationshipManager.getInstance().createRelationship(dealerCode,distributorCode);
			boolean relationshipExists =  RelationshipManager.getInstance().isRelationshipExists(relationship);
			if(relationshipExists) {
				String msg = "Distributor was already added";
				request.setAttribute("message", msg);
				request.getRequestDispatcher("addDistributor.jsp").forward(request, response);
				return;
			}else {
				boolean relationshipAdded = RelationshipManager.getInstance().addRelationshipToDB(relationship);
				if(relationshipAdded) {
					String msg = "Distributor added successfully";
					request.setAttribute("message", msg);
					request.getRequestDispatcher("addDistributor.jsp").forward(request, response);
					return;
				}else {
					String msg = "Some error occured , try again";
					request.setAttribute("message", msg);
					request.getRequestDispatcher("addDistributor.jsp").forward(request, response);
					return;
				}
			}
		}
	}

}

package com.billrepository.controllers;

import java.io.IOException;
import java.util.List;

import com.billrepository.entities.Bill;
import com.billrepository.entities.User;
import com.billrepository.managers.BillManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class DealerBillsServlet
 */
public class DealerBillsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mySessionDealerID = request.getParameter("mySessionDealerID");
		String mySessionDealerIDBillList = request.getParameter("mySessionDealerIDBillList");
		HttpSession session = request.getSession();
		User dealer = (User)session.getAttribute(mySessionDealerID);
		User user = (User)session.getAttribute("user");
		List<Bill> dealerBillList =  BillManager.getInstance().fetchDealerBills(user.getUniqueCode(),dealer.getUniqueCode());
		session.setAttribute(mySessionDealerIDBillList, dealerBillList);
		response.sendRedirect("ViewAssignedBills.jsp?mySessionDealerID="+mySessionDealerID+"&mySessionDealerIDBillList="+mySessionDealerIDBillList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

// Jai Swaminarayan KESHAV , Swami-Shreeji
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

public class BillsAccordingToStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String billStatus = request.getParameter("billStatus");
		HttpSession session = request.getSession();
		session.setAttribute("billStatus", billStatus);
		User user = (User)session.getAttribute("user");
		if(billStatus.equalsIgnoreCase("all")) {
			request.getRequestDispatcher("BillsServlet").forward(request, response);
			return;
		}else if(billStatus.equalsIgnoreCase("unpaid")) {
			List<Bill> billList = BillManager.getInstance().getUnpaidBillsList(user.getUniqueCode());
			session.setAttribute("billList", billList);
			response.sendRedirect("bills.jsp");
		}else if(billStatus.equalsIgnoreCase("paid")) {
			List<Bill> billList = BillManager.getInstance().getPaidBillsList(user.getUniqueCode());
			session.setAttribute("billList", billList);
			response.sendRedirect("bills.jsp");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}

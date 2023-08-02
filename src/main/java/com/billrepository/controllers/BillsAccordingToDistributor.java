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

public class BillsAccordingToDistributor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String distributorCriteria = request.getParameter("distributorCriteria");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(distributorCriteria.equalsIgnoreCase("all")) {
			session.setAttribute("distributorCriteria", distributorCriteria);
			response.sendRedirect("BillsServlet?billStatus="+session.getAttribute("billStatus"));
		}else {
			List<Bill> particularDistributorBills = BillManager.getInstance().fetchBillsOfParticularDistributor(user.getUniqueCode(),distributorCriteria);
			session.setAttribute("billList", particularDistributorBills);
			response.sendRedirect("bills.jsp");
		}
	}

}

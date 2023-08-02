// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.controllers;

import java.io.IOException;
import java.util.List;

import com.billrepository.entities.Bill;
import com.billrepository.entities.User;
import com.billrepository.managers.BillManager;
import com.billrepository.managers.UserManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class BillsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Bill> billList = BillManager.getInstance().fetchBills(user.getUniqueCode());
		session.setAttribute("billList", billList);
		List<User> distributorList = UserManager.getInstance().fetchDistributorsList(user.getUniqueCode());
		session.setAttribute("distributorList", distributorList);
		session.setAttribute("billStatus", "all");
		response.sendRedirect("bills.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

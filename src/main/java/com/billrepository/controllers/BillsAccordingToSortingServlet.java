// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.controllers;

import java.io.IOException;
import java.util.List;

import com.billrepository.entities.Bill;
import com.billrepository.managers.BillManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


	public class BillsAccordingToSortingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sortingCriteria = request.getParameter("sortingCriteria");
		HttpSession session = request.getSession();
		session.setAttribute("sortingCriteria",sortingCriteria);
		String billStatus = (String)session.getAttribute("billStatus");
		if(sortingCriteria.equalsIgnoreCase("none")) {
			response.sendRedirect("BillsAccordingToStatusServlet?billStatus="+billStatus);
		}else {
			BillManager.getInstance().sortBills(sortingCriteria,(List<Bill>)session.getAttribute("billList"));
			response.sendRedirect("bills.jsp");
		}
	}

}

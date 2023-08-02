// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.controllers;

import java.io.IOException;
import java.io.InputStream;

import com.billrepository.managers.BillManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177216)
public class AddPaidReceiptPDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String billID = request.getParameter("billID");
		Part part = request.getPart("paidReceiptPDF");
		InputStream in = part.getInputStream();
		boolean inserted = BillManager.getInstance().savePaidReceiptPDF(billID,in);
		if(inserted) {
			String msg = "Successfully inserted";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("MarkAsPaid.jsp").forward(request, response);
		}else {
			String msg = "Some error occured , try again later";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("MarkAsPaid.jsp").forward(request, response);
		}
	}

}

// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.billrepository.managers.BillManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DownloadBillPDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String billID = request.getParameter("billID");
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition","attachment; filename="+billID+".pdf");
		OutputStream out = response.getOutputStream();
		InputStream in = BillManager.getInstance().getPDFInputStream(billID);
		byte[] b = new byte[100];
		while(in.read(b) != -1) {
			out.write(b);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

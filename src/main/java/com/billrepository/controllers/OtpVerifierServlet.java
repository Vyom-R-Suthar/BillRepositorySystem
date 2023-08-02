// Jai Swaminarayan KESHAV, Swami-Shreeji
package com.billrepository.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.billrepository.entities.User;
import com.billrepository.managers.UserManager;
import com.billrepository.utilities.ImageSaver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OtpVerifierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String enteredOtp = request.getParameter("otp");
		HttpSession session = request.getSession();
		String actualOtp = (String)session.getAttribute("otp");
		
		User user = (User)session.getAttribute("tempRegData");
		InputStream in = (InputStream)session.getAttribute("tempPhotoStream");
		
		if(enteredOtp.equals(actualOtp)) {
			boolean imageInserted = ImageSaver.saveToServer(in, user.getUniqueCode());
			File f = new File("C:\\JDBC\\BillRepositorySystem\\src\\main\\webapp\\pdf\\"+user.getUniqueCode());
			f.mkdir();
			boolean userInserted = UserManager.getInstance().saveUserToDB(user);
			if(userInserted) {
				String msg = "Successfully inserted";
				request.setAttribute("message",msg);
				request.getRequestDispatcher("register2.jsp").forward(request, response);
			}else {
				String msg = "Some error occured , please try again";
				request.setAttribute("message",msg);
				request.getRequestDispatcher("register2.jsp").forward(request, response);
			}
		}else {
			String msg = "Wrong OTP";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("verifyOtp.jsp").forward(request, response);
		}
	}

}

// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.billrepository.entities.User;
import com.billrepository.managers.UserManager;
import com.billrepository.utilities.ImageSaver;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177216)
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName");
		String firmName = request.getParameter("firmName");
		String mobileNo = request.getParameter("mobileNo");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		String uniqueCode = request.getParameter("uniqueCode");
		
		Part part = request.getPart("photo");
		InputStream in = part.getInputStream();
		
		boolean isUnique = UserManager.getInstance().verifyUniquenessOfCode(uniqueCode);
		
		if(!isUnique) {
			String msg = "This code already exists";
			request.setAttribute("message",msg);
			request.getRequestDispatcher("register2.jsp").forward(request, response);
		}
		boolean imageInserted = ImageSaver.saveToServer(in, uniqueCode);
		File f = new File("C:\\JDBC\\BillRepositorySystem\\src\\main\\webapp\\pdf\\"+uniqueCode);
		f.mkdir();
		User user = UserManager.getInstance().createUser(userName,firmName,mobileNo,address,password,uniqueCode);
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
		
	}

}

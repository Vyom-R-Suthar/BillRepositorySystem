// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.billrepository.entities.User;
import com.billrepository.managers.UserManager;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177216)
public class OtpGeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		User user = UserManager.getInstance().createUser(userName,firmName,mobileNo,address,password,uniqueCode);
		HttpSession session = request.getSession();
		session.setAttribute("tempRegData",user);
		session.setAttribute("tempPhotoStream",in);
		
		int intOtp = (int)(Math.random()*100000);
		String otp = String.valueOf(intOtp);
		int responseCode = sendMSG(otp,user.getMobileNo());
		if(responseCode == 200) {
			String msg = "OTP sended successfully";
			session.setAttribute("otp", otp);
			request.setAttribute("message",msg);
			RequestDispatcher rd = request.getRequestDispatcher("verifyOtp.jsp");
			rd.forward(request, response);
		}else {
			String msg = "Could not send OTP, please try again later";
			request.setAttribute("message", msg);
			RequestDispatcher rd = request.getRequestDispatcher("register2.jsp");
		}
	}
	
	public static int sendMSG(String otp,String number) {
		String apiKey = "qenm8AUDdjk1TH2phVLcf9QgltwS50KFN3IXozBWy4GaCJu6OxZXKF3dQS1kUyN9BuEoqJcpVe2Cw6ja";
		String route = "otp";
		String variables_values = otp;
		String numbers = number;
		String flash = "0";
		String myURL = "https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&variables_values="+otp+"&route=otp&numbers="+numbers+"";
		int code = 0;
		try {
			URL url = new URL(myURL);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");
			code = con.getResponseCode();
			System.out.println(code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

}

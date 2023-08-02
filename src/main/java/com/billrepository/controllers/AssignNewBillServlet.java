// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import com.billrepository.entities.Bill;
import com.billrepository.entities.User;
import com.billrepository.managers.BillManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177216)
public class AssignNewBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String billDate = request.getParameter("billDate");
		int billNo = Integer.parseInt(request.getParameter("billNo"));
		double billAmount = Double.parseDouble(request.getParameter("billAmount"));
		int dueDays = Integer.parseInt(request.getParameter("dueDays"));
		String description = request.getParameter("description");
		Part part = request.getPart("billPDF");
		InputStream in = part.getInputStream();
		String billStatus = request.getParameter("billStatus");
		
		String mySessionDealerID = request.getParameter("mySessionDealerID");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		User dealer = (User)session.getAttribute(mySessionDealerID);
		
		String billID = dealer.getUniqueCode()+"_"+user.getUniqueCode()+"_"+billNo;
		
		boolean billExists = BillManager.getInstance().isBillExists(billID);
		if(billExists) {
			String msg = "Bill already exists";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("AssignNewBill.jsp").forward(request, response);
			return;
		}else {
			String billPDFPath = BillManager.getInstance().saveBillPDF(dealer.getUniqueCode(),billID,in);
			Bill bill = BillManager.getInstance().createBill(billID,billNo,dealer.getUniqueCode(),user.getUniqueCode(),billAmount,billDate,dueDays,billStatus,billPDFPath,"Payment is remaining",description);
			
			boolean savedToDB = BillManager.getInstance().saveBillToDB(bill);
			if(savedToDB) {
				int hour = new Date().getHours();
				String greeting;
				if(hour >= 0 && hour <= 11) {
					greeting = "Good Morning !";
				}else if(hour >=12 && hour <= 16) {
					greeting = "Good Afternoon !";
				}else {
					greeting = "Good Evening !";
				}
				String message = greeting + "\n A new bill is inserted in your account by : " + user.getFirmName() + " on " + new Date() +"\n- Bill Repository System";
				sendMSG(message,dealer.getMobileNo());
				String msg = "Bill successfully inserted";
				request.setAttribute("message", msg);
				request.getRequestDispatcher("AssignNewBill.jsp").forward(request, response);
				return;
			}else {
				String msg = "Some error occured , try again";
				request.setAttribute("message", msg);
				request.getRequestDispatcher("AssignNewBill.jsp").forward(request, response);
				return;
			}
		}
	}
	
	public static int sendMSG(String message,String number) {
		String apiKey = "qenm8AUDdjk1TH2phVLcf9QgltwS50KFN3IXozBWy4GaCJu6OxZXKF3dQS1kUyN9BuEoqJcpVe2Cw6ja";
		String route = "q";
		String variables_values = message;
		try {
			message = URLEncoder.encode(message, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String numbers = number;
		String flash = "0";
		String myURL = "https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&message="+message+"&language=english&route=q&numbers="+numbers;
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
		System.out.println("Response code : " + code);
		return code;
	}

}

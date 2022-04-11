package com.carpark.reg.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.UserDAO;
import com.carpark.model.User;

@WebServlet("/VerifyOTP")
public class VerifyOTP extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String uOTP = request.getParameter("uOTP");
		String otp = (String) session.getAttribute("OTP");
		User u = (User) session.getAttribute("new-user");
		if(otp.equals(uOTP)) {
			UserDAO udao = new UserDAO();
			udao.addUser(u);
			session.setAttribute("user", udao.fromCreds(u.getUn(), u.getPw()));
			session.removeAttribute("new-user");
			session.removeAttribute("validated");
			session.removeAttribute("OTP");
			response.sendRedirect("home.jsp");
			
		}else {
			session.setAttribute("invalid-reg","Incorrect OTP enterred");
			response.sendRedirect("register.jsp");
		}
	}

}

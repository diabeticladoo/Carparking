package com.carpark.reg.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.UserDAO;
import com.carpark.helper.Mailer;
import com.carpark.model.User;
import com.carpark.reg.helper.OTPsender;

@WebServlet("/RegCred")
public class RegCred extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String fn = request.getParameter("fname");
		String ln = request.getParameter("lname");
		String un = request.getParameter("uname");
		String p = request.getParameter("pass");
		String pC = request.getParameter("passCon");
		String ad = request.getParameter("address");
		String em = request.getParameter("email");
		String ph = request.getParameter("phno");
		String cr = request.getParameter("carno");
		
		User u = new User("uname",un);
		u.setDetails(fn, ln,"", p, ad, em, ph, cr, "C");
		session.setAttribute("new-user", u);
		
		boolean error = false;
		
		if(request.getParameter("val")!=null) {
			boolean valid = true;
			if(!p.equals(pC)){//password != confirm
				session.setAttribute("invalid-reg","Password mismatch");
				valid = false;
			}
			if(ph.length()!=10){//Phno != 10 dig
				session.setAttribute("invalid-reg","Invalid phone number");
				valid = false;
			}
			if(!Mailer.isValid(em)) {
				session.setAttribute("invalid-reg", "Invalid email");
				valid = false;
			}
			if(UserDAO.alreadyExists("uname", un)) {
				session.setAttribute("invalid-reg", "Username is taken");
				valid = false;
			}
			if(UserDAO.alreadyExists("carno", cr)) {
				session.setAttribute("invalid-reg", "Car is associated with an existing account");
				valid = false;
			}
			if(UserDAO.alreadyExists("email", em)) {
				session.setAttribute("invalid-reg", "Email is associated with an existing account");
				valid = false;
			}
			if(UserDAO.alreadyExists("phno", ph)) {
				session.setAttribute("invalid-reg", "Phone no. is associated with an existing account");
				valid = false;
			}
			if(valid) {
				session.setAttribute("validated", true);
			}
		}else {
			String Otp = OTPsender.generate(6);
			if(request.getParameter("emlOTP")!=null) {
				u.setLogw("CE");
				OTPsender.send(Otp, "mail", em);
			}else if(request.getParameter("smsOTP")!=null) {
				u.setLogw("CP");
				OTPsender.send(Otp, "sms", ph);
			}
			session.setAttribute("OTP", Otp);
		}
		response.sendRedirect("register.jsp");
	}

}

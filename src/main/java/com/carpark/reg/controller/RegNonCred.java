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

@WebServlet("/RegNonCred")
public class RegNonCred extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String ad = request.getParameter("address");
		String ph = request.getParameter("phno");
		String cr = request.getParameter("carno");
		
		User u = (User) session.getAttribute("new-user-re");
		if(u!=null) {
			boolean valid = true;
			u.setDetails("", "", "", "", ad, "", ph, cr, "");
//			System.out.println(u);
			if (ph.length() != 10) {// Phno != 10 dig
				session.setAttribute("invalid-fill", "Invalid phone number");
				valid = false;
			}
			if (UserDAO.alreadyExists("carno", cr)) {
				session.setAttribute("invalid-fill", "Car is associated with an existing account");
				valid = false;
			}
			if (UserDAO.alreadyExists("email", u.getEmail())) {
				session.setAttribute("invalid-fill", "Email is associated with an existing account, please register with a different account");
				valid = false;
			}
			if (UserDAO.alreadyExists("phno", ph)) {
				session.setAttribute("invalid-fill", "Phone no. is associated with an existing account");
				valid = false;
			}
			if (valid) {
				UserDAO udao = new UserDAO();
				udao.addUser(u);
				u = udao.fromNonCred(u.getEmail());
				session.setAttribute("user", u);
				session.removeAttribute("new-user-re");
				response.sendRedirect("home.jsp");
			}else {
				response.sendRedirect("fill.jsp");
			}
		}else {
			session.removeAttribute("new-user-re");
			response.sendRedirect("register.jsp");
		}
		
	}

}

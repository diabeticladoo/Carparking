package com.carpark.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.UserDAO;
import com.carpark.model.User;

@WebServlet("/LoginCred")
public class LoginCred extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		HttpSession session = request.getSession();
		UserDAO udao = new UserDAO();
		User u = udao.fromCreds(uname,pass);
		if(u!=null) {
			session.setAttribute("user",u);
			response.sendRedirect("home.jsp");
		}else {
			session.setAttribute("invalid-login","Invalid credentials");
			response.sendRedirect("login.jsp");			
		}
	}

}

package com.carpark.admlog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.AdminDAO;
import com.carpark.model.Admin;

@WebServlet("/AdmLogin")
public class AdmLogin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		HttpSession session = request.getSession();
		AdminDAO adao = new AdminDAO();
		Admin a = adao.fromCreds(uname,pass);
		if(a!=null) {
			session.setAttribute("admin",a);
			response.sendRedirect("admhome.jsp");
		}else {
			session.setAttribute("invalid-admlogin","Invalid credentials");
			response.sendRedirect("admlogin.jsp");			
		}
	}

}

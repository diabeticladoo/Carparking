package com.carpark.wrklog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.WorkerDAO;
import com.carpark.model.Worker;

@WebServlet("/WrkLogin")
public class WrkLogin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		HttpSession session = request.getSession();
		WorkerDAO wdao = new WorkerDAO();
		Worker w = wdao.fromCreds(uname,pass);
		if(w!=null) {
			session.setAttribute("worker",w);
			response.sendRedirect("wrkhome.jsp");
		}else {
			session.setAttribute("invalid-wrklogin","Invalid credentials");
			response.sendRedirect("wrklogin.jsp");			
		}
	}

}

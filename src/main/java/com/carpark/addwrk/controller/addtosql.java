package com.carpark.addwrk.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.ParkDAO;
import com.carpark.dao.WorkerDAO;

/**
 * Servlet implementation class addtosql
 */
@WebServlet("/addtosql")
public class addtosql extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		WorkerDAO wdao = new WorkerDAO();
		ParkDAO pdao = new ParkDAO();
		String fname = (String) session.getAttribute("fname");
		String joining = (String) session.getAttribute("Time");
		int pid = Integer.parseInt(request.getParameter("pid"));
		int a = WorkerDAO.maxID() + 1;
		String username = "worker"+ a;
		String password = "1234";
		wdao.addWorker(username, password, fname, joining, pid);
		pdao.assignWorker(""+pid, a);		
		response.sendRedirect("admhome.jsp");
		
	}

}

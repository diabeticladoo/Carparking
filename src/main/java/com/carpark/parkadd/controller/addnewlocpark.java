package com.carpark.parkadd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.ParkDAO;
import com.carpark.model.Park;

/**
 * Servlet implementation class addnewlocpark
 */
@WebServlet("/addnewlocpark")
public class addnewlocpark extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String Loc = null;
		
		String loc = request.getParameter("newloc");
		ParkDAO pdao = new ParkDAO();
		
		pdao.addParkspace(loc);
		session.setAttribute("Parkadded", true);
//		if(p!=null) {
//			session.setAttribute("Info", p);
//			if(session.getAttribute("Location")==null) {
//				session.setAttribute("Location", Loc);
//			}
//			if(session.getAttribute("Time")==null) {
//    			session.setAttribute("Time", CiDT);    				
//			}
			response.sendRedirect("addparking.jsp");
//		}else System.out.println("NULL!");
	}
}
package com.carpark.addwrk.controller;

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
 * Servlet implementation class viewparkdetails
 */
@WebServlet("/addingworker")	
public class addingworker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		String Loc = request.getParameter("Location");
    		String fname = request.getParameter("fname");
    		String lname = request.getParameter("lname");
    		
    		String CiDT = request.getParameter("DateTimeIn")+" 00:00";
    		String CoDT = CiDT;
    		HttpSession session = request.getSession();
    		ParkDAO pdao = new ParkDAO();
    		ArrayList<Park> p = pdao.fromBooking2(Loc, CiDT, CoDT);
    		if(p!=null) {
    			session.setAttribute("Info", p);
    			session.setAttribute("Location", Loc);
    			session.setAttribute("lname", lname);
    			session.setAttribute("fname", fname);
    			session.setAttribute("Time", CiDT);
    			response.sendRedirect("addwrktable.jsp");
    		}else System.out.println("NULL!");
    	}
}


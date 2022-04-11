package com.carpark.parkview.controller;

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
 * Servlet implementation class ViewParkspaces
 */
@WebServlet("/ViewParkspaces")
public class ViewParkspaces extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String Loc = request.getParameter("Location");
		session.setAttribute("loc", Loc);
		ParkDAO pdao = new ParkDAO();
		ArrayList<Park> p = pdao.atLoc(Loc);
		if(p!=null) {
			session.setAttribute("Spaces", p);
			response.sendRedirect("viewpark.jsp");
		}else System.out.println("NULL!");
	}

}

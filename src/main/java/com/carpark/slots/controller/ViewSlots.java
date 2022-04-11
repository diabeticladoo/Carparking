package com.carpark.slots.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.ParkDAO;
import com.carpark.dao.WorkerDAO;
import com.carpark.helper.DateTime;
import com.carpark.model.Park;

/**
 * Servlet implementation class ViewSlots
 */
@WebServlet("/ViewSlots")
public class ViewSlots extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ParkDAO pdao = new ParkDAO();
		
		String pid = (String) session.getAttribute("Pid");
		String date = request.getParameter("Dt");
		ArrayList<Park> slots= pdao.bookingFromPid(pid,date);
		ArrayList<String> dates = new ArrayList<String>();
		session.setAttribute("Slots",slots);
		session.setAttribute("dt",date);
		response.sendRedirect("ParkSlotInfo.jsp");
	}

}

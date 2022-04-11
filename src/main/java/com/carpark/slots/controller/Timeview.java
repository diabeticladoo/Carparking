package com.carpark.slots.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.ParkDAO;
import com.carpark.helper.DateTime;
import com.carpark.model.Park;

@WebServlet("/Timeview")
public class Timeview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ParkDAO pdao = new ParkDAO();
		
		String pid = request.getParameter("pid");
		ArrayList<Park> slots= pdao.bookingFromPid(pid);
		ArrayList<String> dates = new ArrayList<String>();
		for(Park p : slots) {
			String date = DateTime.dateFromDT(p.getDTin());
			dates.add(date);
		}
		dates = new ArrayList<String>(new HashSet<String>(dates));
		Collections.sort(dates);
		session.setAttribute("Dates",dates);
		session.setAttribute("Pid", pid);
		
		response.sendRedirect("ParkSlotInfo.jsp");
	}

}
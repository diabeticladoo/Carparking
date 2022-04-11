package com.carpark.slots.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carpark.dao.ParkDAO;
import com.carpark.dao.WorkerDAO;


@WebServlet("/RemovingTime")
public class RemovingTime extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParkDAO pdao = new ParkDAO();
		String pid = request.getParameter("pid");
		String DTout = request.getParameter("time");
		pdao.removeBooking(pid,DTout);
		response.sendRedirect("viewpark.jsp");
	}

}
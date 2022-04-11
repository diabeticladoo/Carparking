package com.carpark.parking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carpark.dao.ParkDAO;
import com.carpark.dao.WorkerDAO;

@WebServlet("/RemoveParkspace")
public class RemoveParkspace extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParkDAO pdao = new ParkDAO();
		WorkerDAO wdao = new WorkerDAO();
		String pid = request.getParameter("pid");
		pdao.removeBooking(pid);
		wdao.unassignParkspace(pdao.getWid(Integer.parseInt(pid)));
		pdao.removeParkspace(pid);
		response.sendRedirect("viewpark.jsp");
	}

}
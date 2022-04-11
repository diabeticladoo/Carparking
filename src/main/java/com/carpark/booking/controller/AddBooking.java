package com.carpark.booking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.ParkDAO;
import com.carpark.model.Park;

/**
 * Servlet implementation class AddBooking
 */
@WebServlet("/AddBooking")
public class AddBooking extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Park slot = (Park) session.getAttribute("Slot");
		if(slot!=null) {
			ParkDAO pdao = new ParkDAO();
			pdao.addBooking(slot);
			response.sendRedirect("mybookings.jsp");
		}else {
			response.sendRedirect("Booking.jsp");
		}
	}

}

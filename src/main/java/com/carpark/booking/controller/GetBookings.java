package com.carpark.booking.controller;

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
import com.carpark.model.User;
import com.carpark.waiting.dao.WaitDAO;

@WebServlet("/GetBookings")
public class GetBookings extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if(u!=null) {
			ParkDAO pdao = new ParkDAO();
			ArrayList<Park> bookings = pdao.getBookings(u.getUid());
			session.setAttribute("Bookings", bookings);

			WaitDAO wadao = new WaitDAO();
			ArrayList<Park> waits = wadao.getWait(u.getUid());
			session.setAttribute("Waits", waits);
			
			response.sendRedirect("mybookings.jsp");
		}else {
			response.sendRedirect("mybookings.jsp");
		}
	}

}

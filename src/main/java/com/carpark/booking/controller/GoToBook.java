package com.carpark.booking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.model.Park;
import com.carpark.model.User;

/**
 * Servlet implementation class GoToPay
 */
@WebServlet("/GoToBook")
public class GoToBook extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Pid = request.getParameter("Pid");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
//		System.out.println(u);
		Park slot = new Park(
				(String)session.getAttribute("Location"),
				(String)session.getAttribute("DateTimeIn"),
				(String)session.getAttribute("DateTimeOut")
			);
		if(u!=null) {
			slot.setPID(Pid);
			slot.setUid(u.getUid());
			slot.setCost(Park.getBill(slot.getDTin(), slot.getDTout()));
			session.setAttribute("Slot", slot);
			session.removeAttribute("ParkingList");
			response.sendRedirect("bookchosen.jsp");
			
		}
	}

}

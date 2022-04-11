package com.carpark.booking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.model.User;
import com.carpark.waiting.dao.WaitDAO;

@WebServlet("/AddWaiting")
public class AddWaiting extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if(u!=null) {
			WaitDAO wadao = new WaitDAO();
			wadao.addWait(
					u.getUid(), 
					(String) session.getAttribute("Location"),
					(String) session.getAttribute("DateTimeIn"),
					(String) session.getAttribute("DateTimeOut")
				);
			response.sendRedirect("mybookings.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}
	}

}

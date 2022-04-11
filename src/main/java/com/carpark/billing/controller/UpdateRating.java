package com.carpark.billing.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.WorkerDAO;

@WebServlet("/UpdateRating")
public class UpdateRating extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		WorkerDAO wdao = new WorkerDAO();
		int wid = (int) session.getAttribute("Wid");
		int rating = Integer.parseInt(request.getParameter("Rating"));
		wdao.feedbackWid(wid, rating);
		session.removeAttribute("Wid");
		response.sendRedirect("home.jsp");
	}

}

package com.carpark.billing.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.billing.dao.LinkDAO;

/**
 * Servlet implementation class FeedBack
 */
@WebServlet("/FeedBack")
public class FeedBack extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String link = request.getParameter("link");
		LinkDAO ldao = new LinkDAO();
		int wid = ldao.getWid(link);
		if(wid>0) {
			session.setAttribute("Wid",wid);
			ldao.remove(link);
			response.sendRedirect("feedback.jsp");
		}else {
			response.sendRedirect("home.jsp");
		}
	}

}

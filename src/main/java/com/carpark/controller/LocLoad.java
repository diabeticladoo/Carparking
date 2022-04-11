package com.carpark.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.ParkDAO;

@WebServlet("/LocLoad")
public class LocLoad extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String redir = (String) session.getAttribute("redirLoc");
		session.removeAttribute("redirLoc");
		ParkDAO pdao = new ParkDAO();
		ArrayList<String> locs = pdao.getLocs();
		session.setAttribute("locList", locs);
		response.sendRedirect(redir);
	}

}

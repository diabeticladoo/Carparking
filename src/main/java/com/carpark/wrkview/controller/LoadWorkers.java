package com.carpark.wrkview.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.WorkerDAO;
import com.carpark.model.Worker;

@WebServlet("/LoadWorkers")
public class LoadWorkers extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		WorkerDAO wdao = new WorkerDAO();
		ArrayList<Worker> wList = wdao.listAll();
		session.setAttribute("wlist", wList);
		response.sendRedirect("viewwrk.jsp");
	}

}

package com.carpark.wrkview.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carpark.billing.dao.LinkDAO;
import com.carpark.dao.ParkDAO;
import com.carpark.dao.WorkerDAO;

@WebServlet("/RemoveWorker")
public class RemoveWorker extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WorkerDAO wdao = new WorkerDAO();
		LinkDAO ldao = new LinkDAO();
		ParkDAO pdao = new ParkDAO();
		int Wid = Integer.parseInt(request.getParameter("wid"));
		pdao.unassignWorker(wdao.getPid(Wid));
		ldao.remove(Wid);
		wdao.removeWorker(Wid);
		response.sendRedirect("LoadWorkers");
	}

}

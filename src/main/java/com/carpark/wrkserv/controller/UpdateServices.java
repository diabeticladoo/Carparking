package com.carpark.wrkserv.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.WorkerDAO;
import com.carpark.model.Worker;

/**
 * Servlet implementation class UpdateServices
 */
@WebServlet("/UpdateServices")
public class UpdateServices extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Worker w = (Worker) session.getAttribute("worker");
		if(w!=null) {
			boolean serv[] = {false,false,false,false};
			for(int i = 0; i < 4; i++) {
				serv[i] = request.getParameter("Serv"+i) != null;
			}
			String servStr = Worker.strFromServ(serv);
			w.setServices(servStr);
			session.setAttribute("worker", w);
			WorkerDAO wdao = new WorkerDAO();
			wdao.updateServ(w.getWid(),servStr);
			response.sendRedirect("wrkhome.jsp");			
		}else {
			response.sendRedirect("wrklogin.jsp");	
		}
	}

}

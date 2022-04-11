package com.carpark.billing.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.billing.dao.LinkDAO;
import com.carpark.dao.ParkDAO;
import com.carpark.dao.UserDAO;
import com.carpark.helper.Mailer;
import com.carpark.helper.SMSer;
import com.carpark.model.Park;
import com.carpark.model.User;

/**
 * Servlet implementation class Pay
 */
@WebServlet("/Pay")
public class Pay extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		String Pid = request.getParameter("pid");
		String Cout = request.getParameter("dtout");
		int Cost = Integer.parseInt(request.getParameter("cost"));
		String uPromo =  request.getParameter("upromo").toUpperCase();
		if(u!=null && Pid!=null) {
			ParkDAO pdao = new ParkDAO();
			UserDAO udao = new UserDAO();
			int Wid = pdao.getWid(Integer.parseInt(Pid));
			pdao.payBooking(Pid,Cout);
			udao.changePromoCnt(u.getUid(),1);
			if(uPromo.length()>0 && uPromo.equals(udao.getPromo(u.getUid()))) {
				udao.removePromo(u.getUid());
				udao.changePromoCnt(u.getUid(), -7);
				Cost = (Cost/5)*4;
			}
			String msg = "You have paid Rs. " + Cost + ". Thank you for using our services!";
			if(Wid>0) {
				LinkDAO ldao = new LinkDAO();
				String link = ldao.genLink(Wid);
				msg = msg + "\nYou can rate your experience at the link below."
						+ "\n" + "http://localhost:8080/Car_Park/FeedBack?link="+link; 
			}
			String logw = u.getLogw();
			if(logw.equals("CP")) {
				SMSer smser = new SMSer(u.getPhno(),msg);
				smser.send();
			}else {
				Mailer mailer = new Mailer(u.getEmail(),"Payment Confirmation",msg);
				mailer.send();
			}
			response.sendRedirect("home.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}
	}

}

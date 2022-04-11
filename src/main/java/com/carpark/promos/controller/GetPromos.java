package com.carpark.promos.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.UserDAO;
import com.carpark.model.User;

/**
 * Servlet implementation class GetPromos
 */
@WebServlet("/GetPromos")
public class GetPromos extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDAO udao = new UserDAO();
		User u = (User) session.getAttribute("user");
		if(u!=null) {
			String promo = udao.getPromo(u.getUid());
			if(promo.length()==0 && udao.getPromoCnt(u.getUid())>5) {
				promo = User.genPromo();
				udao.setPromo(u.getUid(), promo);
			}
			session.setAttribute("Promos", promo);
			response.sendRedirect("promocodes.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}
	}

}

package com.carpark.booking.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpark.dao.ParkDAO;
import com.carpark.model.Park;

/**
 * Servlet implementation class BookingDetails
 */
@WebServlet("/BookingDetails")
public class BookingDetails extends HttpServlet {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		HttpSession session = request.getSession();
			
			String Loc = request.getParameter("Location");
    		String Dt = request.getParameter("Date");
    		String TIn = request.getParameter("TIn");
    		String TOut = request.getParameter("TOut");

    		boolean valid = true;
    		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    		if(today.compareTo(Dt)>=0) {
    			valid = false;
    			session.setAttribute("invalid-dt", "Only future dates can be selected");
    		}
    		if(TIn.compareTo(TOut)>=0) {
    			valid = false;
    			session.setAttribute("invalid-dt", "Check-in time must be before check-out time");
    		}
    		if(valid) {        		
        		String CiDT = Dt + " " + TIn;
        		String CoDT = Dt + " " + TOut;
        		ParkDAO pdao = new ParkDAO();
        		ArrayList<Park> p = pdao.fromBooking2(Loc, CiDT, CoDT);
        		if(p!=null) {
        			session.setAttribute("ParkingList", p);
        			boolean showWaiting = true;
        			for(Park park : p) {
        				if(park.getEmt()==1) {
        					showWaiting = false;
        					break;
        				}
        			}
        			if(showWaiting) session.setAttribute("showWaiting", true);
        			session.setAttribute("Location", Loc);
        			session.setAttribute("DateTimeIn", CiDT);
        			session.setAttribute("DateTimeOut", CoDT);
        			response.sendRedirect("Choosing.jsp");
        		}else System.out.println("NULL!");
    		}else {
    			response.sendRedirect("Booking.jsp");
    		}
    	}
}

package com.zobus.endpoints;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zobus.database.BookingsDAO;

/**
 * Servlet implementation class ViewSeats
 */
@WebServlet(description = "this end point is using will return the available seats with booked passanger gender",
			urlPatterns = { "/api/seat-info" }) 
public class ViewSeats extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewSeats() {
        super(); 
    }
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bus_id = request.getParameter("bus_id");
		String date_time = request.getParameter("date");
		Timestamp date = new Timestamp(Long.parseLong(date_time)); 
		response.setContentType("application/json");
		response.getWriter().print(BookingsDAO.GetAvailableSeats(bus_id, date).toString());
	}
}
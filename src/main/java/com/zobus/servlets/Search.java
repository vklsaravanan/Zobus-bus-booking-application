package com.zobus.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zobus.model.SearchBusesModel;
import com.zobus.database.BusesDAO;

import com.google.gson.Gson;

/**
 * Servlet implementation class Search
 */
@WebServlet(description = "search servlet is used for user search the buses with source and destination with date", urlPatterns = {
		"/search" })
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String date = request.getParameter("date");
		System.out.println("from: " + from);
		System.out.println("to: " + to);
		System.out.println("day_of_week: " + date);

		List<SearchBusesModel> buses = null; 

		try {
//		response.getWriter().print(query==null || query.size()==0);

			buses = BusesDAO.searchBus(from, to, date);
//		System.out.println();
		} catch (Exception e) {
			System.err.println("error from : " + this.getClass().getName());
		}
//		if request is JSON Type send as a end point else send JSP page with attribute
		if (request.getHeader("Accept").toLowerCase().indexOf("application/json") > -1) {

			response.setContentType("application/json");
			response.getWriter().print(new Gson().toJson(buses));

		} else {
			request.setAttribute("buses", buses);
			RequestDispatcher rqDis = request.getRequestDispatcher("view/search/index.jsp");
			rqDis.forward(request, response);

			// here pass the data to jsp pages create view files as per structure

		}
//		if(request.getHeader("Accept") != null);
//		request.setAttribute("buses", buses);

	}

}

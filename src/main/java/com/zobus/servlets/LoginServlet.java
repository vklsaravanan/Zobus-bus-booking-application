package com.zobus.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zobus.database.UsersDAO;
import com.zobus.helper.URLManager;
import com.zobus.model.UsersModel;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static int pwdSaltValue = 10;

	private static final long serialVersionUID = 1L;

	/**
	 * 8r16sdhq
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getUserPrincipal() != null) {
            // User is already authenticated, redirect to the default or dashboard page
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }

		HttpSession session = request.getSession();
		String redirect = request.getRequestURI();
		session.setAttribute("redirectTo", redirect);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/auth/login/index.jsp");
		dispatcher.forward(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		response.sendRedirect(getServletInfo()+"");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String redirectTo = (String) request.getSession().getAttribute("redirectTo");
		boolean remember = request.getParameter("remember") != null;
		System.out.println(redirectTo);
		request.getSession().removeAttribute("redirectTo");
		UsersModel user = null;
		HttpSession session = request.getSession();
		try {
			user = UsersDAO.authendicateUser(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// if user is authendicated successfully
		if (user != null) {
			if (remember) {
				Cookie newCookie = new Cookie("__LINT", user.getLoginToken());
				newCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(newCookie);
				response.sendRedirect(redirectTo);

			} else {
				request.getSession().setAttribute("__LINT", user.getLoginToken());
			}
			session.setAttribute("user_name", user.getName());
		} else {
			session.setAttribute("error", "bad login credentials");
			session.setAttribute("username", username);
			response.sendRedirect(URLManager.getFullURL(request));
		}
	}
}

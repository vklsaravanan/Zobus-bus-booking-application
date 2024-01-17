package com.zobus.middleware;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import com.zobus.database.UsersDAO;
import com.zobus.helper.CookieManager;
import com.zobus.model.UsersModel;

@WebFilter(urlPatterns = { "/*" }, filterName = "filter002AuthendicationCheck")
public class AuthendicationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
			throws IOException, ServletException {
		// type cast for getting cookies
		HttpServletRequest req = (HttpServletRequest) request;

		CookieManager cookies = new CookieManager();
		cookies.processCookies(req);

		String loginTokenCookie = cookies.getCookieValue("__LINT");
		
		String loginTokenSession = (String) req.getSession().getAttribute("__LINT");

		// checking is user is authendicated or not
		if (loginTokenCookie != null || loginTokenSession != null) {
			String loginToken = loginTokenCookie != null ? loginTokenCookie : loginTokenSession;

			UsersModel user = UsersDAO.authendicateUserWithToken(loginToken);
			if (user != null) {
				req.getSession().setAttribute("user_name", user.getName());

			} else {
				req.getSession().removeAttribute("user_name");
			}
		} else {

		}

		arg2.doFilter(req, response); 
	}
}

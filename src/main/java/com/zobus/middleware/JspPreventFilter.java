package com.zobus.middleware;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class JspPreventFilter
 */
@WebFilter(urlPatterns = {"*.jsp", "/"})

public class JspPreventFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public JspPreventFilter() {
        super();
        
    }
 
	/**
	 * @see Filter#destroy()
	 */  
	public void destroy() {
		// TODO Auto-generated method stub
	} 
 
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		 if ("/zobus/".equals(httpRequest.getRequestURI())) {
	            chain.doFilter(request, response);
	            return;
	        }
        
		// place your code here
		System.out.println(httpRequest.getRequestURI()); 
		
		// pass the request along the filter chain
		request.getRequestDispatcher("/view/errorPag404e.jsp").forward(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

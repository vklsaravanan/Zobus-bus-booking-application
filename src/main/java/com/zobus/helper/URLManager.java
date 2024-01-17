package com.zobus.helper;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class URLManager {
	/*
	 * if request need getfull URL with query string use this function
	 * @return like Https://example.com/project?name=saravanan 
	 */
	public static String getFullURL(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }
	/*
	 * overloaded function
	 */
	public static String getFullURL(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
        StringBuffer requestURL = req.getRequestURL();
        String queryString = req.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }
}

package com.zobus.helper;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieManager {
    private HashMap<String, String> cookieMap = new HashMap<>();

    public void processCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
    }

    public String getCookieValue(String cookieName) {
        return cookieMap.get(cookieName);
    }
    
    /*
     * Using for find Cookie is older or not 
     * for refreshing the token 
     * using at LoggingFilter 
     */
    public boolean isCookieTwoDaysOld(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    long cookieTimestamp = cookie.getMaxAge()*1000;
                    System.out.println(cookieTimestamp);
                    long currentTimestamp = System.currentTimeMillis();
                    long twoDaysInMillis = 2 * 24 * 60 * 60 * 1000; // Two days in milliseconds

                    return currentTimestamp - cookieTimestamp >= twoDaysInMillis;
                }
            }
        }
        
        // Cookie not found or no timestamp information
        return false;
    }
}
package com.zobus.helper;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.net.URLDecoder;

public class QueryHelper {
	
	public static HashMap<String, String> parseQueryString(String queryString) {
		HashMap<String, String> queryParams = new HashMap<>();

        if (queryString != null && !queryString.isEmpty()) {
            String[] pairs = queryString.split("&");

            for (String pair : pairs) {
                String[] keyValue = pair.split("=");

                try {
                    String key = URLDecoder.decode(keyValue[0], "UTF-8");
                    String value = (keyValue.length > 1) ? URLDecoder.decode(keyValue[1], "UTF-8") : null;

                    queryParams.put(key, value);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    // Handle the exception as needed
                }
            }
        }

        return queryParams;
    }
	
}
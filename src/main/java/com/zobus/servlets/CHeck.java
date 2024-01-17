package com.zobus.servlets;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CHeck {
	int a = 0;

	public static void main(String[] args) {
		Integer[] a = { 5, 2, 4, 4, 6, 7};
		
		List b = Arrays.asList(a);
		

		for(int i = 1 ;i <= 100; i++) {
			if (!b.contains(i)) {
				System.out.println("\nnot contain: "+i);
			}
		}
	}

}

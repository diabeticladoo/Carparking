package com.carpark.billing.model;

import java.util.Random;

public class Link {
	public static String generate(int len) {
		Random r = new Random();
		String link = "";
		for(int i = 0; i < len; i++) {
			char c = (char) (r.nextInt(26)+'a');
			link = link + c;
		}
		return link;
	}
}

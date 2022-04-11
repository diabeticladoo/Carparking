package com.carpark.helper;

import java.util.regex.*;

public class DateTime {
	public static String dateFromDT(String DT) {
		Pattern pat = Pattern.compile("([\\d-]+)[\\sT]\\d+:\\d+");
		Matcher m = pat.matcher(DT);
		m.find();
		return m.group(1);
	}
}

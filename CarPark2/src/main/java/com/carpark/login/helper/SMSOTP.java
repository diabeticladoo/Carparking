package com.carpark.login.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
 
public class SMSOTP {
	public String sendSms(String mobno, String msg) {
		try {
			// Construct data
			String apiKey = "apikey=" + "NTM0ZTU2NjM1NjczNjk0OTVhNjI2OTRiNjM3MjZmNGU=";
			String message = "&message=" + "This is you message";
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "9717732802";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}
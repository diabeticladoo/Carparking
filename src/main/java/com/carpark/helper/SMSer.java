package com.carpark.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class SMSer {
	private String to,msg;
	final String apiKey = "5VR7LjcNM0arY2dxfZGQO3JKDnhi9u4vEsp6FmAPlUT1zIC8wWZk1aMhc0YlBJyVSXIbQjgsPR85eO7U";
	public SMSer(String To, String Msg) {
		to = To;
		msg = Msg;
	}

	public void send() {
		try {
			String sendId = "TXTIND";
			String route = "v3";

			String message = URLEncoder.encode(msg, "UTF-8");

			String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization=" + apiKey + "&sender_id=" + sendId
					+ "&message=" + message + "&route=" + route + "&numbers=" + to;

			URL url = new URL(myUrl);

			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");

			//int responseCode = con.getResponseCode();

			StringBuffer response = new StringBuffer();

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while (true) {
				String line = br.readLine();

				if (line == null) {
					break;
				}

				response.append(line);
			}

			 System.out.println("SENT SMS");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

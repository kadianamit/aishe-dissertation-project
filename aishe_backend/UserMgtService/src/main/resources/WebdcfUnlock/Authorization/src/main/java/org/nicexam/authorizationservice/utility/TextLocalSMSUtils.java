package org.nicexam.authorizationservice.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TextLocalSMSUtils {

	public static String sendSingleSMSTextLocal(String apiKeyValue, String senderId, String mobileNumber,
			String textmessage) {
		try {
			String apiKey = "apikey=" + apiKeyValue;
			String message = "&message=" + textmessage;
			String sender = "&sender=" + senderId;
			String numbers = "&numbers=" + mobileNumber;

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			System.out.println(data);
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
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}
//	public static void main(String arg[]) {
//		System.out.println(sendSingleSMSTextLocal("", "", "", "", "", "",""));
//	}
}

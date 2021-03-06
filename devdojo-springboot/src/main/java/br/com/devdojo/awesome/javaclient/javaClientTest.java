package br.com.devdojo.awesome.javaclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class javaClientTest {
	public static void main(String[] args) {
		HttpURLConnection connection = null;
		BufferedReader reader = null;

		try {
			URL url = new URL("http://localhost:8081/v1/protected/students/1");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Authorization", "Basic " + encodeUsernamePassword("cledson", "devdojo"));
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	private static String encodeUsernamePassword(String user, String password) {
		String userPassword = user + ":" + password;

		return new String(Base64.encodeBase64(userPassword.getBytes()));
	}
}

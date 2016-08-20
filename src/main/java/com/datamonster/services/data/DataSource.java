package com.datamonster.services.data;

import java.io.File;
import java.util.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.UnsupportedEncodingException;


public class DataSource {
	private String dbServiceURL="http://localhost:3000";
	private final String USER_AGENT = "Mozilla/5.0";
	public void insertFileInDb(String productUrl, String version, int statusCode, File newFile) {
		String urlID = UUID.nameUUIDFromBytes(productUrl.getBytes()).toString();

		try{
		dbServiceURL=dbServiceURL+"/addIndex?searchurl="+urlID+"&filename="+newFile.getName().toString().split("-")[0]+"&timestamp="+version;
		System.out.println(dbServiceURL);
		URL obj = new URL(dbServiceURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");


		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + dbServiceURL);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		if(response.toString().equals("Success")){
			System.out.println("SuccessFully Added Index!!");
		}else{
			System.out.println("Error While Adding Index!!");
		}
		

		}catch(IOException ef){
			ef.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}
	}

}

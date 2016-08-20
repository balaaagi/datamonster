package com.datamonster.finder;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import static com.datamonster.utils.DataMonsterQueryConstants.DB_SERVICE_URL;
import static com.datamonster.utils.DataMonsterQueryConstants.USER_AGENT;

public class Search{
	private String dbServiceURL=DB_SERVICE_URL;
	private String firstHTML="<html> " + "<title>" + "File Search Response" + "</title><body><h2>File Search Results</h2>";
    private String lastHTML="</body></html>";
    // private String sampleFileResults="<b><a href=\""+fileName+"\">"+fileName +"</a></b>";
    private String fileResults="No Files Found";
	
	// public String searchURL(String url){
	// 	String urls;
	// // // 	//TO DO
	// // 	try{
	// // 		urls=URLEncoder.encode(url, "UTF-8");
	// // 	// urls=java.net.URLDecoder.encode(url, "UTF-8");	
	// // }catch(UnsupportedEncodingException e){
	// // 	urls=url;
	// // }
	// 	String urlID = UUID.nameUUIDFromBytes(url.getBytes()).toString();
	// 	try{
	// 	dbServiceURL=dbServiceURL+"/findFileNames?searchurl="+urlID;
	// 	System.out.println(dbServiceURL);
	// 	URL obj = new URL(dbServiceURL);
	// 	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	// 	con.setRequestMethod("GET");


	// 	con.setRequestProperty("User-Agent", USER_AGENT);

	// 	int responseCode = con.getResponseCode();
	// 	System.out.println("\nSending 'GET' request to URL : " + url);
	// 	System.out.println("Response Code : " + responseCode);

	// 	BufferedReader in = new BufferedReader(
	// 	        new InputStreamReader(con.getInputStream()));
	// 	String inputLine;
	// 	StringBuffer response = new StringBuffer();

	// 	while ((inputLine = in.readLine()) != null) {
	// 		response.append(inputLine);
	// 	}
	// 	in.close();

	// 	//print result
	// 	// System.out.println(response.toString());
	// 	ResultsPopulator res=new ResultsPopulator();
	// 	fileResults=res.populateResultforSearchUrl(response.toString());


	// 	}catch(IOException ef){
	// 		ef.printStackTrace();
	// 	}catch(Exception e){

	// 		e.printStackTrace();
	// 	}

	// 	return firstHTML+fileResults+"<br>"+urlID+"<br>"+url+lastHTML;
	// }


	public String searchURLWithLimits(String url,String limits){
		String urls;
	// // 	//TO DO
	// 	try{
	// 		urls=URLEncoder.encode(url, "UTF-8");
	// 	// urls=java.net.URLDecoder.encode(url, "UTF-8");	
	// }catch(UnsupportedEncodingException e){
	// 	urls=url;
	// }
		String urlID = UUID.nameUUIDFromBytes(url.getBytes()).toString();
		try{
			if(limits!=null)
				dbServiceURL=dbServiceURL+"/findFileNamesWithLimits?searchurl="+urlID+"&limitTo="+limits;
			else
				dbServiceURL=dbServiceURL+"/findFileNames?searchurl="+urlID;
		System.out.println(dbServiceURL);
		URL obj = new URL(dbServiceURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");


		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		// System.out.println(response.toString());
		ResultsPopulator res=new ResultsPopulator();
		fileResults=res.populateResultforSearchUrl(response.toString());


		}catch(IOException ef){
			ef.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}

		return firstHTML+fileResults+"<br>"+urlID+"<br>"+url+lastHTML;
	}
	public String searchRawVersion(String url,String version){
		
			String urls;
		
		String urlID = UUID.nameUUIDFromBytes(url.getBytes()).toString();
		
		try{
		dbServiceURL=dbServiceURL+"/findFileNamesByVersion?searchurl="+urlID+"&version="+version;
		System.out.println(dbServiceURL);
		URL obj = new URL(dbServiceURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");


		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		ResultsPopulator res=new ResultsPopulator();
		fileResults=res.populateResultforSearchRawUrl(response.toString());


		}catch(IOException ef){
			ef.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}
		return fileResults;
	}

}
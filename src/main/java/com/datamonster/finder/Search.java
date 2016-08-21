package com.datamonster.finder;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static com.datamonster.utils.DataMonsterQueryConstants.DB_SERVICE_URL;
import static com.datamonster.utils.DataMonsterQueryConstants.USER_AGENT;

public class Search{
	private String dbServiceURL=DB_SERVICE_URL;
	private String firstHTML="<html> " + "<title>" + "File Search Response" + "</title><body><h2>File Search Results</h2>";
    private String lastHTML="</body></html>";
    // private String sampleFileResults="<b><a href=\""+fileName+"\">"+fileName +"</a></b>";
    private String fileResults="No Files Found";
	
	public String searchURLWithLimits(String url,String limits){

		//print result
		// System.out.println(response.toString());
		try {
			StringBuffer response = getSearchResultsFromDb(url,limits);
			ResultsPopulator res=new ResultsPopulator();
			fileResults=res.populateResultforSearchUrl(response.toString());

		}catch(Exception e){

			e.printStackTrace();
		}

		return firstHTML+fileResults+lastHTML;
	}
	private StringBuffer getSearchResultsFromDb(String url, String limits) throws IOException {
		String urls;

		String urlID = UUID.nameUUIDFromBytes(url.getBytes()).toString();

		if(limits!=null)
			dbServiceURL=dbServiceURL+"/findFileNamesWithLimits?searchurl="+urlID+"&limitTo="+limits;
		else
			dbServiceURL=dbServiceURL+"/findFileNames?searchurl="+urlID;
		
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

		return response;
	}
	public String searchRawVersion(String url,String version){
		
			String urls;
		
		String urlID = UUID.nameUUIDFromBytes(url.getBytes()).toString();
		
		try{
		dbServiceURL=dbServiceURL+"/findFileNamesByVersion?searchurl="+urlID+"&version="+version;
		
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

		ResultsPopulator res=new ResultsPopulator();
		fileResults=res.populateResultforSearchRawUrl(response.toString());


		}catch(IOException ef){
			ef.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}
		return fileResults;
	}

	public String purgeAndKeep(String url,String toKeep){
		String urls;
		System.out.println("Coming for purge");
		String urlID = UUID.nameUUIDFromBytes(url.getBytes()).toString();
		try{
			dbServiceURL=dbServiceURL+"/findFileNamesForPurge?searchurl="+urlID+"&toKeep="+toKeep;
		
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

		//print result
		// System.out.println(response.toString());
		ResultsPopulator res=new ResultsPopulator();
		System.out.println("Going inside Results");
		fileResults=res.purgeAndKeep(response.toString(),toKeep);


		}catch(IOException ef){
			ef.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}
		return fileResults;
	}

}
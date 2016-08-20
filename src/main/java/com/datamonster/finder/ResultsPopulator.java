package com.datamonster.finder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import static com.datamonster.utils.DataMonsterQueryConstants.WATCH_FOLDER;


public class ResultsPopulator{

	public String populateResultforSearchUrl(String responseString){
		JSONParser parser=new JSONParser();
		JSONObject resultsObject=null;
		try{

			resultsObject=(JSONObject) parser.parse(responseString);	
		}catch(ParseException e){
			e.printStackTrace();
		}
		String output="<table><tr><th>FileName</th><th>Timestamp</th></tr>";
		

		JSONArray resultArray=(JSONArray)resultsObject.get("results");
		

            for(int i=0;i<resultArray.size();i++){
 			output=output+"<tr>";

            JSONObject singleFileDetails=(JSONObject)resultArray.get(i);
			System.out.println((String)singleFileDetails.get("url"));
			System.out.println((String)singleFileDetails.get("filename"));
			System.out.println(singleFileDetails.get("timestamp").toString());
			output=output+"<td>"+singleFileDetails.get("filename").toString()+"-"+singleFileDetails.get("timestamp").toString()+".html"+"</td><td>"+singleFileDetails.get("timestamp").toString()+"</td>";
			output=output+"</tr>";        
                
            }
		return output;
		
	}

	public String populateResultforSearchRawUrl(String responseString){
		JSONParser parser=new JSONParser();
		JSONObject resultsObject=null;
		try{

			resultsObject=(JSONObject) parser.parse(responseString);	
		}catch(ParseException e){
			e.printStackTrace();
		}
		String output=null;
		

		JSONArray resultArray=(JSONArray)resultsObject.get("results");
		

            
 			

            JSONObject singleFileDetails=(JSONObject)resultArray.get(0);
            String outputFileName=singleFileDetails.get("filename").toString()+"-"+singleFileDetails.get("timestamp").toString()+".html";
			// System.out.println((String)singleFileDetails.get("url"));
			// System.out.println((String)singleFileDetails.get("filename"));
			// System.out.println(singleFileDetails.get("timestamp").toString());
			// output=output+"<td>"+singleFileDetails.get("filename").toString()+"-"+singleFileDetails.get("timestamp").toString()+".html"+"</td><td>"+singleFileDetails.get("timestamp").toString()+"</td>";
			// output=output+"</tr>";        
            							        
            StringBuilder contentBuilder = new StringBuilder();
			try {
    		BufferedReader in = new BufferedReader(new FileReader(WATCH_FOLDER+"/"+outputFileName));
    			// 1eae077003ac2e788ddb4d91e5c69fcf-1470547058547.html"));
			    String str=in.readLine();
			    while ((str = in.readLine()) != null) {
			        contentBuilder.append(str);
			 	}
			    in.close();
			} catch (IOException e) {
				output="Error While Reading the html File Content";
			}
			output =contentBuilder.toString();
			// System.out.println(output);
		return output;
		
	}
	
}
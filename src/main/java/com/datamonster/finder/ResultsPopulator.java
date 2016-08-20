package com.datamonster.finder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
}
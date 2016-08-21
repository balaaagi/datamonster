package com.datamonster.finder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.lang.Integer;
import com.datamonster.filelistener.FileWatcher;
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
		      
            							        
            StringBuilder contentBuilder = new StringBuilder();
			try {
    		BufferedReader in = new BufferedReader(new FileReader(WATCH_FOLDER+"/"+outputFileName));
    			
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

	public String purgeAndKeep(String responseString,String toKeep){
		JSONParser parser=new JSONParser();
		JSONObject resultsObject=null;
		try{

			resultsObject=(JSONObject) parser.parse(responseString);	
		}catch(ParseException e){
			e.printStackTrace();
		}
		String output="<b>Purged Files</b><br><table><tr><th>FileName</th><th>Timestamp</th></tr>";
		

		JSONArray resultArray=(JSONArray)resultsObject.get("results");
		
		// FileWatcher purgeFileWatcher=new FileWatcher();
		int sizeOfResults=resultArray.size();
		System.out.println("Size"+sizeOfResults);
        for(int i=sizeOfResults-Integer.parseInt(toKeep);i<sizeOfResults;i++){
 			

            JSONObject singleFileDetails=(JSONObject)resultArray.get(i);
            String fileName=singleFileDetails.get("filename").toString()+"-"+singleFileDetails.get("timestamp").toString()+".html";
			System.out.println("The file that is to be deleted.."+fileName);
			File toBePurgedFile=new File(WATCH_FOLDER+"/"+fileName);
			
            if(toBePurgedFile.delete()){
            	output=output+"<tr>";
            	output=output+"<td>"+fileName+"</td><td>"+singleFileDetails.get("timestamp").toString()+"</td>";
				output=output+"</tr>";        	
             }else{
             	output=output+"<tr>";
            	output=output+"<td>"+fileName+"</td><td>Not Delete"+"</td>";
				output=output+"</tr>";        	
             }    
            }
		return output;
		
	}
	
}
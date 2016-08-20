package com.datamonster.finder;

public class Search{
	private String firstHTML="<html> " + "<title>" + "File Search Response" + "</title><body><h2>File Search Results</h2>";
    private String lastHTML="</body></html>";
    // private String sampleFileResults="<b><a href=\""+fileName+"\">"+fileName +"</a></b>";
    private String fileResults="No Files Found";
	
	public String searchURL(String url){
		//TO DO
		return firstHTML+fileResults+lastHTML;
	}

	public String searchRawVersion(String url,String version){
		// TO DO
		return firstHTML+fileResults+lastHTML;
	}

}
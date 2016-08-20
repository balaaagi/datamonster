package com.datamonster.services.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileParser {

	private String productUrl;
	private String version;
	private int status;

	public FileParser(File newFile) {
		BufferedReader reader = null;
		try {
			FileInputStream fileStream = new FileInputStream(newFile);
			reader = new BufferedReader(new InputStreamReader(fileStream));
			String line = reader.readLine();
			String[] fileInformation = line.split("\\s");
			this.productUrl = fileInformation[0];
			this.version = fileInformation[1];
			this.status = Integer.parseInt(fileInformation[2]);
			fileStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getproductUrl() {
		return this.productUrl;
	}

	public String getVersion() {
		return this.version;
	}

	public int getStatusCode() {
		return this.status;
	}

}

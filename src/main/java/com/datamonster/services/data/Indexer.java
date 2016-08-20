package com.datamonster.services.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Indexer {
	private static String PROCESSED_FOLDER = "/Users/balaaagi/Devlogs/DataMonster/Processed";

	public void persistFile(File newFile, String productUrl,
			String version, int statusCode) {
		File processedfile = null;
		FileInputStream fileStream = null;
		
		try {
			fileStream = new FileInputStream(newFile);
			processedfile = new File(PROCESSED_FOLDER + "/"+ newFile.getName());
			// writeStreamToFile(fileStream, processedfile);
			
			DataSource mongoDb = new DataSource();
			mongoDb.insertFileInDb(productUrl, version, statusCode, newFile);
			fileStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileStream != null) {
				try {
					fileStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void writeStreamToFile(FileInputStream fileStream, File processedfile) {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(processedfile);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = fileStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

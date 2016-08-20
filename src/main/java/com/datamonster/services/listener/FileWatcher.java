package com.datamonster.services.listener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import com.datamonster.services.data.Indexer;

public class FileWatcher {

	public static final String REMOTE_FOLDER = "C:\\Users\\User\\Desktop\\incomingFolder";
	public String listenerActive="Y";
	
	public void startListening() {
		Path watchDirectory = Paths.get(REMOTE_FOLDER);
//		this.listenerActive = "Y";
		
		while(listenerActive.equals("Y")) {
			try {
				WatchService watcher = watchDirectory.getFileSystem().newWatchService();
				watchDirectory.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
				WatchKey watchKey = watcher.take();
				
				List<WatchEvent<?>> events = watchKey.pollEvents();
				for (WatchEvent event : events) {
					if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
						File incomingFile = new File(REMOTE_FOLDER+"\\"+event.context().toString());
						FileParser fileParser = new FileParser(incomingFile);
						Indexer indexer = new Indexer();
						indexer.persistFile(incomingFile, fileParser.getproductUrl(), fileParser.getVersion(), fileParser.getStatusCode());
						incomingFile.delete();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stopListening() {
		this.listenerActive = "N";
	}
}

package com.project.desktopsearchengine;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;

import com.project.desktopsearchengine.exceptions.InvalidPropertyException;
import com.project.desktopsearchengine.files.FileHandler;
import com.project.desktopsearchengine.utilities.Utilities;

public class MainHandler {

	
	public void initApplication(){
		
		long start = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(50);
		String extension;
		try {
			Utilities.initializeConfigurations("." + Utilities.SEPARATOR + "config" + Utilities.SEPARATOR + "config.properties");
		} catch (InvalidPropertyException e) {
			e.printStackTrace();
		}

		if(Utilities.parentFolders != null){
			for(String parentFolder : Utilities.parentFolders){

				ArrayList<String> selectedFiles = Utilities.getAllFilesWithExtensions(parentFolder);
				for(String f : selectedFiles){
					
//					Runnable worker = new WorkerThread(f);
//		            executor.execute(worker);
		            
					extension = FilenameUtils.getExtension(f);
					extension = extension.toUpperCase(); // No need to check for null, we are already filtering files on given extensions

					try {
						Class clazz = Class.forName(Utilities.FILES_PACKAGE+extension);
						FileHandler file = (FileHandler)clazz.newInstance();
						System.out.println(file.readFileAndGetWords(f));

					} 
					catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
						Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
					}
					
				}//end of for loop


			}//end of for loop
		}

//		executor.shutdown();
//		while (!executor.isTerminated()) {
//        }
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time taken: " + (end-start)/1000);
	}
}

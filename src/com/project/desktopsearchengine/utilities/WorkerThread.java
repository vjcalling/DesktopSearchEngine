package com.project.desktopsearchengine.utilities;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;

import com.project.desktopsearchengine.MainHandler;
import com.project.desktopsearchengine.files.FileHandler;

public class WorkerThread implements Runnable{

	private String currentFile;
	
	public WorkerThread(String currentFile){
		this.currentFile = currentFile;
	}
	
	@Override
	public void run() {

		String extension;
//		ArrayList<String> selectedFiles = Utilities.getAllFilesWithExtensions(parentFolder);
//		for(String f : selectedFiles){
			extension = FilenameUtils.getExtension(currentFile);
			extension = extension.toUpperCase(); // No need to check for null, we are already filtering files on given extensions

			try {
				Class clazz = Class.forName(Utilities.FILES_PACKAGE+extension);
				FileHandler file = (FileHandler)clazz.newInstance();
				System.out.println(file.readFileAndGetWords(currentFile));

			} 
			catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
				Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
			}
		


		
	}

}

package com.project.desktopsearchengine;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;

import com.project.desktopsearchengine.files.FileHandler;
import com.project.desktopsearchengine.utilities.Utilities;

public class OperationsHandler {

	public void addFilesUnderFolderForSearching(String folderPath){
		String extension;
		ArrayList<String> selectedFiles = Utilities.getAllFilesWithExtensions(folderPath);
		for(String f : selectedFiles){
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

		}
	}

	public void searchQuery(String query) {
		
		System.out.println("Searching: "+query);
		
	}
}

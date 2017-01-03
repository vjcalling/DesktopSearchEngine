package com.project.desktopsearchengine.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

/*
 * Class to handle all utilities
 */
public class Utilities {

	public static ArrayList<String> fileNamesSelected = new ArrayList<String>();
	public static List<String> allowedExtensions = new ArrayList<String>();
	public static List<String> parentFolders = new ArrayList<String>();
	public static HashSet <String> stopWords = new HashSet <String>();

	//-------------------------------------------------------------------------------------------
	
	public static void initializeConfigurations(String configPropertiesFile){
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(configPropertiesFile);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			Utilities.allowedExtensions = Arrays.asList(prop.getProperty("EXTENSIONS").split(","));
			Utilities.parentFolders = Arrays.asList(prop.getProperty("PARENT_FOLDERS").split(","));
			Utilities.stopWords = new HashSet<String>(Arrays.asList(prop.getProperty("STOPWORDS").split(",")));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//-------------------------------------------------------------------------------------------
	
	public static ArrayList<String> getAllFilesWithExtensions(String parentFolder){

		File folder = new File(parentFolder);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if ( (listOfFiles[i].isFile()) && (Utilities.allowedExtensions.contains(getExtension(listOfFiles[i].getName())) )) {
				fileNamesSelected.add(listOfFiles[i].getAbsolutePath());
			} else if (listOfFiles[i].isDirectory()) {
				getAllFilesWithExtensions(listOfFiles[i].getAbsolutePath()); //calling same method recursively in case of sub directory
			}
		}
		return fileNamesSelected;
	
	}
	
	//-------------------------------------------------------------------------------------------
	
	public static String getExtension(String fileName){
		
		String extension = null;
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		return extension;
	}
	
//-------------------------------------------------------------------------------------------

	
}

package com.project.desktopsearchengine.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.project.desktopsearchengine.exceptions.InvalidPropertyException;

/*
 * Class to handle all utilities
 */
public class Utilities {

	public static final String SEPARATOR = File.separator;
	public static final String FILES_PACKAGE = "com.project.desktopsearchengine.files.";
	public static List<String> allowedExtensions = new ArrayList<String>();
	public static List<String> parentFolders = new ArrayList<String>();
	public static HashSet <String> stopWords = new HashSet <String>();

	//-------------------------------------------------------------------------------------------

	public static void initializeConfigurations(String configPropertiesFile) throws InvalidPropertyException{

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(configPropertiesFile);

			// load a properties file
			prop.load(input);
			Utilities.allowedExtensions = prop.getProperty("EXTENSIONS") != null ? Arrays.asList(prop.getProperty("EXTENSIONS").split(",")) : null;
			Utilities.parentFolders = prop.getProperty("PARENT_FOLDERS") != null ? Arrays.asList(prop.getProperty("PARENT_FOLDERS").split(",")) : null;
			Utilities.stopWords = prop.getProperty("STOPWORDS") != null ? new HashSet<String>(Arrays.asList(prop.getProperty("STOPWORDS").split(","))) : null;

			if(Utilities.allowedExtensions == null)
				throw new InvalidPropertyException("Extensions missing from config file");

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

	public static void getAllFilesWithExtensions(String parentFolder, ArrayList<String> selectedFiles){

		File folder = new File(parentFolder);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if ( (listOfFiles[i].isFile()) && (Utilities.allowedExtensions != null && Utilities.allowedExtensions.contains(getExtension(listOfFiles[i].getName())) )) {
				selectedFiles.add(listOfFiles[i].getAbsolutePath());
			} else if (listOfFiles[i].isDirectory()) {
				getAllFilesWithExtensions(listOfFiles[i].getAbsolutePath(),selectedFiles); //calling same method recursively in case of sub directory
			}
		}

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

	public void populateWordCountHashMap(LinkedList<String> filteredWords, HashMap<String,Integer> mymap){

		int count;
		for(String word : filteredWords){
			if(mymap.containsKey(word)){
				count = mymap.get(word);
				count++;
				mymap.put(word, count);
			}else{
				mymap.put(word, 1);
			}
		}
	}

	//-------------------------------------------------------------------------------------------

}

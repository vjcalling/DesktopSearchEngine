package com.project.desktopsearchengine.files;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class FileHandler {

	public static Set<File> foldersAdded = new HashSet<File>();

	//-----------------------------------------------------------------------------------
	
	public abstract HashMap<String, Integer> readFileAndGetWords(String filePath);

	//-----------------------------------------------------------------------------------
		
	static boolean checkForSubFolder(File maybeChild, File possibleParent)
	{
	    URI parentURI = possibleParent.toURI();
	    URI childURI = maybeChild.toURI();
	    return !parentURI.relativize(childURI).isAbsolute();
	}
	
//-----------------------------------------------------------------------------------
	
	public static boolean isFolderAlreadyAdded(String folderPath){

		System.out.println("Checking for folder: "+folderPath);
		
		File folder = new File(folderPath);
		if(FileHandler.foldersAdded.contains(folder)){
			return true;
		}
		
		Iterator<File> it = FileHandler.foldersAdded.iterator();
		while(it.hasNext()){
			if(checkForSubFolder(folder, (File)it.next())){
				return true;
			}
		}
		
		
		return false;
	}
}

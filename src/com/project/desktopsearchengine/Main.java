package com.project.desktopsearchengine;

import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.project.desktopsearchengine.exceptions.InvalidPropertyException;
import com.project.desktopsearchengine.utilities.Utilities;

public class Main {
	
	public static void main(String[] args) {

		try {
			Utilities.initializeConfigurations("."+Utilities.SEPARATOR+"config"+Utilities.SEPARATOR+"config.properties");
		} catch (InvalidPropertyException e) {
			e.printStackTrace();
		}
		System.out.println(Utilities.stopWords);
		
		if(Utilities.parentFolders != null){
			for(String parentFolder : Utilities.parentFolders){
				ArrayList<String> selectedFiles = Utilities.getAllFilesWithExtensions(parentFolder);
				System.out.println(selectedFiles.size());
			}
		}

	}

}

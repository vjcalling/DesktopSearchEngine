package com.project.desktopsearchengine;

import java.util.ArrayList;

import com.project.desktopsearchengine.exceptions.InvalidPropertyException;
import com.project.desktopsearchengine.utilities.Utilities;

public class MainHandler {

	public void initApplication(){
		
		try {
			Utilities.initializeConfigurations("." + Utilities.SEPARATOR + "config" + Utilities.SEPARATOR + "config.properties");
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

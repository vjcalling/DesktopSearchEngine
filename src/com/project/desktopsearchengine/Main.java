package com.project.desktopsearchengine;

import java.util.ArrayList;

import com.project.desktopsearchengine.utilities.Utilities;

public class Main {

	public static void main(String[] args) {
		
		Utilities.initializeConfigurations(".\\config\\config.properties");
		System.out.println(Utilities.stopWords);
		for(String parentFolder : Utilities.parentFolders){
			ArrayList<String> selectedFiles = Utilities.getAllFilesWithExtensions(parentFolder);
			System.out.println(selectedFiles.size());
		}
		

	}

}

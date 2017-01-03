package com.project.desktopsearchengine;

import java.util.ArrayList;
import java.util.Arrays;

import com.project.desktopsearchengine.utilities.Utilities;

public class Main {

	public static void main(String[] args) {
		
		Utilities.initializeConfigurations(".\\config\\config.properties");
		
		for(String parentFolder : Utilities.parentFolders){
			ArrayList<String> selectedFiles = Utilities.getAllFilesWithExtensions(parentFolder);
			System.out.println(selectedFiles.size());
		}
		

	}

}

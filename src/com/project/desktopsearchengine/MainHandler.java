package com.project.desktopsearchengine;

import java.io.IOException;

import com.project.desktopsearchengine.exceptions.InvalidPropertyException;
import com.project.desktopsearchengine.files.PDF;
import com.project.desktopsearchengine.utilities.Utilities;

public class MainHandler {

	public void initApplication(){

		try {
			Utilities.initializeConfigurations("." + Utilities.SEPARATOR + "config" + Utilities.SEPARATOR + "config.properties");
		} catch (InvalidPropertyException e) {
			e.printStackTrace();
		}

		//		if(Utilities.parentFolders != null){
		//			for(String parentFolder : Utilities.parentFolders){
		//				ArrayList<String> selectedFiles = Utilities.getAllFilesWithExtensions(parentFolder);
		//				
		//				
		//				
		//			}
		//		}

		//		CSV csvReader = new CSV();
		//		System.out.println(csvReader.readFileAndGetWords("C:\\Users\\vibhjain\\Desktop\\CISCO_DOCS\\TRAINING\\GITHUB\\DesktopSearchEngine\\sample_data\\sports.csv"));

		PDF pdfReader = new PDF();
		System.out.println(pdfReader.readFileAndGetWords("C:\\Users\\vibhjain\\Desktop\\CISCO_DOCS\\TRAINING\\GITHUB\\DesktopSearchEngine\\sample_data\\helloWorld.pdf"));
		

	}
}

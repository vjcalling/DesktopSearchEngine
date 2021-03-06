package com.project.desktopsearchengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.project.desktopsearchengine.exceptions.InvalidPropertyException;
import com.project.desktopsearchengine.files.FileHandler;
import com.project.desktopsearchengine.index.FileWeightage;
import com.project.desktopsearchengine.index.IndexHandler;
import com.project.desktopsearchengine.index.NormalIndex;
import com.project.desktopsearchengine.utilities.Utilities;

public class MainHandler {


	OperationsHandler operationsHandler = new OperationsHandler();

	public void initApplication(){

		String folderPath = null;
		String query = null;

		try {
			Utilities.initializeConfigurations("." + Utilities.SEPARATOR + "config" + Utilities.SEPARATOR + "config.properties");
			IndexHandler.loadIndices();
		} catch (InvalidPropertyException e) {
			e.printStackTrace();
		}


		System.out.println("======================");
		System.out.println("Press 1 to add a folder");
		System.out.println("Press 2 for search");
		System.out.println("Press 3 to check files added");
		System.out.println("Press 4 to check folders added");
		System.out.println("Press 0 to exit");
		System.out.println("======================\n\n");


		int choice;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a number: ");
		choice = reader.nextInt(); // Scans the next token of the input as an int.

		try{
			while(choice != 0){

				switch (choice) {

				//logic for adding files under folder
				case 1:
					System.out.println("Enter folder path you want to index: ");
					try {
						folderPath = br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}

					if(FileHandler.isFolderAlreadyAdded(folderPath)){
						System.out.println("Folder is already added, skipping...");
						break;
					}
					operationsHandler.addFilesUnderFolderForSearching(folderPath);

					System.out.println("------------------------------------------------------------");

					break;


					//logic for searching
				case 2:
					System.out.println("Enter query string: ");
					try {
						query = br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Query to be searched: "+query);
					List<File> results = operationsHandler.searchQuery(query);

					if(results == null)
						System.out.println("No records found");
					else{
						System.out.println("Result:");
						System.out.println("------");
						for(File f : results)
							System.out.println(f.getCanonicalPath());
					}

				System.out.println("------------------------------------------------------------");

				break;

				//logic for printing normal index
			case 3:
				System.out.println("Files Added:");
				Iterator it = NormalIndex.fileNumToNameMap.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					System.out.println(pair.getKey() + " = " + pair.getValue().toString());
				}

				System.out.println("------------------------------------------------------------");

				break;

			case 4:
				System.out.println("Folders Added:");
				for(File folder : FileHandler.foldersAdded){
					System.out.println(folder.getCanonicalPath());
				}

				System.out.println("------------------------------------------------------------");

				break;

			default:
				System.out.println("Invalid choice. Choose from available options");
				System.out.println("------------------------------------------------------------");
				break;
			}

			System.out.println("======================");
			System.out.println("Press 1 to add a folder");
			System.out.println("Press 2 for search");
			System.out.println("Press 3 to check files added");
			System.out.println("Press 4 to check folders added");
			System.out.println("Press 0 to exit");
			System.out.println("======================\n\n");
			System.out.println("Enter a number: ");
			choice = reader.nextInt(); // Scans the next token of the input as an int.

		} //end of while


	}catch(Exception e){
		e.printStackTrace();
	}finally{

		IndexHandler.persistIndices();
	}


}
}

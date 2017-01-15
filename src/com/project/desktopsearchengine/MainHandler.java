package com.project.desktopsearchengine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.project.desktopsearchengine.exceptions.InvalidPropertyException;
import com.project.desktopsearchengine.index.InvertedIndex;
import com.project.desktopsearchengine.index.NormalIndex;
import com.project.desktopsearchengine.utilities.Utilities;

public class MainHandler {


	OperationsHandler operationsHandler = new OperationsHandler();

	public void initApplication(){

		String folderPath = null;
		String query = null;

		try {
			Utilities.initializeConfigurations("." + Utilities.SEPARATOR + "config" + Utilities.SEPARATOR + "config.properties");
		} catch (InvalidPropertyException e) {
			e.printStackTrace();
		}


		System.out.println("======================");
		System.out.println("Press 1 to add a folder");
		System.out.println("Press 2 for search");
		System.out.println("Press 4 to exit");
		System.out.println("======================\n\n");


		int choice;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a number: ");
		choice = reader.nextInt(); // Scans the next token of the input as an int.

		while(choice != 4){

			switch (choice) {
			case 1:
				System.out.println("Enter folder path you want to index: ");
				try {
					folderPath = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				operationsHandler.addFilesUnderFolderForSearching(folderPath);

				break;

			case 2:
				System.out.println("Enter query string: ");
				try {
					query = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Query to be searched: "+query);
				operationsHandler.searchQuery(query);
				break;

			case 3:
				Iterator it = NormalIndex.fileNumToNameMap.entrySet().iterator();
				while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        System.out.println(pair.getKey() + " = " + pair.getValue().toString());
			        //it.remove(); // avoids a ConcurrentModificationException
			    }
				
//				it = InvertedIndex.wordToFileNumsMapping.entrySet().iterator();
//				while (it.hasNext()) {
//			        Map.Entry pair = (Map.Entry)it.next();
//			        System.out.println(pair.getKey() + " = " + pair.getValue().toString());
//			        it.remove(); // avoids a ConcurrentModificationException
//			    }
				
				break;
				
			default:
				System.out.println("Invalid choice. Choose from available options");
				break;
			}


			System.out.println("Enter a number: ");
			choice = reader.nextInt(); // Scans the next token of the input as an int.

		} //end of while
		
		
		
	}
}

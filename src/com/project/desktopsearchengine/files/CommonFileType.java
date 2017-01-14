package com.project.desktopsearchengine.files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.desktopsearchengine.utilities.Filter;
import com.project.desktopsearchengine.utilities.Utilities;

public class CommonFileType {

public HashMap<String, Integer> readFileAndGetWords(String filePath, String DELIMITER) {
		
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();

		FileInputStream fis = null;
		BufferedReader reader = null;
		String line;
		String []wordsInLine = null;
		Utilities util = new Utilities();
		Filter filter = new Filter();
		LinkedList<String> filteredWords = new LinkedList<String>();

		try {
			fis = new FileInputStream(filePath);
			reader = new BufferedReader(new InputStreamReader(fis));

			while((line = reader.readLine()) != null){
				wordsInLine = line.split(DELIMITER);
				filter.filterWords(wordsInLine,filteredWords);
				util.populateWordCountHashMap(filteredWords, wordCount);
				filteredWords.clear();
			}           

		} catch (FileNotFoundException ex) {
			Logger.getLogger(CommonFileType.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CommonFileType.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			try {
				reader.close();
				fis.close();
			} catch (IOException ex) {
				Logger.getLogger(CommonFileType.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

			return wordCount;
	}

}

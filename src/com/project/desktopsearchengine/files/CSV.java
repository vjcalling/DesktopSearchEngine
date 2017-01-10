package com.project.desktopsearchengine.files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSV implements FileHandler{

	public static final String DELIMITER = ",";

	public HashMap<String, Integer> readFileAndGetWords(String filePath) {
		
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();

		FileInputStream fis = null;
		BufferedReader reader = null;
		String line;
		int count;

		try {
			fis = new FileInputStream(filePath);
			reader = new BufferedReader(new InputStreamReader(fis));

			while((line = reader.readLine()) != null){
				String[] wordsInLine = line.split(DELIMITER);
				for(String word : wordsInLine){
					if(wordCount.containsKey(word)){
						count = wordCount.get(word);
						count++;
						wordCount.put(word, count);
					}else{
						wordCount.put(word, 1);
					}
				}
			}           

		} catch (FileNotFoundException ex) {
			Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			try {
				reader.close();
				fis.close();
			} catch (IOException ex) {
				Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

			return wordCount;
	}

}

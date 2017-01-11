package com.project.desktopsearchengine.files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.desktopsearchengine.utilities.Utilities;

public class CommonFileType {

public HashMap<String, Integer> readFileAndGetWords(String filePath, String DELIMITER) {
		
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();

		FileInputStream fis = null;
		BufferedReader reader = null;
		String line;
		Utilities util = new Utilities();

		try {
			fis = new FileInputStream(filePath);
			reader = new BufferedReader(new InputStreamReader(fis));

			while((line = reader.readLine()) != null){
				util.populateWordCountHashMap(line, DELIMITER, wordCount);
			}           

		} catch (FileNotFoundException ex) {
			Logger.getLogger(TXT.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(TXT.class.getName()).log(Level.SEVERE, null, ex);

		} finally {
			try {
				reader.close();
				fis.close();
			} catch (IOException ex) {
				Logger.getLogger(TXT.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

			return wordCount;
	}
}

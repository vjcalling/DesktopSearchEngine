package com.project.desktopsearchengine.files;

import java.util.HashMap;

public class DOC implements FileHandler{

	public final String DELIMITER = " ";

	public HashMap<String, Integer> readFileAndGetWords(String filePath) {
		
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
		wordCount = new CommonFileType().readFileAndGetWords(filePath, DELIMITER);
		return wordCount;
	}

}

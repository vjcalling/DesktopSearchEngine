package com.project.desktopsearchengine.utilities;

import java.util.LinkedList;



public class Filter {

	public void filterWords(String[] words, LinkedList<String> filteredWords){

		for(String word : words){
			word = word.toLowerCase();
			word = word.replaceAll("\\.|!|'|:|;|(|)|", "");
			if(Utilities.stopWords.contains(word))	//filtering out al stopwords
				continue;
			filteredWords.add(word);
		}
	}
}

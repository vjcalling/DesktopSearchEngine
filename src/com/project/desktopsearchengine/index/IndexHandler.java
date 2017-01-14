package com.project.desktopsearchengine.index;

import java.util.HashMap;

public class IndexHandler {

	public void updateIndices(HashMap<String,Integer> wordCount, String fileName){
		
		int fileIndex = NormalIndex.addFileToNormalIndex(fileName);
		InvertedIndex.addWordCountMapToInvertedIndex(wordCount, fileIndex);
	}
}

package com.project.desktopsearchengine.index;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InvertedIndex {

	public static HashMap<String, LinkedList<FileWeightage>> wordToFileNumsMapping = new HashMap<String, LinkedList<FileWeightage>>();
	
	public static void addWordCountMapToInvertedIndex(HashMap<String, Integer> wordCount, int fileIndex){
		
		int frequency;
		String word;
		LinkedList<FileWeightage> fileWeightageList;
		
		Iterator it = wordCount.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        word = (String) pair.getKey();
	        frequency = (int) pair.getValue();
	        
//	        System.out.println("Word: "+word+" Frequency: "+frequency);
	        
	        FileWeightage weightage = new FileWeightage(fileIndex, frequency);
	        
	        if(wordToFileNumsMapping.containsKey(word)){
	        	fileWeightageList = wordToFileNumsMapping.get(word);
	        	fileWeightageList.add(weightage);
	        }else{
	        	fileWeightageList = new LinkedList<FileWeightage>();
	        	fileWeightageList.add(weightage);
	        	wordToFileNumsMapping.put(word, fileWeightageList);
	        }
	        
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
}

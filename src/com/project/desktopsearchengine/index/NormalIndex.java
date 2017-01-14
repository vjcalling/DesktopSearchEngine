package com.project.desktopsearchengine.index;

import java.util.HashMap;

public class NormalIndex {

	public static HashMap<Integer, String> fileNumToNameMap = new HashMap<Integer, String>();
	
	public static int addFileToNormalIndex(String fileName){
		
		int currentCounter = fileNumToNameMap.size() + 1;
		fileNumToNameMap.put(currentCounter, fileName);
		return currentCounter;
	}
}

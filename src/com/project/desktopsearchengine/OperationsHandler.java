package com.project.desktopsearchengine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;

import com.project.desktopsearchengine.files.FileHandler;
import com.project.desktopsearchengine.index.FileWeightage;
import com.project.desktopsearchengine.index.IndexHandler;
import com.project.desktopsearchengine.index.InvertedIndex;
import com.project.desktopsearchengine.index.NormalIndex;
import com.project.desktopsearchengine.utilities.Utilities;

public class OperationsHandler {

	IndexHandler indexHandler = new IndexHandler();
	
	public void addFilesUnderFolderForSearching(String folderPath){
		
		
		String extension;
		HashMap<String, Integer> wordCount;
		ArrayList<String> selectedFiles;
		selectedFiles = Utilities.getAllFilesWithExtensions(folderPath);
		FileHandler.foldersAdded.add(new File(folderPath));
		
		System.out.println("Selected files count: "+selectedFiles.size());
		
		for(String f : selectedFiles){
			extension = FilenameUtils.getExtension(f);
			extension = extension.toUpperCase(); // No need to check for null, we are already filtering files on given extensions

			try {
				Class clazz = Class.forName(Utilities.FILES_PACKAGE+extension);
				FileHandler file = (FileHandler)clazz.newInstance();
				wordCount = file.readFileAndGetWords(f);
				indexHandler.updateIndices(wordCount, f);

			} 
			catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
				Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
	}

	public List<File> searchQuery(String query) {

		query = query.toLowerCase();
		
		List<File> results = new ArrayList<File>();		
		List<FileWeightage> files = new LinkedList<FileWeightage>();
		files = InvertedIndex.wordToFileNumsMapping.get(query);
		
		Collections.sort(files);	//ranking results according to their frequency
		
		for(FileWeightage w : files){
			System.out.println(NormalIndex.fileNumToNameMap.get(w.getFileIndex())+" "+w.getFrequency());
		}
		
		return results;
	}
	
}

package com.project.desktopsearchengine.index;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import com.project.desktopsearchengine.files.FileHandler;

public class IndexHandler {


//-------------------------------------------------------------------------------------------
		
	public void updateIndices(HashMap<String,Integer> wordCount, String fileName){
		
		int fileIndex = NormalIndex.addFileToNormalIndex(fileName);
		InvertedIndex.addWordCountMapToInvertedIndex(wordCount, fileIndex);
	}

//-------------------------------------------------------------------------------------------
		
	public static void persistIndices(){
		
		persistIndex(NormalIndex.fileNumToNameMap, "normal_index.ser");
		persistIndex(InvertedIndex.wordToFileNumsMapping, "inverted_index.ser");
		persistIndex(FileHandler.foldersAdded, "added_folders_index.ser");
		
	}
	
//-------------------------------------------------------------------------------------------
	
	public static void persistIndex(Object obj, String fileName){
		
		try {
	         FileOutputStream fileOut = new FileOutputStream("./indices/"+fileName,false);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(obj);
	         out.close();
	         fileOut.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}

//-------------------------------------------------------------------------------------------
	
	public static void loadIndices(){
		
		try {
	         
			 FileInputStream fileInNormal = new FileInputStream("./indices/normal_index.ser");
	         ObjectInputStream inNormal = new ObjectInputStream(fileInNormal);
	         NormalIndex.fileNumToNameMap = (HashMap<Integer, String>) inNormal.readObject();
	         inNormal.close();
	         fileInNormal.close();
	         
	         FileInputStream fileInverted = new FileInputStream("./indices/inverted_index.ser");
	         ObjectInputStream inInverted = new ObjectInputStream(fileInverted);
	         InvertedIndex.wordToFileNumsMapping = (HashMap<String, LinkedList<FileWeightage>>) inInverted.readObject();
	         inInverted.close();
	         fileInverted.close();
	         

	         FileInputStream fileFoldersAdded = new FileInputStream("./indices/added_folders_index.ser");
	         ObjectInputStream inFolders = new ObjectInputStream(fileFoldersAdded);
	         FileHandler.foldersAdded = (HashSet<File>) inFolders.readObject();
	         fileFoldersAdded.close();
	         inFolders.close();
	         
	      }catch(IOException i) {
//	         i.printStackTrace();
//	         return;
	      }catch(ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	}
		
//--------------------------------------------------------------------------------------------	
	
}

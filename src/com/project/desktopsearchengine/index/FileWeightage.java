package com.project.desktopsearchengine.index;

import java.io.Serializable;

public class FileWeightage implements Comparable<FileWeightage>, Serializable{

	private int fileIndex;
	private int frequency;
	
	public FileWeightage(){
		
	}
	
	public FileWeightage(int fileIndex, int frequency){
		this.fileIndex = fileIndex;
		this.frequency = frequency;
	}
	public int getFileIndex() {
		return fileIndex;
	}
	public void setFileIndex(int fileIndex) {
		this.fileIndex = fileIndex;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public int compareTo(FileWeightage file) {
		
		return file.getFrequency() - this.getFrequency();
	}
	
}

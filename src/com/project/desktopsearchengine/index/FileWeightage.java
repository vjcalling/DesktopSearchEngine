package com.project.desktopsearchengine.index;

public class FileWeightage {

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
	
}

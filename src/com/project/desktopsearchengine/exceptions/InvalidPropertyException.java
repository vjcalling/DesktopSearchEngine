package com.project.desktopsearchengine.exceptions;

public class InvalidPropertyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPropertyException(String s){
		super(s);
		System.out.println("Property not found in properties file");
	}
	
	public InvalidPropertyException(){
		super();
		System.out.println("Extensions missing in properties file");
	}
}

package com.project.desktopsearchengine.exceptions;

public class InvalidPropertyException extends Exception{

	public InvalidPropertyException(String s){
		super(s);
		System.out.println("Property not found in properties file");
	}
	
	public InvalidPropertyException(){
		super();
		System.out.println("Extensions missing in properties file");
	}
}

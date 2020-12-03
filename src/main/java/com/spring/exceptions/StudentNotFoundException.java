package com.spring.exceptions;

public class StudentNotFoundException extends RuntimeException{
	
	public StudentNotFoundException(String exception) {
		super(exception);
	}
}

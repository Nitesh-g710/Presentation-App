package com.in.main.exception;

public class AdminOnlyException extends RuntimeException {
	private String fieldType, field;
	
	public AdminOnlyException(String fieldType, String field) {
		super(String.format("Only Admin can %s a %s", fieldType, field));
		this.fieldType = fieldType;
		this.field = field;
	}
}

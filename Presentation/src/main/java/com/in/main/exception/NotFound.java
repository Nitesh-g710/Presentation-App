package com.in.main.exception;

public class NotFound extends RuntimeException  {
	private String resourceName ,field ;
	private String fieldValue;
	
	
	public NotFound(String resourceName , String field , String fieldValue){
		super(String.format("%s is not found with %s : %s",resourceName,field,fieldValue));
		this.resourceName = resourceName;
		this.field =  field;
		this.fieldValue = fieldValue;
	}
	
	
	public NotFound(String resourceName, String field) {
		super(String.format("%s is not found! %n %s = false ",resourceName, field));
		this.resourceName = resourceName;
		this.field = field;
	}
	
	
	
}

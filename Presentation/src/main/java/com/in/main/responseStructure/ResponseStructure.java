package com.in.main.responseStructure;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T> {
	
	private Integer statuscode;
	private String message;
	private T data;
	
	
	
}

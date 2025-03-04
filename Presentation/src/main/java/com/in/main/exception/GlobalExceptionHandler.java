package com.in.main.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.in.main.responseStructure.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFound.class)
	public ResponseEntity<ResponseStructure<String>> CatchNotFound(NotFound excep) {
		// TODO Auto-generated method stub
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setData(excep.getMessage());
		rs.setMessage("User not found!");
		rs.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}
	
	@ExceptionHandler(AdminOnlyException.class)
	public ResponseEntity<ResponseStructure<String>> CatchAdminException(AdminOnlyException excep) {
		// TODO Auto-generated method stub
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setData(null);
		rs.setMessage(excep.getMessage());
		rs.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> CatchMethodArgumentNotValid(MethodArgumentNotValidException excep) {
		
		Map<String, String> rs = new HashMap<>();
		
		excep.getBindingResult().getAllErrors().forEach(error -> 
			{
				String fields =((FieldError) error).getField();
				String value = error.getDefaultMessage();
				rs.put(fields, value);
			});
		
		
//		ResponseStructure<String> rs = new ResponseStructure<>();
//		rs.setData(null);
//		rs.setMessage(excep.getMessage());
//		rs.setStatuscode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}
	
	
	
}

package com.in.main.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class loginDto {
	
	@Email
	private String email;
	private String password;
	
}

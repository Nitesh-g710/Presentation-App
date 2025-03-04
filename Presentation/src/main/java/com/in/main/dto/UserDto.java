package com.in.main.dto;

import java.util.List;

import com.in.main.entity.Presentations;
import com.in.main.enums.Role;
import com.in.main.enums.Status;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

	private Integer userId;

	@Size(min = 2, message = "Invalid Name!")
	@NotNull
	private String name;

	@Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits")
	@NotNull
	private long phone;

	@NotNull
	@Size(min = 5)
	private String password;

	@Email
	private String email;

	private Status status;

	private List<Presentations> presentation;

	private Role role;

	private Double userTotalScore;
}

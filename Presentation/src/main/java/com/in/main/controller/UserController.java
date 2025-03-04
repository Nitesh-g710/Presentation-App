package com.in.main.controller;

import org.springframework.web.bind.annotation.RestController;

import com.in.main.dto.UserDto;
import com.in.main.dto.loginDto;
import com.in.main.entity.User;
import com.in.main.enums.Status;
import com.in.main.responseStructure.ResponseStructure;
import com.in.main.service.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	// register user
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto user) {
		userService.register(user);
		ResponseStructure<UserDto> rs = new ResponseStructure<>();
		rs.setData(user);
		rs.setMessage("Registered Sucessfully!!");
		rs.setStatuscode(200);
		return new ResponseEntity<>(rs, HttpStatus.CREATED);
	}

	// user login
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody loginDto login) {
		boolean bool = userService.login(login);
		if (bool) {
			ResponseStructure<?> rs = new ResponseStructure<>();
			rs.setData(null);
			rs.setMessage("Login Sucessfully!!");
			rs.setStatuscode(200);
			return new ResponseEntity<>(rs, HttpStatus.OK);
		}
		ResponseStructure<?> rs = new ResponseStructure<>();
		rs.setData(null);
		rs.setMessage("Login failed!!");
		rs.setStatuscode(404);
		return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);

	}

	// user logout
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestParam("email") String email) {
		System.out.println(email);
		boolean bool = userService.logout(email);
		if (bool) {
			ResponseStructure<?> rs = new ResponseStructure<>();
			rs.setData(null);
			rs.setMessage("Logout Sucessfully!!");
			rs.setStatuscode(200);
			return new ResponseEntity<>(rs, HttpStatus.OK);
		}
		ResponseStructure<?> rs = new ResponseStructure<>();
		rs.setData(null);
		rs.setMessage("Logout failed!!");
		rs.setStatuscode(404);
		return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
	}

	// get user by gmail
	@GetMapping("/get/{email}")
	public ResponseEntity<ResponseStructure<UserDto>> getById(@PathVariable String email ) {
		
		UserDto user = userService.getUser(email);
	    ResponseStructure<UserDto> response = new ResponseStructure<>(200, "Retrieved User successfully !!", user);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<UserDto>> getById(@PathVariable Integer id) {
		UserDto userDto = userService.getUserById(id);
		ResponseStructure<UserDto> rs = new ResponseStructure<>(200, "User Retrieved Sucessfully!!", userDto); 
		return new ResponseEntity<>(rs, HttpStatus.OK);

	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<UserDto>>> getAllUsers() {
		List<UserDto> users = userService.getAllUser();
		ResponseStructure<List<UserDto>> rs = new ResponseStructure<>(200, "All users Retrieved Sucessfully!!", users);
		return new ResponseEntity<ResponseStructure<List<UserDto>>>(rs, HttpStatus.OK);

	}
	
//	@PutMapping("/update/id/{uId}")
//	public ResponseEntity<ResponseStructure<UserDto>> getById(@RequestBody UserDto user, @PathVariable Integer uId ) {
//		
//		UserDto updatedUser = userService.updateUserDetails(uId,user);
//	    ResponseStructure<UserDto> response = new ResponseStructure<>(200, "User Details Updated successfully !!", updatedUser);
//	    return new ResponseEntity<>(response, HttpStatus.OK);
//	}
	
	@PutMapping("/update/status/{email}")
	public ResponseEntity<ResponseStructure<UserDto>> updateStatus(@PathVariable String email,@RequestBody Status status) {
		userService.updateStatus(email, status);
		ResponseStructure<UserDto> response = new ResponseStructure<>(200, "user status updated!!", null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

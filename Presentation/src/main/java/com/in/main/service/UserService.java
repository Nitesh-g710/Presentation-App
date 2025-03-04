package com.in.main.service;

import java.util.List;
import com.in.main.dto.UserDto;
import com.in.main.dto.loginDto;
import com.in.main.entity.User;
import com.in.main.enums.Status;


public interface UserService {
	
public UserDto register(UserDto user) ;

	
	boolean login(loginDto login)  ;
	
	boolean logout(String email) ;
	
	UserDto getUser(String email)  ;	
	

//	UserDto updateUserDetails(Integer uId,UserDto userDto);

	UserDto updateStatus(String email, Status status);

	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUser();  
	
}

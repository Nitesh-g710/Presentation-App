package com.in.main.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.main.dao.UserDao;
import com.in.main.dto.UserDto;
import com.in.main.dto.loginDto;
import com.in.main.entity.User;
import com.in.main.enums.Role;
import com.in.main.enums.Status;
import com.in.main.exception.AdminOnlyException;
import com.in.main.exception.NotFound;
import com.in.main.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDto register(UserDto user) {
		User user1 = userDao.save(dtoToUser(user));
		return userToDto(user1);
	}

	@Override
	public boolean login(loginDto login) {
		User opt = userDao.findByEmail(login.getEmail())
				.orElseThrow(() -> new NotFound("email", "Login", login.getEmail()));

		if (opt.getPassword().equals(login.getPassword())) {
			opt.setStatus(Status.ACTIVE);
			userDao.save(opt);
			return true;
		}
		return false;
	}

	@Override
	public boolean logout(String email) {
		try {
			
			User user = userDao.findByEmail(email).orElseThrow(() -> new NotFound("email", "logout"));
			
			
			user.setStatus(Status.INACTIVE);
			userDao.save(user);
		
			return true;
			
		} catch (NotFound e) {
			throw new NotFound("email", "logout");
		}
	}

	@Override
	public UserDto getUser(String email) {
		User user = userDao.findByEmail(email).orElseThrow(() -> new NotFound("Email", "User", email));
		return userToDto(user);
	}
	
	public UserDto getUserById(Integer userId) {
		User user = userDao.findById(userId).orElseThrow(()-> new NotFound("user id","User", ""+userId ));
		return userToDto(user);
		
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = userDao.findAll();
		List<UserDto> userDtos = users.stream().map(user1 -> this.modelMapper.map(user1, UserDto.class))
				.collect(Collectors.toList());
		return userDtos;
	}

//	@Override
//	public UserDto updateUserDetails(Integer uId, UserDto userDto) {
//		User user = userDao.findById(uId)
//				.orElseThrow(() -> new NotFound("email", "User", ""+uId));
//		if(user.getRole()==Role.ADMIN) {
//			
//			user.setName(userDto.getName());
//			user.setEmail(userDto.getEmail());
//			user.setPhone(userDto.getPhone());
//			user.setPassword(userDto.getPassword());
//			return userToDto(user);
//		}
//		throw new AdminOnlyException("update", "User Details");
//	}

	@Override
	public UserDto updateStatus(String email, Status status) {
		User user = userDao.findByEmail(email).orElseThrow(() -> new NotFound("email", "User", email));
		if (user.getRole()==Role.ADMIN) {
			user.setStatus(status);
			return userToDto(user);
		}
		throw new AdminOnlyException("update", "user status");
	}

	
	
	private UserDto userToDto(User user) {
		return this.modelMapper.map(user, UserDto.class);

	}

	private User dtoToUser(UserDto userDto) {
		return this.modelMapper.map(userDto, User.class);

	}

}

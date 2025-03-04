package com.in.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.in.main.entity.User;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	
	Optional<User> findByEmail(String email);
	
}

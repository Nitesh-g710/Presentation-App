package com.in.main.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in.main.enums.Role;
import com.in.main.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@NotBlank(message = "Name should not be empty!")
	private String name;
	
	@Column(unique =true)
	private String email;
	
	private long phone;
	
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Presentations> presentation;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	private Double userTotalScore;
	
}

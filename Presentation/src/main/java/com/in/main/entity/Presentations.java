package com.in.main.entity;

import java.util.List;

import com.in.main.enums.PresentationStatus;
import com.in.main.enums.Status;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Presentations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
	@Nullable
	private String course;
	
	
	@Nullable
	private String topics;
	
	@Enumerated(EnumType.STRING)
	private PresentationStatus presentationStatus;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Double presentationTotalScore;
	
}


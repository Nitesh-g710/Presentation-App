package com.in.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rid;
	
	private Integer communication;
	private Integer confidence;
	private Integer content;
	private Integer interaction;
	private Integer liveliness;
	private Integer usageProps;
	private Double totalScore;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "pid")
	private Presentations presentation;
	
}

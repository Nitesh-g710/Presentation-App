package com.in.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.in.main.entity.User;
import com.in.main.enums.PresentationStatus;
import com.in.main.enums.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PresentationDto {

	private Integer pid;

	@NotNull(message = "User id must not be empty!!")
	@JsonProperty("user")
	private User user;

	@NotNull(message = "course must not be empty!!")
	@JsonProperty("course")
	private String course;

	@NotNull(message = "topics must not empty!")
	@JsonProperty("topics")
	private String topics;

	@NotNull(message = "presentation status must not be empty!!")
	@JsonProperty("presentationStatus")
	private PresentationStatus presentationStatus;

	@NotNull(message = "status can not be empty!!")
	@JsonProperty("status")
	private Status status;

	private Double userTotalScore;

	
}

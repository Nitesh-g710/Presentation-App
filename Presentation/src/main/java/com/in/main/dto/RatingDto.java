package com.in.main.dto;

import com.in.main.entity.Presentations;
import com.in.main.entity.User;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RatingDto {
	
	Integer rId;
	
	 @Min(value = 1, message = "Communication score must be at least 1!!")
	 @Max(value = 5, message = "Communication score must be at most 5!!")
	@NotNull
	Integer communication;
	
	 @Min(value = 1, message = "Confidence score must be at least 1!!")
	 @Max(value = 5, message = "Confidence score must be at most 5!!")
	@NotNull
	Integer confidence;
	
	 @Min(value = 1, message = "Content score must be at least 1!!")
	 @Max(value = 5, message = "Content score must be at most 5!!")
	@NotNull
	Integer content;
	
	 @Min(value = 1, message = "Interaction score must be at least 1!!")
	 @Max(value = 5, message = "Interaction score must be at most 5!!")
	@NotNull
	Integer interaction;
	
	 @Min(value = 1, message = "liveliness score must be at least 1!!")
	 @Max(value = 5, message = "liveliness score must be at most 5!!")
	@NotNull
	Integer liveliness;
	
	 @Min(value = 1, message = "useageProps score must be at least 1!!")
	 @Max(value = 5, message = "useageProps score must be at most 5!!")
	@NotNull
	Integer useageProps;
	
	
	double totalScore;
	
	@NotNull(message = "user id is not defined!!")
	User user;
	
	@NotNull(message = "presentation id is not defined!! ")
	Presentations presentation;
}

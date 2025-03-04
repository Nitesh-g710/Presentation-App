package com.in.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.main.dto.RatingDto;
import com.in.main.entity.Rating;
import com.in.main.responseStructure.ResponseStructure;
import com.in.main.service.RatingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@PostMapping("/addRating")
	public ResponseEntity<?> addRating(@Valid @RequestBody RatingDto rating) {
		System.out.println("score added!");
		RatingDto ratingDto = ratingService.saveRating(rating);
		ResponseStructure<RatingDto> rs = new ResponseStructure<>(200, "rating added!", rating);
		return new ResponseEntity<ResponseStructure<?>>(rs, HttpStatus.OK);
	}
	
	
	@GetMapping("/uId/{uId}")
	public ResponseEntity<?> getByUserId(@PathVariable Integer uId) {
		List<RatingDto> ratings =  ratingService.getRatingByUserId(uId);
		ResponseStructure<List<RatingDto>> rs = new ResponseStructure<>(200, "All Ratings Retrieved Sucessfully!", ratings);
		return new ResponseEntity<ResponseStructure<?>>(rs, HttpStatus.OK);
	}
	
	
	@GetMapping("/pId/{pId}")
	public ResponseEntity<?> getByPresentationId(@PathVariable Integer pId) {
		List<RatingDto> rating = ratingService.getRatingByPresentationId(pId);
		ResponseStructure<List<RatingDto>> rs = new ResponseStructure<>(200, "All Ratings Retrieved Sucessfully!", rating);
		return new ResponseEntity<ResponseStructure<?>>(rs, HttpStatus.OK);
	}
	
}

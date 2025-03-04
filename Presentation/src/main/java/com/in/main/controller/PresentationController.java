package com.in.main.controller;


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
import org.springframework.web.bind.annotation.RestController;

import com.in.main.dto.PresentationDto;
import com.in.main.enums.PresentationStatus;
import com.in.main.responseStructure.ResponseStructure;
import com.in.main.service.PresentationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/presentations")
public class PresentationController {

	@Autowired
	PresentationService presentationService;

	
	//get
	@PostMapping("/assignPresentations")
	public ResponseEntity<ResponseStructure<PresentationDto>> assignPresentation(
			@Valid @RequestBody PresentationDto presentationDto) {
		System.out.print("Nitesh");
		System.out.println(presentationDto);
		presentationService.assignPresentation(presentationDto);
		ResponseStructure<PresentationDto> rs = new ResponseStructure<>(200, "presentation assgined sucessfully!",
				presentationDto);
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<PresentationDto>> getByPresentationId(@PathVariable Integer pId) {
		PresentationDto presentation = presentationService.getPresentation(pId);
		ResponseStructure<PresentationDto> rs = new ResponseStructure<>(200, "Retrieved Presentations Sucessfully!!",
				presentation);
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}

	@GetMapping("/getAll/{uId}")
	public ResponseEntity<ResponseStructure<List<PresentationDto>>> getAllByUserid(@PathVariable Integer uId) {
		List<PresentationDto> presentationDtos = presentationService.getAllPresentationByUserid(uId);
		ResponseStructure<List<PresentationDto>> rs = new ResponseStructure<List<PresentationDto>>(200,
				"All Presentations are Retrieved Sucessfully!!", presentationDtos);
		return new ResponseEntity<ResponseStructure<List<PresentationDto>>>(rs, HttpStatus.OK);
	}

	@PutMapping("/id/{pId}")
	public ResponseEntity<ResponseStructure<?>> changePresentationStatus(@PathVariable Integer pId,
			@RequestBody PresentationStatus status) {
		
		presentationService.changeStatus(pId, status);
		ResponseStructure<?> rs = new ResponseStructure<>(200, "Status changed Sucussfully!!" , status);
		
		return new ResponseEntity<ResponseStructure<?>>(rs, HttpStatus.OK);
	}
	
	
	@PutMapping("/pid/{pid}/score/{userScore}")
	public ResponseEntity<?> addScore(@PathVariable Integer pid,@PathVariable Double userScore) {
		presentationService.addScore(pid, userScore);
		ResponseStructure<?> rs = new ResponseStructure<>(200, "score added!", null);
		return new ResponseEntity<ResponseStructure<?>>(rs, HttpStatus.OK);
	}
	

}

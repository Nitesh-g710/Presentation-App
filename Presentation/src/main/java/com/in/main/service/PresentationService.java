package com.in.main.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.in.main.dto.PresentationDto;
import com.in.main.dto.UserDto;
import com.in.main.enums.PresentationStatus;


public interface PresentationService{
	public PresentationDto getPresentation(Integer pid) ;

	public List<PresentationDto> getAllPresentationByUserid(Integer uid) ;

	void assignPresentation(PresentationDto presentation);
	
	public boolean changeStatus(Integer presentationId, PresentationStatus newStatus ) ;
	
	public void addScore( Integer pid ,Double userScore);
		
}

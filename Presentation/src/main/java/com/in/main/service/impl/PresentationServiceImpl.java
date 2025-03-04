package com.in.main.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.in.main.dao.PresentationDao;
import com.in.main.dao.UserDao;
import com.in.main.dto.PresentationDto;
import com.in.main.dto.UserDto;
import com.in.main.entity.Presentations;
import com.in.main.entity.User;
import com.in.main.enums.PresentationStatus;
import com.in.main.exception.NotFound;
import com.in.main.service.PresentationService;

import jakarta.transaction.Transactional;

@Service
public class PresentationServiceImpl implements PresentationService {

	@Autowired
	PresentationDao presentationDao;

	@Autowired
	UserDao userDao;

	@Autowired
	ModelMapper modelMapper;

	@Override
	@Transactional
	public void assignPresentation(PresentationDto presentation) {
		System.out.println(presentation);
		Presentations presentation2 = this.modelMapper.map(presentation, Presentations.class);
		presentationDao.save(presentation2);
	}

	
	@Override
	public PresentationDto getPresentation(Integer pid) {
		Presentations presentation = presentationDao.findById(pid)
				.orElseThrow(() -> new NotFound("id", "Presentations", "" + pid));
		PresentationDto dto = this.modelMapper.map(presentation, PresentationDto.class);

		return dto;
	}

	@Override
	public List<PresentationDto> getAllPresentationByUserid(Integer uid) {
		User user = userDao.findById(uid).orElseThrow(() -> new NotFound("user id", "User", "" + uid));
		List<Presentations> presentations = presentationDao.findByUser(user.getUserId());
		return presentations.stream().map(presentation -> this.modelMapper.map(presentations, PresentationDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public boolean changeStatus(Integer pId, PresentationStatus newStatus) {
		Presentations presentation = presentationDao.findById(pId)
				.orElseThrow(() -> new NotFound("Presentations id", "Presentations", "" + pId));
		presentation.setPresentationStatus(newStatus);
		presentationDao.save(presentation);
		return true;
	}

	@Override
	public void addScore(Integer pid, Double userScore) {
		Presentations presentation = presentationDao.findById(pid)
				.orElseThrow(() -> new NotFound("presentation id", "Presentations", "" + pid));
		presentation.setPresentationTotalScore(userScore);
		presentationDao.save(presentation);

		updateUserTotalScore(presentation.getUser().getUserId());
	}

	
	//after changing/adding the score new total score getting add here 
	private void updateUserTotalScore(Integer userId) {
		User user = userDao.findById(userId).orElseThrow(()-> new NotFound("user id", "User", ""+userId));
		List<Presentations> presentations = presentationDao.findByUser(user.getUserId());
		List<Presentations> completedPresentations = new ArrayList<>();

		int count = 0;
		// filter completed presentation
		completedPresentations.addAll(presentations.stream()
				.filter(presentation -> presentation.getPresentationStatus().equals("COMPLETED")
						&& presentation.getPresentationTotalScore() != null
						&& presentation.getPresentationTotalScore() > 0)
				.collect(Collectors.toList()));

		// calculate total score
		double totalScore = 0.0;

		totalScore = completedPresentations.stream()
				.mapToDouble(presentation -> presentation.getPresentationTotalScore()).sum();
		
		double userTotalScore = totalScore/completedPresentations.size();
		user.setUserTotalScore(userTotalScore);
		userDao.save(user);
		
		
	}

}

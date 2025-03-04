package com.in.main.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.in.main.service.EmailService;
import com.in.main.service.PDFService;
import com.in.main.service.RatingService;

import jakarta.transaction.Transactional;

import com.in.main.dao.PresentationDao;
import com.in.main.dao.RatingDao;
import com.in.main.dao.UserDao;
import com.in.main.dto.RatingDto;
import com.in.main.entity.Presentations;
import com.in.main.entity.Rating;
import com.in.main.entity.User;
import com.in.main.enums.PresentationStatus;
import com.in.main.enums.Role;
import com.in.main.exception.AdminOnlyException;
import com.in.main.exception.NotFound;
import com.in.main.responseStructure.ResponseStructure;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	UserDao userDao;

	@Autowired
	PresentationDao presentationDao;

	@Autowired
	RatingDao ratingDao;

	@Autowired
	ModelMapper mapper;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PDFService pdfService;

	@Override
	@Transactional
	public RatingDto saveRating(RatingDto ratingd) {
		System.out.println("rating service");
		Rating rating = mapper.map(ratingd, Rating.class);
		User user = userDao.findById(rating.getUser().getUserId())
				.orElseThrow(() -> new NotFound("user id", "User", "" + rating.getUser().getUserId()));

		if (user.getRole() == Role.ADMIN) {

			Presentations presentation = presentationDao.findById(rating.getPresentation().getPid()).orElseThrow(
					() -> new NotFound("Presentations id", "presentation", "" + rating.getPresentation().getPid()));

			Rating ratings = ratingDao.findByUserIdAndPresentationId(rating.getUser().getUserId(),
					rating.getPresentation().getPid());
			if (ratings == null) {
				ratings = new Rating();
				ratings.setUser(user);
				ratings.setPresentation(presentation);
			}

			// Set rating values
			ratings.setCommunication(ratingd.getCommunication());
			ratings.setConfidence(ratingd.getConfidence());
			ratings.setContent(ratingd.getContent());
			ratings.setInteraction(ratingd.getInteraction());
			ratings.setLiveliness(ratingd.getLiveliness());
			ratings.setUsageProps(ratingd.getUseageProps());

			// Calculate & set total score
//	        ratings.setTotalScore();

			ratingDao.save(ratings);
			RatingDto ratingDto = this.mapper.map(ratings, RatingDto.class);

			// Fetch User details

//	        if (user != null) {
//	            String email = user.getEmail();
//	            String subject = "Your Presentations Rating Summary";
//	            String message = "<h3>Dear " + user.getName() + ",</h3>"
//	                    + "<p>Your rating for the presentation has been recorded.</p>"
//	                    + "<p>Please find the attached rating summary.</p>";
//
//	            // Generate PDF
//	            byte[] pdfData = pdfService.generateRatingPDF(ratingd);
//
//	            // Send email with PDF
//	            emailService.sendRatingEmailWithAttachment(email, subject, message, pdfData);
//	        }

			return ratingDto;
		}

		throw new AdminOnlyException("rate", "presentation");
	}

	@Override
	public List<RatingDto> getRatingByUserId(Integer uId) {
		List<Rating> ratings = ratingDao.findByUserId(uId);

		List<RatingDto> ratingDtos = ratings.stream().map(rating -> this.mapper.map(ratings, RatingDto.class))
				.collect(Collectors.toList());

		return ratingDtos;
	}

	@Override
	public List<RatingDto> getRatingByPresentationId(Integer pId) {
		List<Rating> ratings = ratingDao.findByPresentationId(pId);
		List<RatingDto> ratingDtos = ratings.stream().map(rate -> this.mapper.map(rate, RatingDto.class))
				.collect(Collectors.toList());

		return ratingDtos;
	}

	@Override
	public void updateUserTotalScore(Integer userId) {
		//getting all presentaions by userId
		List<Presentations> presentations = presentationDao.findByUser(userId);

		//filtering the presentations only taking completed presentations without null and 0
		 List<Presentations> pList = presentations.stream()
				.filter(p -> p.getPresentationStatus() == PresentationStatus.COMPLETED
						&& p.getPresentationTotalScore() > 0 
						&& p.getPresentationTotalScore() != null)
				.collect(Collectors.toList());
		
		 //adding all presentations total score
		 Double total = pList.stream().mapToDouble(score -> score.getPresentationTotalScore() ).sum();
		 
		 //calculating average
		 if (total>0) {
			total /= pList.size();
		}
		 
		 //find the user and setting the total score of user
		 User user = userDao.findById(userId).orElseThrow(()-> new NotFound("user id",  "User", ""+userId));
		 user.setUserTotalScore(total);
		 userDao.save(user);
		 
		 
		 
	}

	@Override
	public void updatePresentationTotalScore(Integer pId) {
		List<Rating> ratings = ratingDao.findByPresentationId(pId);
		Double total = 0.0;

		total = ratings.stream().mapToDouble(score -> score.getTotalScore()).sum();

		if (ratings.size() > 0) {
			total /= ratings.size();
		}

		Presentations presentations = presentationDao.findById(pId)
				.orElseThrow(() -> new NotFound("presentation", "Presentation", "" + pId));
		presentations.setPresentationTotalScore(total);
		presentationDao.save(presentations);
	}

}

package com.in.main.service;

import java.util.List;
import com.in.main.dto.RatingDto;
import com.in.main.entity.Rating;

public interface RatingService {

	public RatingDto saveRating( RatingDto  rating) ;
	
	public List<RatingDto> getRatingByUserId(Integer uId) ;
	
	public List<RatingDto> getRatingByPresentationId(Integer pId) ;
	
	void updateUserTotalScore(Integer userId);
	
	void updatePresentationTotalScore(Integer pId);
	
}

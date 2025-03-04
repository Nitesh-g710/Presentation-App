package com.in.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.in.main.entity.Presentations;
import com.in.main.entity.Rating;
import com.in.main.entity.User;

@Repository
public interface RatingDao extends JpaRepository<Rating, Integer> {
	
	@Query("SELECT r FROM Rating r WHERE r.user.userId = ?1")
	List<Rating> findByUserId(Integer uId);

	@Query("SELECT r FROM Rating r WHERE r.presentation.pid = ?1")
	List<Rating> findByPresentationId(Integer presentationId);

	@Query("SELECT r FROM Rating r WHERE r.user.userId = ?1 AND r.presentation.pid = ?2")
	Rating findByUserIdAndPresentationId(Integer uId, Integer pId);
}

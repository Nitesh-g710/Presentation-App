package com.in.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.in.main.entity.Presentations;
import java.util.List;


@Repository
public interface PresentationDao extends JpaRepository<Presentations, Integer>{
	
	@Query("select p from Presentations p where p.user.userId=?1")
	List<Presentations> findByUser(Integer userId);
	
	
	
}

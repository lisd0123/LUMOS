package com.project.lumos.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.lumos.review.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

	@Query(value = "SELECT MAX(REVIEW_CODE) FROM TBL_REVIEW", nativeQuery = true)
	int findMaxReviewCode();


}

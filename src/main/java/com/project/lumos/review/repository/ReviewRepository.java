package com.project.lumos.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	long countByPdCode(int pdCode);

}

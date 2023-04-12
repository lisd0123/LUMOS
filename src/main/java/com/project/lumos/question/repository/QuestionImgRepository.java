package com.project.lumos.question.repository;

import java.util.List;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import com.project.lumos.question.entity.QuestionImg;

public interface QuestionImgRepository extends JpaRepository<QuestionImg, Integer>{

	QuestionImg findByQuestionCode(int questionCode);

	

	
}

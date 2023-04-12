package com.project.lumos.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.question.entity.QuestionAndImage;

public interface QuestionAndImageRepository extends JpaRepository<QuestionAndImage, Integer>{

	QuestionAndImage findByQuestionCode(int questionCode);

}

package com.project.lumos.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.question.entity.Question;
import com.project.lumos.question.entity.QuestionMemberAndImg;

public interface QuestionMemberAndImgRepository extends JpaRepository<QuestionMemberAndImg, Integer>{

	

}

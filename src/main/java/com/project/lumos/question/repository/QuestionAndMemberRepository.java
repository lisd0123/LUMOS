package com.project.lumos.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.question.entity.QuestionAndMember;

public interface QuestionAndMemberRepository extends JpaRepository<QuestionAndMember, Integer>{

}

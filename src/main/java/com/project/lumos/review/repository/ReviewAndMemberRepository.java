package com.project.lumos.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.review.entity.ReviewAndMember;

public interface ReviewAndMemberRepository extends JpaRepository<ReviewAndMember, Integer>{

	Page<ReviewAndMember> findByPdCode(int searchValue, Pageable paging);

}

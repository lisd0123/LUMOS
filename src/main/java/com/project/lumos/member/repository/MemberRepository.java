package com.project.lumos.member.repository;

import java.util.List;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.lumos.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	//=====================[전진이]====================//
	Member findByMemberId(String string); 		
//	Member findByMemberEmail(String memberEmail);
	@Query("SELECT MAX(a.memberCode) FROM Member a")
	int maxMemberCode();

	/* 토큰에 담긴 memberId로 memberCode 조회(이건일) */ 
	@Query(value = "SELECT MEMBER_CODE FROM TBL_MEMBER WHERE MEMBER_ID = :memberId", nativeQuery = true)
	public int findMemberCodeByMemberId(String memberId);

	
	//중복체크
//	List<Member> findByduplicatedId(String memberId);
//	Member findByduplicatedId(String memberId);
	
	/* [구도연] */
	Member findMemberByMemberId(String memberId);
	List<Member> findByMemberNameContaining(String searchValue);
	List<Member> findByMemberIdContaining(String searchValue);
	
}
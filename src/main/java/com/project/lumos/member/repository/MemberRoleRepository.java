package com.project.lumos.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.member.entity.MemberRole;
import com.project.lumos.member.entity.MemberRolePk;

//================================회원가입===========================================//
public interface MemberRoleRepository extends JpaRepository<MemberRole, MemberRolePk>{ 

}

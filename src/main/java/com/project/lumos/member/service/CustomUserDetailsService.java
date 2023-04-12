package com.project.lumos.member.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.lumos.member.dto.MemberDTO;
import com.project.lumos.member.entity.Member;
import com.project.lumos.member.entity.MemberRole;
import com.project.lumos.member.repository.MemberRepository;

@Service 
public class CustomUserDetailsService implements UserDetailsService{
   private final MemberRepository memberRepository;
   private final ModelMapper modelMapper;    
   
   @Autowired
   public CustomUserDetailsService(MemberRepository memberRepository, ModelMapper modelMapper) {
      this.memberRepository = memberRepository;
      this.modelMapper = modelMapper;
   }

   //=============================회원가입[전진이]================================//
   @Transactional
   @Override
   public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
      Member member = memberRepository.findByMemberId(memberId);
      
      MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);
      
      List<GrantedAuthority> authorities = new ArrayList<>();
      for(MemberRole memberRole : member.getMemberRole()) {
         String authorityName = memberRole.getAuthority().getAuthorityName();
         authorities.add(new SimpleGrantedAuthority(authorityName));
      }
      
      memberDTO.setAuthorities(authorities);
      
      return memberDTO;
   }
}





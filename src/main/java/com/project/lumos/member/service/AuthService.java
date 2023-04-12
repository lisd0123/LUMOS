package com.project.lumos.member.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.lumos.exception.DuplicatedMemberIdException;
import com.project.lumos.exception.LoginFailedException;
import com.project.lumos.jwt.TokenProvider;
import com.project.lumos.member.dto.MemberDTO;
import com.project.lumos.member.dto.TokenDTO;
import com.project.lumos.member.entity.Member;
import com.project.lumos.member.entity.MemberRole;
import com.project.lumos.member.repository.MemberRepository;
import com.project.lumos.member.repository.MemberRoleRepository;

@Service
public class AuthService {

	private static final Logger log = LoggerFactory.getLogger(AuthService.class);
	private final MemberRepository memberRepository; 		 // 회원가입
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final ModelMapper modelMapper;
	private final MemberRoleRepository memberRoleRepository; // 회원가입
	
	@Autowired
	public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder
					 , TokenProvider tokenProvider, ModelMapper modelMapper
					 ,MemberRoleRepository memberRoleRepository) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
		this.modelMapper = modelMapper;
		this.memberRoleRepository = memberRoleRepository;
	}
	
	//====================================로그인[전진이]======================================//
	public Object login(MemberDTO memberDTO) { //사용자가 입력한 아이디 비번
		log.info("[AuthService] Login Start ======================================");
		log.info("[AuthService] {}", memberDTO); //사용자가 입력한 아이디 비번 확인
		
		/* 1. 아이디 조회 */
		Member member = memberRepository.findByMemberId(memberDTO.getMemberId());
		
		if(member == null) {
			throw new LoginFailedException(memberDTO.getMemberId() + "를 찾을 수 없습니다.");
		}
		
		/* 2. 비밀번호 매칭 */
		if(!passwordEncoder.matches(memberDTO.getMemberPassword(), member.getMemberPassword())) {
			log.info("[AuthService] Password Match Fail!");
			throw new LoginFailedException("잘못된 비밀번호 입니다.");
		}
		
		/* 3. 토큰 발급 */
		TokenDTO tokenDTO = tokenProvider.generateTokenDTO(member);
		log.info("[AuthService] tokenDTO {}", tokenDTO);
		
		log.info("[AuthService] Login End ======================================");
		return tokenDTO;
	}

	//====================================회원가입[전진이]======================================//
	
	@Transactional //DML 작업은 Transactional 어노테이션 추가
	public MemberDTO signup(MemberDTO memberDTO) { //이것이 AuthController에서
		log.info("[AuthService] Signup Start ==================================");
		log.info("[AuthService] memberDTO {}", memberDTO); //회원가입때 받은 정보가 나오는지 확인
		
		/* id 중복 유효성 검사...hmm */
		if(memberRepository.findByMemberId(memberDTO.getMemberId()) != null) {
			log.info("[AuthService] Id가 중복됩니다.");
			throw new DuplicatedMemberIdException("중복된 아이디입니다authService.");
		}
		/* email 중복 유효성 검사 */
//		if(memberRepository.findByMemberEmail(memberDTO.getMemberEmail()) != null) {
//			log.info("[AuthService] Email 중복");
//			throw new DuplicatedMemberEmailException("중복된 이메일입니다.");
//		}
		
		
		/* 우선 repository를 통해 쿼리를 날리기 전에 DTO에 담긴 값을 Entity로 옮기자.*/
		Member registMember = modelMapper.map(memberDTO, Member.class);
		
		/* 1. TBL_MEMBER 테이블에 insert */
		registMember.setMemberPassword(passwordEncoder.encode(registMember.getMemberPassword()));
		Member result1 = memberRepository.save(registMember);
		
		/* 2. TBL_MEMBER_ROLE 테이블에 회원별 권한 insert(현재 엔티티에는 회원가입 후 pk값이 없음) */
		/* 2-1. 일반 회원 권한 AuthorityCode값 2 추가 */
		int maxMemberCode = memberRepository.maxMemberCode(); //jpql활용해서 회원번호 max값 추출
		
		MemberRole registMemberRole = new MemberRole(maxMemberCode, 2); //MemberRole에 memberNo, authorityCode두개만있는 생성자 만들었음.
		
		MemberRole result2 = memberRoleRepository.save(registMemberRole);
		
		log.info("[AuthService] Member Insert Result{}" , (result1 != null && result2 != null)? "˗ˋˏ 회원가입성공!! ˎˊ˗" : "˗ˋˏ 회원가입실패... ˎˊ˗");
		
		log.info("[AuthService] Signup End ==================================");
		return memberDTO;
	}


	
	//=========================회원가입시 아이디 중복 체크[전진이]==============================//
//	public MemberDTO selectMyInfo(String memberId) {
//		log.info("[MemberService] getMyInfo Start =======================");
//		
//		Member member = memberRepository.findMemberByMemberId(memberId);
//		log.info("[MemberService] {}", memberId);
//		log.info("[MemberService] {}", member);
//		log.info("[MemberService] getMyInfo End =========================");
//		
//		return modelMapper.map(member, MemberDTO.class);
//	}
	
	public Object selectMyInfo(String memberId) {
		Member member = memberRepository.findMemberByMemberId(memberId);
		/* id 중복 유효성 검사...hmm */
		if(memberRepository.findByMemberId(memberId) != null) {
			log.info("[AuthService] Id가 중복됩니다.");
						
			return true;
		}
		
		return false;
    

	}
//	
//	public Object selectMyInfo(String memberId) {
//		
//        List<Member> memberList = memberRepository.findByduplicatedId(memberId);
//        
//        
//        return memberList.stream().map(pro -> modelMapper.map(pro, MemberDTO.class)).collect(Collectors.toList());
//	}	

}



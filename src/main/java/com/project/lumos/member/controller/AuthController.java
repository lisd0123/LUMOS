package com.project.lumos.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.lumos.common.ResponseDTO;
import com.project.lumos.member.dto.MemberDTO;
import com.project.lumos.member.service.AuthService;
import com.project.lumos.member.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/auth")

public class AuthController {
	private final AuthService authService;
	private final MemberService memberService;
	
	@Autowired
	public AuthController(AuthService authService, MemberService memberService) {
		this.authService = authService;
		this.memberService = memberService;
	}
	
	//=================================로그인[전진이]=========================================//
	@Operation(summary ="로그인 요청", description = "로그인 및 인증이 진행됩니다.", tags = {"AuthController"})
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody MemberDTO memberDTO){
		System.out.println("넘어온 회원가입 데이터: " + memberDTO);
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "˗ˋˏ 로그인 성공 ˎˊ˗", authService.login(memberDTO)));
	}
	
	//=================================회원가입[전진이]=========================================//
	@Operation(summary ="회원가입 요청", description = "회원 가입이 진행됩니다.", tags = {"AuthController"})
	@PostMapping("/signup")
	public ResponseEntity<ResponseDTO> signup(@RequestBody MemberDTO memberDTO){
		System.out.println("넘어온 회원가입 데이터: " + memberDTO);
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.CREATED, "˗ˋˏ 회원가입 성공 ˎˊ˗", authService.signup(memberDTO)));
	}
	
	//=========================회원가입시 아이디 중복 체크[전진이]==============================//
//	@Operation(summary = "아이디 중복 확인을 위한 회원 아이디 조회", description = "아이디가 조회됩니다.", tags = { "AuthController" })
//	@GetMapping("/check")
//	public ResponseEntity<ResponseDTO> selectMemberId(String memberId) {
//		System.out.println("넘어온 회원: " + memberId);
//		return ResponseEntity
//				.ok()
//				.body(new ResponseDTO(HttpStatus.OK, "조회 성공", authService.selectMyInfo(memberId)));
//	}
	@Operation(summary = "아이디 중복 확인을 위한 회원 아이디 조회", description = "아이디가 조회됩니다.", tags = { "AuthController" })
	@GetMapping("/check")
	public ResponseEntity<ResponseDTO> selectMemberId(@RequestParam String memberId) {
		System.out.println("넘어온 회원: " + memberId);
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "조회 성공", authService.selectMyInfo(memberId)));
	}

}

package com.project.lumos.question.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.lumos.common.Criteria;
import com.project.lumos.common.PageDTO;
import com.project.lumos.common.PagingResponseDTO;
import com.project.lumos.common.ResponseDTO;
import com.project.lumos.question.dto.QuestionDTO;
import com.project.lumos.question.dto.QuestionImgDTO;
import com.project.lumos.question.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class QuestionController {

	
	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
	
	private final QuestionService questionService;

	@Autowired
	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
		
	}
	
	@Operation(summary = "문의 등록 요청", description = "1대1 문의 등록이 진행됩니다.", tags = {"QuestionController"})
	@PostMapping(value="/question")
	public ResponseEntity<ResponseDTO> insertQuestion(@ModelAttribute MultipartFile questionImage, String memberId, QuestionDTO questionDTO, QuestionImgDTO questionImgDTO){
		
		int memberCode = questionService.findMemberCode(memberId);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "등록 성공", questionService.insertQuestion(memberCode, questionDTO, questionImage, questionImgDTO)));
	}
	
	@Operation(summary = "회원별 문의 리스트 조회 요청", description = "해당 고객이 작성한 문의 리스트 조회가 진행됩니다.", tags = { "QuestionController" })
    @GetMapping("/question/{memberId}")
    public ResponseEntity<ResponseDTO> selectQuestionListWithPaging(@PathVariable String memberId,  @RequestParam(name="offset", defaultValue="1") String offset) {
        
		int memberCode = questionService.findMemberCode(memberId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);
        cri.setSearchValue(String.valueOf(memberCode));	             // 해당 회원의 문의만을 검색하기 위한 검색 조건
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        int total = (int)questionService.selectQuestionTotal(Integer.valueOf(cri.getSearchValue()));
        
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
        pagingResponseDTO.setData(questionService.selectQuestionListWithPaging(cri));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }
	
	@Operation(summary = "관리자 전체 문의 리스트 조회 요청", description = "모든 고객이 작성한 문의 리스트 조회가 진행됩니다.", tags = { "QuestionController" })
    @GetMapping("/question/list")
    public ResponseEntity<ResponseDTO> questionListWithPaging(@RequestParam(name="offset", defaultValue="1") String offset) {
        
        Criteria cri = new Criteria(Integer.valueOf(offset), 10);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        cri.getSearchValue();	
        
        int total = questionService.questionTotal();
        
        pagingResponseDTO.setData(questionService.questionListWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
        
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }
	
	@Operation(summary = "문의 답변 수정 요청", description = "문의 작성자의 문의 수정이 진행됩니다.", tags = { "QuestionController" })
    @PutMapping("/question/detail/{questionCode}")
    public ResponseEntity<ResponseDTO> updateQuestion(@ModelAttribute MultipartFile questionImage, QuestionDTO questionDTO, QuestionImgDTO questionImgDTO) {

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "문의 수정 성공",  questionService.updateQuestion(questionImage, questionDTO, questionImgDTO)));
    }
	
	@Operation(summary = "문의 삭제 요청", description = "문의 작성자의 문의 삭제가 진행됩니다.", tags = { "QuestionController" })
    @DeleteMapping("/question/delete/{questionCode}")
	public ResponseEntity<ResponseDTO> deleteQuestion(@PathVariable String questionCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "문의 수정 성공",  questionService.deleteQuestion(Integer.valueOf(questionCode))));
    }
	
	@Operation(summary = "문의 상세 페이지 조회 요청", description = "해당 문의의 상세 페이지 조회가 진행됩니다.", tags = { "QuestionController" })
    @GetMapping("/question/detail/{questionCode}")
    public ResponseEntity<ResponseDTO> selectQuestionDetail(@PathVariable String questionCode) {
		
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  questionService.selectQuestionDetail(Integer.valueOf(questionCode))));
    }
	
	@Operation(summary = "관리자 문의 상세 페이지 조회 요청", description = "해당 문의의 상세 페이지가 관리자용 페이지로 조회됩니다.", tags = { "QuestionController" })
    @GetMapping("/question/detail/admin/{questionCode}")
    public ResponseEntity<ResponseDTO> selectQuestionDetailAdmin(@PathVariable String questionCode) {
		
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", questionService.selectQuestionDetailAdmin(Integer.valueOf(questionCode))));
    }
	
	@Operation(summary = "문의 수정 요청", description = "문의 작성자의 문의 수정이 진행됩니다.", tags = { "QuestionController" })
    @PutMapping("/questionAnswer/{questionCode}")
    public ResponseEntity<ResponseDTO> updateAnswer(@RequestBody QuestionDTO questionDTO) {
		
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "문의 수정 성공",  questionService.updateAnswer(questionDTO)));
    }
	
	/* 회원이 쓴 게시물중에 가장 큰 questionCode를 찾아서 반환 -> 인서트 이후 디테일페이지에 전달 */
	@Operation
	@GetMapping("/newQuestionCode/{memberId}")
	public ResponseEntity<ResponseDTO> selectNewQuestionCode(@PathVariable String memberId){

		int memberCode = questionService.findMemberCode(memberId);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "등록된 문의사항 조회 성공",  questionService.selectNewQuestionCode(memberCode)));
	}
}

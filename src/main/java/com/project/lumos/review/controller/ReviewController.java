package com.project.lumos.review.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.project.lumos.review.dto.ImageDTO;
import com.project.lumos.review.dto.ReviewDTO;
import com.project.lumos.review.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/lumos")
public class ReviewController {
	
	private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
	private final ReviewService reviewService;
	
	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@Operation(summary = "상품 리뷰 등록 요청", description = "해당 상품 리뷰 등록이 진행됩니다.", tags = {"ReviewController"})
	@PostMapping("/reviews")
	public ResponseEntity<ResponseDTO> insertProductReview(@ModelAttribute ReviewDTO reviewDTO, ImageDTO imageDTO, MultipartFile reviewImage) {
//	public ResponseEntity<ResponseDTO> insertProductReview(@ModelAttribute ReviewDTO reviewDTO) {
//		log.info("[ReviewController] reviewAndImageDTO: " + imageDTO);
		log.info("[ReviewController] reviewDTO: " + reviewDTO);
		log.info("[ReviewController] imageDTO: " + imageDTO);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "리뷰 입력 성공", reviewService.insertProductReview(reviewDTO, imageDTO, reviewImage)));
	}
	
	@Operation(summary = "상품 리뷰 리스트 조회 요청", description = "해당 상품 리뷰 조회가 진행됩니다", tags = {"ReviewController"})
	@GetMapping("/reviews/{pdCode}")
	public ResponseEntity<ResponseDTO> selectReviewListWithPaging(@PathVariable String pdCode, @RequestParam(name = "offset", defaultValue="1") String offset) {
		log.info("[ReviewController] selectReviewListWithPaging : " + offset);
		log.info("[ReviewController] pdCode : " + pdCode);
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		cri.setSearchValue(pdCode);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		
		int total = (int)reviewService.selectReviewTotal(Integer.valueOf(cri.getSearchValue()));
		
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		pagingResponseDTO.setData(reviewService.selectReviewListWithPaging(cri));
		
		log.info("pagingResponseDTO" + pagingResponseDTO);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
	}
	
	@Operation(summary = "리뷰 상세 페이지 조회 요청", description = "해당 리뷰의 상세 페이지 조회가 진행됩니다", tags = {"ReviewController"})
	@GetMapping("/reviews/product/{reviewCode}")
	public ResponseEntity<ResponseDTO> selectReviewDetail(@PathVariable String reviewCode) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", reviewService.selectReviewDetail(Integer.valueOf(reviewCode))));
	}
	
	@Operation(summary = "리뷰 수정 요청", description = "리뷰 수정이 진행됩니다.", tags = { "ReviewController" })
	@PutMapping("/reviews")
	public ResponseEntity<ResponseDTO> updateProductReview(@RequestBody ReviewDTO reviewDTO) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "리뷰 수정 성공", reviewService.updateProductReview(reviewDTO)));
	}
	

}

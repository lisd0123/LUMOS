package com.project.lumos.review.service;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.lumos.common.Criteria;
import com.project.lumos.member.entity.Member;
import com.project.lumos.review.dto.ImageDTO;
import com.project.lumos.review.dto.ReviewAndMemberDTO;
import com.project.lumos.review.dto.ReviewDTO;
import com.project.lumos.review.entity.Image;
import com.project.lumos.review.entity.Review;
import com.project.lumos.review.entity.ReviewAndMember;
import com.project.lumos.review.repository.ReviewAndMemberRepository;
import com.project.lumos.review.repository.ReviewRepository;
import com.project.lumos.review.repository.ImageRepository;
import com.project.lumos.review.repository.ReviewAndImageRepository;
import com.project.lumos.util.FileUploadUtils;

@Service
public class ReviewService {
	
	private static final Logger log = LoggerFactory.getLogger(ReviewService.class);
	private final ModelMapper modelMapper;
	private final ReviewRepository reviewRepository;
	private final ReviewAndMemberRepository reviewAndMemberRepository;
	private final ReviewAndImageRepository reviewAndImageRepository;
	private final ImageRepository imageRepository;
	
	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	@Value("${image.image-url}")
	private String IMAGE_URL;
	
	@Autowired
	public ReviewService(ModelMapper modelMapper, ReviewRepository reviewRepository, ReviewAndMemberRepository reviewAndMemberRepository, ReviewAndImageRepository reviewAndImageRepository, ImageRepository imageRepository) {
		this.modelMapper = modelMapper;
		this.reviewRepository = reviewRepository;
		this.reviewAndMemberRepository = reviewAndMemberRepository;
		this.reviewAndImageRepository = reviewAndImageRepository;
		this.imageRepository = imageRepository;
		
	}
	
	@Transactional
	public Object insertProductReview(ReviewDTO reviewDTO, ImageDTO imageDTO, MultipartFile reviewImage) {
		log.info("[ReviewService] insertProductReview Start ====================");
		log.info("[ReviewService] ImageDTO: " + imageDTO);
		log.info("[ReviewService] MultipartFile: " + reviewImage);
		
		String imageName = UUID.randomUUID().toString().replace("-", "");
		String replaceFileName = null;
		int result = 0;
		
		int maxReviewCode = imageRepository.findMaxReviewCode();
		
		java.util.Date now = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		String uploadDate = sdf.format(now);
		reviewDTO.setUploadDate(uploadDate);
		
		log.info("[ProductService] insert Image Name: ", imageName);
		
		try {
			replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, reviewImage);
			
			imageDTO.setNewNm(replaceFileName);
			
			log.info("[ProductService] insert Image Name: ", imageDTO);
			
			Review review = modelMapper.map(reviewDTO, Review.class);
			
			int reviewCode = maxReviewCode + 1;
			imageDTO.setOriginNm((reviewImage).getResource().getFilename());
			imageDTO.setReviewCode(reviewCode);
			Image image = modelMapper.map(imageDTO, Image.class);
			
			
			log.info("[ProductService] image: ", image);
			log.info("[ProductService] review: ", review);
			
			imageRepository.save(image);
			
			reviewRepository.save(review);
			

			
			log.info("[ReviewService] insertProductReview End =======================");
			
			result = 1;
		} catch (Exception e) {
			FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			throw new RuntimeException(e);
//			log.info("[review insert] Exception!!");
		}
		
		
		
		return (result > 0) ? "리뷰 입력 성공" : "리뷰 입력 실패";
		
	}
	
	public long selectReviewTotal(int pdCode) {
		log.info("[ReviewService] selectReviewTotal Start =====================");
		
		long result = reviewRepository.countByPdCode(pdCode);
		log.info("[ReviewService] result: {} ", result);
		
		log.info("[ReviewService] selectReviewTotal End=========================");
		
		return result;
	}
	
	public Object selectReviewListWithPaging(Criteria cri) {
		log.info("[ReviewService] selectReviewListWithPaging Start ====================");
		
		int index = cri.getPageNum() - 1;
		int count = cri.getAmount();
		Pageable paging = PageRequest.of(index, count, Sort.by("reviewCode"));
		
		Page<ReviewAndMember> result = reviewAndMemberRepository.findByPdCode(Integer.valueOf(cri.getSearchValue()), paging);
		List<ReviewAndMember> reviewList = (List<ReviewAndMember>)result.getContent();
		Member member = (result.getContent()).get(1).getMember();
		
		for ( ReviewAndMember test : reviewList) {
			log.info("test: {}", test.getMember());
			
		}
		
		log.info("member: {}", member);
		log.info("[ReviewService] reviewList: " + reviewList);
		log.info("[ReviewService] selectReviewListWithPaging End ======================");
		
		return reviewList.stream().map(review -> modelMapper.map(review, ReviewAndMemberDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectReviewDetail(int reviewCode) {
		log.info("[ReviewService] geteReviewDetail Start ==========================");
		
		ReviewAndMember review = reviewAndMemberRepository.findById(reviewCode).get();
		
		log.info("[ReviewService] review: " + review);
		log.info("[ReviewService] getReviewDetail End ==============================");
		
		return modelMapper.map(review, ReviewAndMemberDTO.class);
	}

	@Transactional
	public Object updateProductReview(ReviewDTO reviewDTO) {
		log.info("[ReviewService] updateProductReview Start ===============================");
		
		int result = 0;
		
		try {
			Review review = reviewRepository.findById(reviewDTO.getReviewCode()).get();
			review.setReviewTitle(reviewDTO.getReviewTitle());
			review.setPdGrade(reviewDTO.getPdGrade());
			review.setReviewContent(reviewDTO.getReviewContent());
			log.info("ReviewService] update review: " + review);
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception");
		}
		
		log.info("{ReviewService] updateProductReview End ======================");
		
		return (result > 0) ? "리뷰 수정 성공" : "리뷰 수정 실패";
	}
	
}

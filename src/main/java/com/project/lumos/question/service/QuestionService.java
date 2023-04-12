package com.project.lumos.question.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.project.lumos.member.dto.MemberDTO;
import com.project.lumos.member.entity.Member;
import com.project.lumos.member.repository.MemberRepository;
import com.project.lumos.question.dto.QuestionAndMemberDTO;
import com.project.lumos.question.dto.QuestionDTO;
import com.project.lumos.question.dto.QuestionImgDTO;
import com.project.lumos.question.entity.Question;
import com.project.lumos.question.entity.QuestionAndMember;
import com.project.lumos.question.entity.QuestionImg;
import com.project.lumos.question.repository.QuestionAndImageRepository;
import com.project.lumos.question.repository.QuestionAndMemberRepository;
import com.project.lumos.question.repository.QuestionImgRepository;
import com.project.lumos.question.repository.QuestionMemberAndImgRepository;
import com.project.lumos.question.repository.QuestionRepository;
import com.project.lumos.util.FileUploadUtils;

@Service
public class QuestionService {

	private static final Logger log = LoggerFactory.getLogger(QuestionService.class);
	private final QuestionRepository questionRepository;
	private final QuestionAndMemberRepository questionAndMemberRepository;
	private final ModelMapper modelMapper;
	private final QuestionImgRepository questionImgRepository;
	private final MemberRepository memberRepository;
	private final QuestionMemberAndImgRepository questionMemberAndImgRepository;
	private final QuestionAndImageRepository questionAndImageRepository;
	
	/* 이미지 저장 할 위치 및 응답 할 이미지 주소(WebConfig 설정파일 추가하기) */
    @Value("${image.image-dir-question}")
    private String IMAGE_DIR;
    @Value("${image.image-url-question}")
    private String IMAGE_URL;
	
	@Autowired
	public QuestionService(QuestionAndImageRepository questionAndImageRepository, QuestionMemberAndImgRepository questionMemberAndImgRepository, QuestionAndMemberRepository questionAndMemberRepository, QuestionRepository questionRepository, ModelMapper modelMapper, QuestionImgRepository questionImgRepository, MemberRepository memberRepository) {
		this.questionRepository = questionRepository;
		this.questionAndMemberRepository = questionAndMemberRepository;
		this.modelMapper = modelMapper;
		this.questionImgRepository = questionImgRepository;
		this.memberRepository = memberRepository;
		this.questionMemberAndImgRepository = questionMemberAndImgRepository;
		this.questionAndImageRepository = questionAndImageRepository;
	}
	
	/* 회원 문의 등록 */
	@Transactional
	public Object insertQuestion(int memberCode, QuestionDTO questionDTO, MultipartFile questionImage, QuestionImgDTO questionImgDTO) {
        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0;
        int maxQuestionCode = findMaxQuestionCode();
        
        java.util.Date now = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		String questionDate = sdf.format(now);
		
		questionDTO.setQuestionCreateDate(questionDate);
		questionDTO.setMemberCode(memberCode);
		
        try {
        	 Question insertQuestion = modelMapper.map(questionDTO, Question.class);
	         questionRepository.save(insertQuestion);
	     
	         /* 새로 등록될(아직 생성되지 않은) 게시물 코드 */
	         int questionCode = maxQuestionCode + 1;         
	     
	         if(questionImage != null) {
	        	 replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, questionImage);
	        	 
	        	 questionImgDTO.setOriginalName((questionImage).getResource().getFilename());
	        	 questionImgDTO.setQuestionCode(questionCode);       
	        	 questionImgDTO.setNewName(replaceFileName);
	        	 
	        	 QuestionImg questionimg = modelMapper.map(questionImgDTO, QuestionImg.class);
	        	 questionImgRepository.save(questionimg);   
	         }
	         result = 1;
		} catch (Exception e) {
			 FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
	         throw new RuntimeException(e);
		}
		
        return (result > 0) ? "문의 등록 성공" : "문의 등록 실패";
	}
	
	/* 최신 문의사항코드 추출 */
	@Transactional
	public int findMaxQuestionCode() {
		
		int  maxQuestionCode = questionRepository.findMaxQuestionCode();
		
		return maxQuestionCode;
	}
	
	/* 회원별 문의 내역 조회 */
	public long selectQuestionTotal(int memberCode) {

		long result = questionRepository.countByMemberCode(memberCode);

        return result;
	}

	/* 회원별 문의 내역 조회 페이징 */
	public Object selectQuestionListWithPaging(Criteria cri) {
		
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count, Sort.by(Sort.Direction.DESC, "questionCode"));		
        
        Page<Question> result = questionRepository.findByMemberCode(Integer.valueOf(cri.getSearchValue()), paging);
        List<Question> questionList = (List<Question>)result.getContent();
        
		return questionList.stream().map(question -> modelMapper.map(question, QuestionDTO.class)).collect(Collectors.toList());
	}

	/* 관리자 전체 문의 내역 조회 */
	public int questionTotal() {
		
		int result = questionRepository.findAll().size();
		
        return result;
	}
	
	/* 관리자 전체 문의 내역 조회 페이징 */
	public Object questionListWithPaging(Criteria cri) {
		
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count, Sort.by(Sort.Direction.DESC, "questionCode"));
        
        Page<QuestionAndMember> result = questionAndMemberRepository.findAll(paging);
        List<QuestionAndMember> questionList = (List<QuestionAndMember>)result.getContent();
        
		return questionList.stream().map(question -> modelMapper.map(question, QuestionAndMemberDTO.class)).collect(Collectors.toList());
	}

	/* 회원 문의 상세 조회 */
	public Object selectQuestionDetail(int questionCode) {
		
		Question question = questionRepository.findById(questionCode).get();
		QuestionImg questionImg = questionImgRepository.findByQuestionCode(questionCode);
		QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
		
		Map<String, Object> questionMap = new HashMap<>();
		
		if(questionImg != null) {
			QuestionImgDTO questionImgDTO = modelMapper.map(questionImg, QuestionImgDTO.class);
			questionImgDTO.setNewName(IMAGE_URL + questionImg.getNewName());
			questionMap.put("questionImgDTO", questionImgDTO);
		}
			questionMap.put("questionDTO", questionDTO);
		return questionMap;
	}

	/* 회원 문의 수정 */
	@Transactional
	public Object updateQuestion(MultipartFile questionImage, QuestionDTO questionDTO, QuestionImgDTO questionImgDTO) {
		
//		String replaceFileName = null;
//	    int result = 0;
//		
//		try {
//			Question question = questionRepository.findById(questionDTO.getQuestionCode()).get();
//			int questionCode = question.getQuestionCode();
//			
//			QuestionImg questionImg = questionImgRepository.findByQuestionCode(questionCode);
//			String oriImage = questionImg.getNewName();
//			
//			question.setQuestionTitle(questionDTO.getQuestionTitle());
//			question.setQuestionContent(questionDTO.getQuestionContent());
//			question.setAnswerContent(questionDTO.getAnswerContent());
//			question.setQuestionCategory(questionDTO.getQuestionCategory());
//			
//			questionImg.setNewName(questionImgDTO.getNewName());
//
//			if(questionImage != null){
//                String imageName = UUID.randomUUID().toString().replace("-", "");
//                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, questionImage);
//                questionImg.setNewName(replaceFileName);	
//                boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
//            } else {
//                /* 이미지 변경 없을 시 기존 이름 다시 update */
//            	questionImg.setNewName(oriImage);
//            }
//			result = 1;
//		} catch (Exception e) {
//			log.info("[question update] Exception!!");
//			FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
//            throw new RuntimeException(e);
//		}
//		return (result > 0) ? "문의 수정 성공" : "문의 수정 실패" ;
//	}
	log.info("questionDTO" + questionDTO);
	String replaceFileName = null;
    int result = 0;
    String oriImage = "";
    Question question = questionRepository.findById(questionDTO.getQuestionCode()).get();
    int questionCode = question.getQuestionCode();
    QuestionImg questionImg = questionImgRepository.findByQuestionCode(questionCode);

    if(questionImg != null) {
    	oriImage = questionImg.getNewName();
    }
    
	try {
		Question updateQuestion = modelMapper.map(questionDTO, Question.class);
		questionRepository.save(updateQuestion);
		
		question.setQuestionTitle(questionDTO.getQuestionTitle());
		question.setQuestionContent(questionDTO.getQuestionContent());
		question.setAnswerContent(questionDTO.getAnswerContent());
		question.setQuestionCategory(questionDTO.getQuestionCategory());
		question.setQuestionStatus(questionDTO.getQuestionStatus());
		

		if(questionImage != null){
            String imageName = UUID.randomUUID().toString().replace("-", "");
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, questionImage);
            
            questionImgDTO.setNewName(replaceFileName);
            questionImgDTO.setOriginalName((questionImage).getResource().getFilename());
            questionImgDTO.setQuestionCode(question.getQuestionCode());
            
            QuestionImg updateImg = modelMapper.map(questionImgDTO, QuestionImg.class);
            
            if(questionImg == null) {
            	questionImgRepository.save(updateImg);
            }
            
            if(questionImg != null) {
            	questionImg.setNewName(replaceFileName);	                	
            	boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
            }
            
        } else if(questionImg != null){
            /* 이미지 변경 없을 시 기존 이름 다시 update */
        	questionImg.setNewName(oriImage);
        }
		result = 1;
	} catch (Exception e) {
		log.info("[question update] Exception!!");
		FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
        throw new RuntimeException(e);
	}
	return (result > 0) ? "문의 수정 성공" : "문의 수정 실패" ;
}

	/* 문의 삭제 */
	@Transactional
	public Object deleteQuestion(int questionCode) {
		
		int result = 0;
		
		try { 
			if(questionImgRepository.findByQuestionCode(questionCode).getNewName() != null) {
				String replaceFileName = questionImgRepository.findByQuestionCode(questionCode).getNewName();
				FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
				questionImgRepository.deleteById(questionImgRepository.findByQuestionCode(questionCode).getQuestionImgCode());
			}
			questionRepository.deleteById(questionCode);
			result = 1;
		} catch (Exception e) {
			log.info("[question delete] Exception!!");
		}
		return (result > 0) ? "문의 수정 성공" : "문의 수정 실패" ;
	}

	/* memberId로 memberCode 조회 */
	public int findMemberCode(String memberId) {
		
		int memberCode = memberRepository.findMemberCodeByMemberId(memberId);
		return memberCode;
	}
	
	/* 관리자 답변 수정 */
	@Transactional
	public Object updateAnswer(QuestionDTO questionDTO) {
	    
		int result = 0;
		
		try {
			Question question = questionRepository.findById(questionDTO.getQuestionCode()).get();
			
			question.setQuestionStatus(questionDTO.getQuestionStatus());
			question.setAnswerContent(questionDTO.getAnswerContent());
			Thread.sleep(2000);
			result = 1;
		} catch (Exception e) {
			log.info("[question update] Exception!!");
		}
		return (result > 0) ? "답변 등록 성공" : "답변 등록 실패" ;
	}
	
	/* 관리자 문의 상세 조회 */
	public Object selectQuestionDetailAdmin(int questionCode) {
		
		Question question = questionRepository.findById(questionCode).get();
		QuestionImg questionImg = questionImgRepository.findByQuestionCode(questionCode);
		QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
		
		Member member = memberRepository.findById(question.getMemberCode()).get();
		MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);
		Map<String, Object> questionMap = new HashMap<>();
		
		if(questionImg != null) {
			QuestionImgDTO questionImgDTO = modelMapper.map(questionImg, QuestionImgDTO.class);
			questionImgDTO.setNewName(IMAGE_URL + questionImg.getNewName());
			questionMap.put("questionImgDTO", questionImgDTO);
		}
		questionMap.put("questionDTO", questionDTO);
		questionMap.put("memberDTO", memberDTO);
		return questionMap;
	}	
		

	/* 최신 문의사항 코드 조회 */
	public Object selectNewQuestionCode(int memberCode) {
		
		int newQuestionCode = questionRepository.findNewQuestionCode(memberCode);
		return newQuestionCode;
	}
	
}

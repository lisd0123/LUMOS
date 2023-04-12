package com.project.lumos.order.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
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

import com.project.lumos.common.Criteria;
import com.project.lumos.member.entity.Member;
import com.project.lumos.member.repository.MemberRepository;
import com.project.lumos.order.dto.OrderAndOrderProductAndMemberDTO;
import com.project.lumos.order.dto.OrderDTO;
import com.project.lumos.order.entity.Order;
import com.project.lumos.order.entity.OrderAndOrderProductAndMember;
import com.project.lumos.order.entity.OrderProduct;
import com.project.lumos.order.repository.OrderAndOrderProductAndMemberRepository;
import com.project.lumos.order.repository.OrderProductRepository;
import com.project.lumos.order.repository.OrderRepository;
import com.project.lumos.product.repository.OptionRepository;
import com.project.lumos.product.repository.ProductImageRepository;
import com.project.lumos.product.repository.ProductRepository;
import com.project.lumos.question.dto.QuestionDTO;
import com.project.lumos.question.entity.Question;
import com.project.lumos.question.repository.QuestionRepository;

@Service
public class OrderService {
	
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	private final MemberRepository memberRepository;
	private final OrderAndOrderProductAndMemberRepository orderAndOrderProductAndMemberRepository;
	private final OrderProductRepository orderProductRepository;
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final OptionRepository optionRepository;
	private final ProductImageRepository productImageRepository;
	private final QuestionRepository questionRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public OrderService(MemberRepository memberRepository,
			OrderAndOrderProductAndMemberRepository orderAndOrderProductAndMemberRepository,
			OrderProductRepository orderProductRepository, OrderRepository orderRepository,
			ProductRepository productRepository, OptionRepository optionRepository, 
			ProductImageRepository productImageRepository, 
			QuestionRepository questionRepository, ModelMapper modelMapper) {
		this.memberRepository = memberRepository;
		this.orderAndOrderProductAndMemberRepository = orderAndOrderProductAndMemberRepository;
		this.orderProductRepository = orderProductRepository;
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.optionRepository = optionRepository;
		this.productImageRepository = productImageRepository;
		this.questionRepository = questionRepository;
		this.modelMapper = modelMapper;
	}
	
    @Value("${image.image-url}")
    private String IMAGE_URL;

    /* [주문내역 리스트 조회 for 대시보드] 주문 상태 여부 확인 및 페이징처리 없음 */
	public Object selectOrderList() {
		
		log.info("[OrderService] selectOrderListWithPaging Start ===================================");
		
        List<OrderAndOrderProductAndMember> orderList = orderAndOrderProductAndMemberRepository.findByStOrder("Y");
        
//        log.info("[OrderService] No paging orderList ▶ {}", orderList);
        
        log.info("[OrderService] selectOrderListWithPaging End ===================================");
        
        return orderList.stream().map(order -> modelMapper.map(order, OrderAndOrderProductAndMemberDTO.class)).collect(Collectors.toList());
        
	}
	
	/* [문의내역 리스트 조회 for 대시보드] 페이징처리 없음 */
	public Object selectQuestionList() {
		
		log.info("[OrderService] selectQuestionList Start ===================================");
		
        List<Question> questionList = questionRepository.findByQuestionCategoryAndQuestionStatusLikeOrQuestionCategoryAndQuestionStatusLike("주문취소", "미해결", "환불", "미해결");
        
        log.info("[OrderService] No paging questionList ▶ {}", questionList);
        
        log.info("[OrderService] selectQuestionList End ===================================");
        
        return questionList.stream().map(question -> modelMapper.map(question, QuestionDTO.class)).collect(Collectors.toList());
        
	}
	
	/* [주문내역 리스트 조회] 주문 상태 여부 확인 | 주문 내역 총 갯수 반환 */
	public int selectOrderListTotal() {
		
        log.info("[OrderService] selectOrderListTotal Start ===================================");
        
        List<OrderAndOrderProductAndMember> orderList = orderAndOrderProductAndMemberRepository.findByStOrder("Y");
        
        log.info("[OrderService] orderList.size() ▶ {}", orderList.size());
        
        log.info("[OrderService] selectOrderListTotal End ===================================");
        
        return orderList.size();
        
	}

	/* [주문내역 리스트 조회] 주문 상태 여부 확인 | 페이징 처리된 주문내역 리스트 반환 */
	public Object selectOrderListWithPaging(Criteria cri) {
		
		log.info("[OrderService] selectOrderListWithPaging Start ===================================");
		
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count, Sort.by("orderNum").descending());
	        
        Page<OrderAndOrderProductAndMember> result = orderAndOrderProductAndMemberRepository.findByStOrder("Y", paging);
        List<OrderAndOrderProductAndMember> orderList = (List<OrderAndOrderProductAndMember>)result.getContent();
        
//        log.info("[OrderService] orderList ▶ {}", orderList);
        
        log.info("[OrderService] selectOrderListWithPaging End ===================================");
        
        return orderList.stream().map(order -> modelMapper.map(order, OrderAndOrderProductAndMemberDTO.class)).collect(Collectors.toList());
        
	}

	/* [주문내역 상세 조회] */
	public Object selectOrderByOrderCode(String orderCode) {
		
		log.info("[OrderService] selectOrderByOrderCode Start ===================================");
		
		log.info("[OrderService] orderCode ▶ {}" + orderCode);
		
        OrderAndOrderProductAndMember orderDetail = orderAndOrderProductAndMemberRepository.findByOrderCode(orderCode);
        
		// 이미지 경로 설정
		List<OrderProduct> orderProductList = orderProductRepository.findAllByOrderNumLike(orderDetail.getOrderNum());
		for(OrderProduct orderProduct : orderProductList) {
			orderProduct.setMainImgPath(IMAGE_URL + orderProduct.getMainImgPath());
		}
        
        log.info("[OrderService] orderDetail ▶ {}", orderDetail);
        
        log.info("[OrderService] selectOrderByOrderCode End ===================================");
        
        return modelMapper.map(orderDetail, OrderAndOrderProductAndMemberDTO.class);
        
	}

	/* [주문내역 리스트 검색] 주문 상태 여부 확인 */
	public Object searchOrderList(String searchDate, String searchTitle, String searchValue) {
		
		log.info("[OrderService] searchOrderList Start ===================================");
		
		log.info("[OrderService] @RequestParam ▶ " + searchDate + " & " + searchTitle + " & " + searchValue);
		
		List<OrderAndOrderProductAndMember> searchList = new ArrayList<>();
		
		java.sql.Timestamp sqlTimeStamp = java.sql.Timestamp.valueOf(searchDate + " 00:00:00");
		
		if(searchTitle.equals("non") && searchValue.equals("non")) {
			searchList = orderAndOrderProductAndMemberRepository.findAllByOrderDateGreaterThanEqualAndStOrderLike(sqlTimeStamp, "Y");
		} else {
			switch(searchTitle) {
			case "주문번호" :
				searchList = orderAndOrderProductAndMemberRepository.findAllByOrderDateGreaterThanEqualAndOrderCodeContainingAndStOrderLike(sqlTimeStamp, searchValue, "Y");
				break;
			case "구매자명" :
				List<Member> memberList1 = memberRepository.findByMemberNameContaining(searchValue);
				for(Member member : memberList1) {
					searchList = orderAndOrderProductAndMemberRepository.findByOrderDateGreaterThanEqualAndMemberCodeLikeAndStOrderLike(sqlTimeStamp, member, "Y");
				}
				break;
			case "구매자ID" :
				List<Member> memberList2 = memberRepository.findByMemberIdContaining(searchValue);
				for(Member member : memberList2) {
					searchList = orderAndOrderProductAndMemberRepository.findByOrderDateGreaterThanEqualAndMemberCodeLikeAndStOrderLike(sqlTimeStamp, member, "Y");
				}
				break;
			case "수취인명" :
				searchList = orderAndOrderProductAndMemberRepository.findAllByOrderDateGreaterThanEqualAndCgNmContainingAndStOrderLike(sqlTimeStamp, searchValue, "Y");
				break;
			case "결제방법" :
				searchList = orderAndOrderProductAndMemberRepository.findAllByOrderDateGreaterThanEqualAndPaymentMtContainingAndStOrderLike(sqlTimeStamp, searchValue, "Y");
				break;
			case "배송방법" :
				searchList = orderAndOrderProductAndMemberRepository.findAllByOrderDateGreaterThanEqualAndDeliveryMtContainingAndStOrderLike(sqlTimeStamp, searchValue, "Y");
				break;
			}
		}
        
        log.info("[OrderService] searchOrderList End ===================================");
        
        return searchList.stream().map(order -> modelMapper.map(order, OrderAndOrderProductAndMemberDTO.class)).collect(Collectors.toList());
        
	}

	/* [주문내역 상세 수정] 배송 정보 또는 상태 정보 수정 */
	/*
	 * 방법 1 : 화면단에서 스토어에서 관리되는 시퀀스를 DTO에 담아 가져오기
	 * 방법 2 : 해당 테이블에서 주문번호는 시퀀스와 같은개념이므로 그냥 사용
	 */
	@Transactional
	public Object updateOrderDelivery(String orderCode, OrderDTO orderDTO) {
		
		log.info("[OrderService] updateOrderDelivery Start ===================================");
		
		log.info("[OrderService] update ▶ " + orderDTO);
		log.info("[OrderService] update ▶ " + orderDTO.getDeliveryEnd());
		
		int result = 0;
		
		try {
			Order originOrder = orderRepository.findByOrderCode(orderCode);
			originOrder.setDeliveryCp(orderDTO.getDeliveryCp());
			originOrder.setDeliveryNum(orderDTO.getDeliveryNum());
			
			orderRepository.save(originOrder);
			result = 1;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
        log.info("[OrderService] updateOrderDelivery End ===================================");
        
        return (result > 0) ? "배송 정보 수정 성공" : "배송 정보 수정 실패";
        
	}
	
	/* [주문내역 상세 수정] 배송 정보 수정에 따른 날짜 정보 입력 */
	@Transactional
	public Object updateHistory(String orderCode, String updateKind) {
		
		log.info("[OrderService] updateHistory Start ===================================");
		log.info("[OrderService] updateKind", updateKind);
		
		int result = 0;
		
		try {
			Order originOrder = orderRepository.findByOrderCode(orderCode);
			
			log.info("[OrderService] order.getDeliveryEnd()  ▶ " + originOrder.getDeliveryEnd());
			
			/* util date 생성 및 포맷 (★★★ 9시간 => OffsetDateTime 활용 ★★★) */
			OffsetDateTime now = OffsetDateTime.now();
			ZoneOffset offset = now.getOffset();
			int seconds = offset.getTotalSeconds();
			OffsetDateTime offsetDateTime = null;
			offsetDateTime = now.plusSeconds(seconds);
			
			java.sql.Timestamp sqlTimeStamp = java.sql.Timestamp.valueOf(offsetDateTime.toLocalDateTime());
			
			log.info("[OrderService] offsetDateTime ▶ " + offsetDateTime);
			log.info("[OrderService] sqlTimeStamp ▶ " + sqlTimeStamp);
			
			switch(updateKind) {
				case "발주확인" :
					log.info("[OrderService] 발주확인");
					originOrder.setOrderConf(sqlTimeStamp);
					orderRepository.save(originOrder);
					break;
//				모달로 입력받아 수정 로직 추가
//				case "송장번호입력" : break;
				case "배송출발처리" :
					log.info("[OrderService] 배송출발처리");
					originOrder.setDeliveryStart(sqlTimeStamp);
					orderRepository.save(originOrder);
					break;
				case "배송완료처리" : 
					log.info("[OrderService] 배송완료처리");
					originOrder.setDeliveryEnd(sqlTimeStamp);
					orderRepository.save(originOrder);
					break;
				case "주문취소처리" : 
					log.info("[OrderService] 주문취소처리");
					originOrder.setStClaim("주문취소");
					orderRepository.save(originOrder);
					break;
				case "반품접수" :
					log.info("[OrderService] 반품접수");
					originOrder.setStClaim("반품접수");
					orderRepository.save(originOrder);
					break;
				case "반품완료처리" :
					log.info("[OrderService] 반품완료처리");
					originOrder.setStClaim("반품완료");
					orderRepository.save(originOrder);
					break;
			}
			
			result = 1;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

        log.info("[OrderService] updateHistory End ===================================");
        
        return (result > 0) ? "날짜 수정(입력) 성공" : "날짜 수정(입력) 실패";
        
	}

	/* [회원] memberId로 memberCode 추출 */
	public int findMemberCode(String memberId) {
		
		int memberCode = memberRepository.findMemberCodeByMemberId(memberId);
		return memberCode;
	}

	/* 회원별 주문 내역 리스트 조회 */
	public long orderListTotal(int memberCode) {
		log.info("[OrderService] orderListTotal Start ===================================");
		
		long result = orderRepository.countByMemberCode(memberCode);

        log.info("[OrderService] orderListTotal End ===================================");
        
        return result;
	}


	

}
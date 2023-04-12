package com.project.lumos.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.lumos.common.Criteria;
import com.project.lumos.common.PageDTO;
import com.project.lumos.common.PagingResponseDTO;
import com.project.lumos.common.ResponseDTO;
import com.project.lumos.order.dto.OrderDTO;
import com.project.lumos.order.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	private final OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/* [주문내역 리스트 조회 for 대시보드] 주문 상태 여부 확인 및 페이징처리 없음 */
	@Operation(summary = "[관리자] 대시보드", description = "전체 주문내역 조회", tags = {"OrderController"})
	@GetMapping("/order-dashboard")
	public ResponseEntity<ResponseDTO> selectOrderList() {

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", orderService.selectOrderList()));
		
	}
	
	/* [문의내역 리스트 조회 for 대시보드] 페이징처리 없음 */
	@Operation(summary = "[관리자] 대시보드", description = "전체 주문내역 조회", tags = {"OrderController"})
	@GetMapping("/question-dashboard")
	public ResponseEntity<ResponseDTO> selectQuestionList() {

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", orderService.selectQuestionList()));
		
	}
	
	/* [주문내역 리스트 조회] 주문 상태 여부 확인 */
	@Operation(summary = "[관리자] 주문 내역 조회", description = "전체 주문내역 조회 및 페이징 처리", tags = {"OrderController"})
	@GetMapping("/order-management")
	public ResponseEntity<ResponseDTO> selectOrderListWithPaging(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		
		int total = orderService.selectOrderListTotal();
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(orderService.selectOrderListWithPaging(cri));
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
		
	}
	
	/* [주문내역 리스트 검색] 주문 상태 여부 확인 */
	@Operation(summary = "[관리자] 주문 내역 검색", description = "검색어에 해당하는 주문내역 조회", tags = {"OrderController"})
	@GetMapping("/order-management/search")
	public ResponseEntity<ResponseDTO> searchOrderList(@RequestParam(name="s1") String searchDate, @RequestParam(name="s2") String searchTitle, @RequestParam(name="s3") String searchValue) {
		
		log.info("[OrderController] @RequestParam ▶ " + searchDate + " & " + searchTitle + " & " + searchValue);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  orderService.searchOrderList(searchDate, searchTitle, searchValue)));
		
	}
	
	/* [주문내역 상세 조회] 주문 상태 여부 확인 */
	@Operation(summary = "[관리자] 주문 내역 상세 조회", description = "주문번호별 주문내역 조회", tags = {"OrderController"})
	@GetMapping(value = {"/order-management/{orderCode}"})
	public ResponseEntity<ResponseDTO> selectOrderByOrderCode(@PathVariable String orderCode) {
		
//		log.info("[OrderController] orderCode ▶ " + orderCode);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 내역 상세 조회 성공",  orderService.selectOrderByOrderCode(orderCode)));
		
	}
	
	/* [주문내역 상세 수정] 배송 정보 또는 상태 정보 수정 */
	@Operation(summary = "[관리자] 주문 내역 상세 수정", description = "주문내역 정보 수정", tags = {"OrderController"})
	@PutMapping(value = {"/order-management/{orderCode}/delivery-update"})
	public ResponseEntity<ResponseDTO> updateOrderStatus(@PathVariable String orderCode, @ModelAttribute OrderDTO orderDTO) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 내역 상세 수정 성공",  orderService.updateOrderDelivery(orderCode, orderDTO)));
		
	}
	
	/* [주문내역 상세 수정] 배송 정보 수정에 따른 날짜 정보 입력 */
	@Operation(summary = "[관리자] 배송 처리 내역 수정", description = "배송처리 내역의 날짜 정보 수정(입력)", tags = {"OrderController"})
	@PutMapping(value = {"/order-management/{orderCode}/history-update/{updateKind}"})
	public ResponseEntity<ResponseDTO> updateHistory(@PathVariable String orderCode, @PathVariable String updateKind) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 내역 날짜 수정 성공",  orderService.updateHistory(orderCode, updateKind)));
		
	}
	
	/* [주문내역 리스트 조회] 주문 상태 여부 확인 */
	@Operation(summary = "[회원] 주문 내역 조회", description = "회원 주문내역 조회 및 페이징 처리", tags = {"OrderController"})
	@GetMapping("/orderList/{memberid}")
	public ResponseEntity<ResponseDTO> orderListWithPaging(@PathVariable String memberId, @RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[OrderController] orderListWithPaging : " + offset);
		log.info("[OrderController] memberId : " + memberId);
		int memberCode = orderService.findMemberCode(memberId);
		
		log.info("[QuestionController] memberCode : " + memberCode);
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		cri.setSearchValue(String.valueOf(memberCode));	
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		
		int total = (int)orderService.orderListTotal(Integer.valueOf(cri.getSearchValue()));
		pagingResponseDTO.setData(orderService.selectOrderListWithPaging(cri));
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		log.info("[QuestionController] pagingResponseDTO : " + pagingResponseDTO);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
	}
}
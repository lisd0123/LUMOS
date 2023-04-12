package com.project.lumos.cart.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lumos.cart.service.CartService;
import com.project.lumos.common.ResponseDTO;
import com.project.lumos.order.controller.OrderController;
import com.project.lumos.order.dto.OrderDTO;
import com.project.lumos.order.dto.OrderProductDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class CartController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	private final CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	/* [장바구니 생성 및 상품 추가] 장바구니가 없는 경우(N) 생성, 있는 경우 추가 */
	// ★★★ @RequestBody => 안 달면 값 안넘어옴
	@Operation(summary = "[회원] 장바구니 생성", description = "장바구니 조회 또는 생성 후 상품 추가", tags = {"CartController"})
	@PostMapping("/cart/{memberId}")
	public ResponseEntity<ResponseDTO> insertCart(@PathVariable String memberId, @ModelAttribute OrderProductDTO orderProductDTO) {
		
		log.info("[CartController] 장바구니 추가 memberId ▶ " + memberId);
		log.info("[CartController] 장바구니 추가 orderProductDTO ▶ " + orderProductDTO);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "장바구니 상품 추가 성공", cartService.createAndUpdateCart(memberId, orderProductDTO)));
		
	}
	
	/* [장바구니 조회] */
	@Operation(summary = "[회원] 장바구니 조회", description = "장바구니 조회", tags = {"CartController"})
	@GetMapping("/cart/{memberId}")
	public ResponseEntity<ResponseDTO> selectCart(@PathVariable String memberId) {
		
		log.info("[CartController] memberId ▶ " + memberId);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "장바구니 조회 성공", cartService.selectCart(memberId)));
		
	}

	/* [장바구니 상품 수량 수정] */
	@Operation(summary = "[회원] 주문 수량 수정", description = "장바구니 주문 수량 수정", tags = {"CartController"})
	@PutMapping(value = {"/cart/{memberId}/amount-update/{opCode}/{amount}"})
	public ResponseEntity<ResponseDTO> updateOrderProductAmount(@PathVariable String memberId, @PathVariable int opCode, @PathVariable int amount) {
		
		log.info("[OrderController] updateOrderProductAmount memberId ▶ " + memberId);
		log.info("[OrderController] updateOrderProductAmount opCode ▶ " + opCode);
		log.info("[OrderController] updateOrderProductAmount amount ▶ " + amount);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "장바구니 제품 수량 수정 성공",  cartService.updateOrderProductAmount(memberId, opCode, amount)));
		
	}
	
	/* [장바구니 상품 삭제] */
	@Operation(summary = "[회원] 주문 상품 삭제", description = "장바구니 주문 상품 삭제", tags = {"CartController"})
	@DeleteMapping(value = {"/cart/{memberId}/item-delete/{orderPdNum}"})
	public ResponseEntity<ResponseDTO> deleteOrderProduct(@PathVariable String memberId, @PathVariable String orderPdNum) {
		
		log.info("[OrderController] deleteOrderProduct memberId ▶ " + memberId);
		log.info("[OrderController] deleteOrderProduct orderPdNum ▶ " + orderPdNum);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "장바구니 제품 삭제 성공",  cartService.deleteOrderProduct(memberId, orderPdNum)));
		
	}
	
	/* [장바구니 상품 구매] */
	@Operation(summary = "[회원] 주문 결제", description = "결제 API 성공 시 장바구니 상태값 변경 및 재고수량 변경", tags = {"CartController"})
	@PutMapping(value = {"/cart/{orderCode}/purchase"})
	public ResponseEntity<ResponseDTO> purcahseOrder(@PathVariable String orderCode, @ModelAttribute OrderDTO orderDTO) {
		
		log.info("[OrderController] purcahseOrder orderCode ▶ " + orderCode);
		log.info("[OrderController] purcahseOrder orderDTO ▶ " + orderDTO);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "장바구니 제품 구매 성공",  cartService.purcahseOrder(orderCode, orderDTO)));
		
	}
	
}
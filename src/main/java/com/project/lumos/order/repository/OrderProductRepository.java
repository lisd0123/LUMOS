package com.project.lumos.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.order.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

	/* 주문 번호로 장바구니 제품 정보 조회 */
	OrderProduct findByOrderNumAndOpCodeLike(int orderNum, int opCode);

	/* 장바구니에 있는 기존 제품과 같은지 확인 */
	OrderProduct findByOrderNumAndOpCodeAndPdCodeLike(int orderNum, int opCode, int opCode2);

	/* 옵션 재고 수량 수정을 위한 장바구니 제품 정보들 확인 */
	List<OrderProduct> findAllByOrderNumLike(int orderNum);

}
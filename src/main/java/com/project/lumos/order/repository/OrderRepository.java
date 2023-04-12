package com.project.lumos.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.lumos.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	/* createAndUpdateCart() */
//	Order findByMemberCodeAndStOrder(int memberCode, @Param("stOrder") String string);
	
//	SELECT *
//	FROM TBL_ORDER
//	WHERE MEMBER_CODE = 3
//	AND ST_ORDER = 'Y';
	
//	@Query("SELECT a FROM Order a WHERE a.memberCode = ?1 AND a.stOrder = ?2")
//	Order findByMemberCodeLikeAndStOrderLike(@Param("memberCode")int memberCode, @Param("stOrder")String stOrder);
	
	Order findByMemberCodeLikeAndStOrderLike(int memberCode, String stOrder);
	
	/* createAndUpdateCart() */
	@Query("SELECT MAX(SUBSTR(a.orderCode, 10, 8)) FROM Order a WHERE SUBSTR(a.orderCode, 1, 8) LIKE REPLACE('20'||SYSDATE, '/', '')")
	String todayMaxOrderNum();

	/* createAndUpdateCart() */
//	@Query("SELECT a.orderNum FROM Order a WHERE a.orderCode = ?1")
//	Order findByOrderCode(String orderCode);
//	int findByOrderCode(String orderCode);
//	↑
//	Null return value from advice does not match primitive return type for: 
//	public abstract int com.project.lumos.order.repository.OrderRepository.findByOrderCode(java.lang.String)
	
	Order findByOrderCode(String orderCode);

	/* 구매시 기존 장바구니 정보 조회 */
	Order findByOrderCodeLike(String orderCode);

	/* 회원별 주문 내역 조회 */
	long countByMemberCode(int memberCode);

}
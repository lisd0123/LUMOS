package com.project.lumos.order.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.member.entity.Member;
import com.project.lumos.order.entity.OrderAndOrderProductAndMember;

public interface OrderAndOrderProductAndMemberRepository extends JpaRepository<OrderAndOrderProductAndMember, Integer> {

	List<OrderAndOrderProductAndMember> findByStOrder(String c);

	Page<OrderAndOrderProductAndMember> findByStOrder(String string, Pageable paging);

	OrderAndOrderProductAndMember findByOrderCode(String orderCode);

	List<OrderAndOrderProductAndMember> findByOrderCodeContaining(String search);

	/* 날짜 검색 */
	List<OrderAndOrderProductAndMember> findAllByOrderDateGreaterThanEqualAndStOrderLike(Timestamp sqlTimeStamp,
			String string);

	/* 날짜 + 주문번호 검색 */
	List<OrderAndOrderProductAndMember> findAllByOrderDateGreaterThanEqualAndOrderCodeContainingAndStOrderLike(
			Timestamp sqlTimeStamp, String searchValue, String string);

	/* 날짜 + 구매자명 */
	List<OrderAndOrderProductAndMember> findByOrderDateGreaterThanEqualAndMemberCodeLikeAndStOrderLike(
			Timestamp sqlTimeStamp, Member member, String string);

	/* 날짜 + 구매자ID */
	List<OrderAndOrderProductAndMember> findAllByOrderDateGreaterThanEqualAndCgNmContainingAndStOrderLike(
			Timestamp sqlTimeStamp, String searchValue, String string);

	/* 날짜 + 결제방법 */
	List<OrderAndOrderProductAndMember> findAllByOrderDateGreaterThanEqualAndPaymentMtContainingAndStOrderLike(
			Timestamp sqlTimeStamp, String searchValue, String string);

	/* 날짜 + 배송방법 */
	List<OrderAndOrderProductAndMember> findAllByOrderDateGreaterThanEqualAndDeliveryMtContainingAndStOrderLike(
			Timestamp sqlTimeStamp, String searchValue, String string);

}
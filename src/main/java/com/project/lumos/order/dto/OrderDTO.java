package com.project.lumos.order.dto;

import java.util.Date;

import com.project.lumos.member.dto.MemberDTO;

public class OrderDTO {
	
//	ORDER_NUM		NUMBER				주문식별번호
//	ORDER_CODE		VARCHAR2(50 BYTE)	주문코드
//	ORDER_DATE		DATE				주문일
//	PAYMENT_MT		VARCHAR2(50 BYTE)	결제 방법
//	ORDER_PC		NUMBER				주문 금액
//	DELIVERY_PC		NUMBER				배송비
//	TOTAL_PC		NUMBER				합계 금액
//	DELIVERY_MT		VARCHAR2(50 BYTE)	배송 방법
//	DELIVERY_CP		VARCHAR2(50 BYTE)	택배사
//	ST_ORDER		VARCHAR2(50 BYTE)	주문상태
//	CG_NM			VARCHAR2(50 BYTE)	수하인 이름
//	CG_PH			VARCHAR2(50 BYTE)	수하인 연락처
//	CG_ADS_NUM		VARCHAR2(50 BYTE)	배송지 우편번호
//	CG_ADS			VARCHAR2(255 BYTE)	배송지 주소
//	CG_ADS_DETAIL	VARCHAR2(255 BYTE)	배송지 주소 상세
//	MEMBER_CODE		NUMBER				회원코드
//	DELIVERY_NUM	VARCHAR2(50 BYTE)	송장번호
//	ST_CLAIM		VARCHAR2(50 BYTE)	클레임 상태
//	ST_PAYMENT		VARCHAR2(50 BYTE)	결제 상태
//	ORDER_CONF		DATE				발주 확인일
//	DELIVERY_START	DATE				배송 출발일
//	DELIVERY_END	DATE				배송 완료일
//	PURCHASE_CONF	DATE				구매 확정일
//	DELIVERY_MSG	VARCHAR2(255 BYTE)	배송 메세지

	private int orderNum;
	private String orderCode;
	private Date orderDate;
	private String paymentMt;
	private int orderPc;
	private int deliveryPc;
	private int totalPc;
	private String deliveryMt;
	private String deliveryCp;
	private String stOrder;
	private String cgNm;
	private String cgPh;
	private String cgAdsNum;
	private String cgAds;
	private String cgAdsDetail;
	private int memberCode;
	private String deliveryNum;
	private String stClaim;
	private String stPayment;
	private Date orderConf;
	private Date deliveryStart;
	private Date deliveryEnd;
	private Date purchaseConf;
	private String deliveryMsg;
	
	public OrderDTO() {
	}

	public OrderDTO(int orderNum, String orderCode, Date orderDate, String paymentMt, int orderPc, int deliveryPc,
			int totalPc, String deliveryMt, String deliveryCp, String stOrder, String cgNm, String cgPh,
			String cgAdsNum, String cgAds, String cgAdsDetail, int memberCode, String deliveryNum, String stClaim,
			String stPayment, Date orderConf, Date deliveryStart, Date deliveryEnd, Date purchaseConf,
			String deliveryMsg) {
		this.orderNum = orderNum;
		this.orderCode = orderCode;
		this.orderDate = orderDate;
		this.paymentMt = paymentMt;
		this.orderPc = orderPc;
		this.deliveryPc = deliveryPc;
		this.totalPc = totalPc;
		this.deliveryMt = deliveryMt;
		this.deliveryCp = deliveryCp;
		this.stOrder = stOrder;
		this.cgNm = cgNm;
		this.cgPh = cgPh;
		this.cgAdsNum = cgAdsNum;
		this.cgAds = cgAds;
		this.cgAdsDetail = cgAdsDetail;
		this.memberCode = memberCode;
		this.deliveryNum = deliveryNum;
		this.stClaim = stClaim;
		this.stPayment = stPayment;
		this.orderConf = orderConf;
		this.deliveryStart = deliveryStart;
		this.deliveryEnd = deliveryEnd;
		this.purchaseConf = purchaseConf;
		this.deliveryMsg = deliveryMsg;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getPaymentMt() {
		return paymentMt;
	}

	public void setPaymentMt(String paymentMt) {
		this.paymentMt = paymentMt;
	}

	public int getOrderPc() {
		return orderPc;
	}

	public void setOrderPc(int orderPc) {
		this.orderPc = orderPc;
	}

	public int getDeliveryPc() {
		return deliveryPc;
	}

	public void setDeliveryPc(int deliveryPc) {
		this.deliveryPc = deliveryPc;
	}

	public int getTotalPc() {
		return totalPc;
	}

	public void setTotalPc(int totalPc) {
		this.totalPc = totalPc;
	}

	public String getDeliveryMt() {
		return deliveryMt;
	}

	public void setDeliveryMt(String deliveryMt) {
		this.deliveryMt = deliveryMt;
	}

	public String getDeliveryCp() {
		return deliveryCp;
	}

	public void setDeliveryCp(String deliveryCp) {
		this.deliveryCp = deliveryCp;
	}

	public String getStOrder() {
		return stOrder;
	}

	public void setStOrder(String stOrder) {
		this.stOrder = stOrder;
	}

	public String getCgNm() {
		return cgNm;
	}

	public void setCgNm(String cgNm) {
		this.cgNm = cgNm;
	}

	public String getCgPh() {
		return cgPh;
	}

	public void setCgPh(String cgPh) {
		this.cgPh = cgPh;
	}

	public String getCgAdsNum() {
		return cgAdsNum;
	}

	public void setCgAdsNum(String cgAdsNum) {
		this.cgAdsNum = cgAdsNum;
	}

	public String getCgAds() {
		return cgAds;
	}

	public void setCgAds(String cgAds) {
		this.cgAds = cgAds;
	}

	public String getCgAdsDetail() {
		return cgAdsDetail;
	}

	public void setCgAdsDetail(String cgAdsDetail) {
		this.cgAdsDetail = cgAdsDetail;
	}

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public String getDeliveryNum() {
		return deliveryNum;
	}

	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
	}

	public String getStClaim() {
		return stClaim;
	}

	public void setStClaim(String stClaim) {
		this.stClaim = stClaim;
	}

	public String getStPayment() {
		return stPayment;
	}

	public void setStPayment(String stPayment) {
		this.stPayment = stPayment;
	}

	public Date getOrderConf() {
		return orderConf;
	}

	public void setOrderConf(Date orderConf) {
		this.orderConf = orderConf;
	}

	public Date getDeliveryStart() {
		return deliveryStart;
	}

	public void setDeliveryStart(Date deliveryStart) {
		this.deliveryStart = deliveryStart;
	}

	public Date getDeliveryEnd() {
		return deliveryEnd;
	}

	public void setDeliveryEnd(Date deliveryEnd) {
		this.deliveryEnd = deliveryEnd;
	}

	public Date getPurchaseConf() {
		return purchaseConf;
	}

	public void setPurchaseConf(Date purchaseConf) {
		this.purchaseConf = purchaseConf;
	}

	public String getDeliveryMsg() {
		return deliveryMsg;
	}

	public void setDeliveryMsg(String deliveryMsg) {
		this.deliveryMsg = deliveryMsg;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderNum=" + orderNum + ", orderCode=" + orderCode + ", orderDate=" + orderDate
				+ ", paymentMt=" + paymentMt + ", orderPc=" + orderPc + ", deliveryPc=" + deliveryPc + ", totalPc="
				+ totalPc + ", deliveryMt=" + deliveryMt + ", deliveryCp=" + deliveryCp + ", stOrder=" + stOrder
				+ ", cgNm=" + cgNm + ", cgPh=" + cgPh + ", cgAdsNum=" + cgAdsNum + ", cgAds=" + cgAds + ", cgAdsDetail="
				+ cgAdsDetail + ", memberCode=" + memberCode + ", deliveryNum=" + deliveryNum + ", stClaim=" + stClaim
				+ ", stPayment=" + stPayment + ", orderConf=" + orderConf + ", deliveryStart=" + deliveryStart
				+ ", deliveryEnd=" + deliveryEnd + ", purchaseConf=" + purchaseConf + ", deliveryMsg=" + deliveryMsg
				+ "]";
	}
	
}
package com.project.lumos.order.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.lumos.member.entity.Member;

/* 관리자 혹은 주문자가 주문내역 리스트를 편히 보기위한 조회용 엔티티 */
@Entity
@Table(name = "TBL_ORDER")
public class OrderAndOrderProductAndMember {

	@Id
	@Column(name = "ORDER_NUM")
	private int orderNum;
	
	@Column(name = "ORDER_CODE")
	private String orderCode;
	
//	@CreationTimestamp
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	
	@Column(name = "PAYMENT_MT")
	private String paymentMt;
	
	@Column(name = "ORDER_PC")
	private int orderPc;
	
	@Column(name = "DELIVERY_PC")
	private int deliveryPc;
	
	@Column(name = "TOTAL_PC")
	private int totalPc;
	
	@Column(name = "DELIVERY_MT")
	private String deliveryMt;
	
	@Column(name = "DELIVERY_CP")
	private String deliveryCp;
	
	@Column(name = "ST_ORDER")
	private String stOrder;
	
	@Column(name = "CG_NM")
	private String cgNm;
	
	@Column(name = "CG_PH")
	private String cgPh;
	
	@Column(name = "CG_ADS_NUM")
	private String cgAdsNum;
	
	@Column(name = "CG_ADS")
	private String cgAds;
	
	@Column(name = "CG_ADS_DETAIL")
	private String cgAdsDetail;
	
	/* 주문 페이지에서 주문자의 주소등을 긁어와야함 */
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE", insertable = false, updatable = false)
	private Member memberCode;
	
	@Column(name = "DELIVERY_NUM")
	private String deliveryNum;
	
	@Column(name = "ST_CLAIM")
	private String stClaim;
	
	@Column(name = "ST_PAYMENT")
	private String stPayment;
	
	@Column(name = "ORDER_CONF")
	private Date orderConf;
	
	@Column(name = "DELIVERY_START")
	private Date deliveryStart;
	
	@Column(name = "DELIVERY_END")
	private Date deliveryEnd;
	
	@Column(name = "PURCHASE_CONF")
	private Date purchaseConf;
	
	@Column(name = "DELIVERY_MSG")
	private String deliveryMsg;
	
	/* 주문 : 주문제품정보 = 1 : N */
	@OneToMany
	@JoinColumn(name = "ORDER_NUM")
	private List<OrderProduct> orderProductList;
	
	public OrderAndOrderProductAndMember() {
	}

	public OrderAndOrderProductAndMember(int orderNum, String orderCode, Date orderDate, String paymentMt, int orderPc,
			int deliveryPc, int totalPc, String deliveryMt, String deliveryCp, String stOrder, String cgNm, String cgPh,
			String cgAdsNum, String cgAds, String cgAdsDetail, Member memberCode, String deliveryNum, String stClaim,
			String stPayment, Date orderConf, Date deliveryStart, Date deliveryEnd, Date purchaseConf,
			String deliveryMsg, List<OrderProduct> orderProductList) {
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
		this.orderProductList = orderProductList;
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

	public Member getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(Member memberCode) {
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

	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}

	@Override
	public String toString() {
		return "OrderAndOrderProduct [orderNum=" + orderNum + ", orderCode=" + orderCode + ", orderDate=" + orderDate
				+ ", paymentMt=" + paymentMt + ", orderPc=" + orderPc + ", deliveryPc=" + deliveryPc + ", totalPc="
				+ totalPc + ", deliveryMt=" + deliveryMt + ", deliveryCp=" + deliveryCp + ", stOrder=" + stOrder
				+ ", cgNm=" + cgNm + ", cgPh=" + cgPh + ", cgAdsNum=" + cgAdsNum + ", cgAds=" + cgAds + ", cgAdsDetail="
				+ cgAdsDetail + ", memberCode=" + memberCode + ", deliveryNum=" + deliveryNum + ", stClaim=" + stClaim
				+ ", stPayment=" + stPayment + ", orderConf=" + orderConf + ", deliveryStart=" + deliveryStart
				+ ", deliveryEnd=" + deliveryEnd + ", purchaseConf=" + purchaseConf + ", deliveryMsg=" + deliveryMsg
				+ ", orderProductList=" + orderProductList + "]";
	}

}
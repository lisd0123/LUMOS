package com.project.lumos.order.dto;

import com.project.lumos.product.entity.Option;
import com.project.lumos.product.entity.Product;

public class OrderProductAndPdAndOpDTO {

	private int orderPdNum;
	private int orderAmount;
	/* 주문정보 테이블의 존재가 선수행 되어야 함 */
	private int orderNum;
	private String mainImgPath;
	private int pdCode;
	private int opCode;
	private String pdName;
	private String opName;
	private int pdPc;
	
	/* 장바구니에 담을 상품정보 by 상품코드 */
	/* 장바구니 N : 상품 1 */
	private Product pdInfo;
	/* 장바구니에 담을 사용자가 선택한 옵션정보 by 옵션코드 */
	/* 장바구니 N : 옵션 1 */
	private Option opInfo;
	
	public OrderProductAndPdAndOpDTO() {
	}

	public OrderProductAndPdAndOpDTO(int orderPdNum, int orderAmount, int orderNum, String mainImgPath, int pdCode,
			int opCode, String pdName, String opName, int pdPc, Product pdInfo, Option opInfo) {
		this.orderPdNum = orderPdNum;
		this.orderAmount = orderAmount;
		this.orderNum = orderNum;
		this.mainImgPath = mainImgPath;
		this.pdCode = pdCode;
		this.opCode = opCode;
		this.pdName = pdName;
		this.opName = opName;
		this.pdPc = pdPc;
		this.pdInfo = pdInfo;
		this.opInfo = opInfo;
	}

	public int getOrderPdNum() {
		return orderPdNum;
	}

	public void setOrderPdNum(int orderPdNum) {
		this.orderPdNum = orderPdNum;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getMainImgPath() {
		return mainImgPath;
	}

	public void setMainImgPath(String mainImgPath) {
		this.mainImgPath = mainImgPath;
	}

	public int getPdCode() {
		return pdCode;
	}

	public void setPdCode(int pdCode) {
		this.pdCode = pdCode;
	}

	public int getOpCode() {
		return opCode;
	}

	public void setOpCode(int opCode) {
		this.opCode = opCode;
	}

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public int getPdPc() {
		return pdPc;
	}

	public void setPdPc(int pdPc) {
		this.pdPc = pdPc;
	}

	public Product getPdInfo() {
		return pdInfo;
	}

	public void setPdInfo(Product pdInfo) {
		this.pdInfo = pdInfo;
	}

	public Option getOpInfo() {
		return opInfo;
	}

	public void setOpInfo(Option opInfo) {
		this.opInfo = opInfo;
	}

	@Override
	public String toString() {
		return "OrderProductAndPdAndOpDTO [orderPdNum=" + orderPdNum + ", orderAmount=" + orderAmount + ", orderNum="
				+ orderNum + ", mainImgPath=" + mainImgPath + ", pdCode=" + pdCode + ", opCode=" + opCode + ", pdName="
				+ pdName + ", opName=" + opName + ", pdPc=" + pdPc + ", pdInfo=" + pdInfo + ", opInfo=" + opInfo + "]";
	}

}
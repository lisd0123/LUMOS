package com.project.lumos.shop.dto;

public class ShopDTO {

//	SHOP_NM			VARCHAR2(100 BYTE)	쇼핑몰명
//	SHOP_WEB_ADS	VARCHAR2(255 BYTE)	쇼핑몰 주소
//	SHOP_EMAIL		VARCHAR2(40 BYTE)	쇼핑몰 이메일
//	SHOP_DESC		VARCHAR2(1000 BYTE)	쇼핑몰 소개
//	OM_ST			VARCHAR2(10 BYTE)	통신판매업 신고여부
//	OM_NUM			VARCHAR2(100 BYTE)	통신판매신고 번호
//	CS_TEL			VARCHAR2(100 BYTE)	고객센터 번호
//	CS_EMAIL		VARCHAR2(100 BYTE)	고객센터 이메일
//	CS_HOUR			VARCHAR2(100 BYTE)	고객센터 운영시간
//	PIC_NM			VARCHAR2(100 BYTE)	개인정보보호 책임자
//	PIC_TEL			VARCHAR2(100 BYTE)	개인정보보호 책임자 연락처
//	PIC_EMAIL		VARCHAR2(100 BYTE)	개인정보보호 책임자 이메일

	private String shopNm;
	private String shopWebAds;
	private String shopEmail;
	private String shopDesc;
	private String omSt;
	private String omNum;
	private String csTel;
	private String csEmail;
	private String csHour;
	private String picNm;
	private String picTel;
	private String picEmail;
	
	public ShopDTO() {
	}

	public ShopDTO(String shopNm, String shopWebAds, String shopEmail, String shopDesc, String omSt, String omNum,
			String csTel, String csEmail, String csHour, String picNm, String picTel, String picEmail) {
		this.shopNm = shopNm;
		this.shopWebAds = shopWebAds;
		this.shopEmail = shopEmail;
		this.shopDesc = shopDesc;
		this.omSt = omSt;
		this.omNum = omNum;
		this.csTel = csTel;
		this.csEmail = csEmail;
		this.csHour = csHour;
		this.picNm = picNm;
		this.picTel = picTel;
		this.picEmail = picEmail;
	}

	public String getShopNm() {
		return shopNm;
	}

	public void setShopNm(String shopNm) {
		this.shopNm = shopNm;
	}

	public String getShopWebAds() {
		return shopWebAds;
	}

	public void setShopWebAds(String shopWebAds) {
		this.shopWebAds = shopWebAds;
	}

	public String getShopEmail() {
		return shopEmail;
	}

	public void setShopEmail(String shopEmail) {
		this.shopEmail = shopEmail;
	}

	public String getShopDesc() {
		return shopDesc;
	}

	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}

	public String getOmSt() {
		return omSt;
	}

	public void setOmSt(String omSt) {
		this.omSt = omSt;
	}

	public String getOmNum() {
		return omNum;
	}

	public void setOmNum(String omNum) {
		this.omNum = omNum;
	}

	public String getCsTel() {
		return csTel;
	}

	public void setCsTel(String csTel) {
		this.csTel = csTel;
	}

	public String getCsEmail() {
		return csEmail;
	}

	public void setCsEmail(String csEmail) {
		this.csEmail = csEmail;
	}

	public String getCsHour() {
		return csHour;
	}

	public void setCsHour(String csHour) {
		this.csHour = csHour;
	}

	public String getPicNm() {
		return picNm;
	}

	public void setPicNm(String picNm) {
		this.picNm = picNm;
	}

	public String getPicTel() {
		return picTel;
	}

	public void setPicTel(String picTel) {
		this.picTel = picTel;
	}

	public String getPicEmail() {
		return picEmail;
	}

	public void setPicEmail(String picEmail) {
		this.picEmail = picEmail;
	}

	@Override
	public String toString() {
		return "ShopDTO [shopNm=" + shopNm + ", shopWebAds=" + shopWebAds + ", shopEmail=" + shopEmail + ", shopDesc="
				+ shopDesc + ", omSt=" + omSt + ", omNum=" + omNum + ", csTel=" + csTel + ", csEmail=" + csEmail
				+ ", csHour=" + csHour + ", picNm=" + picNm + ", picTel=" + picTel + ", picEmail=" + picEmail + "]";
	}

}
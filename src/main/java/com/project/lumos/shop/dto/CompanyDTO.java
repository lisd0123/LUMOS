package com.project.lumos.shop.dto;

public class CompanyDTO {

//	BSR_NUM			NUMBER				사업자 등록 번호
//	CP_NM			VARCHAR2(100 BYTE)	상호
//	RP_NM			VARCHAR2(100 BYTE)	대표자 성명
//	CP_TEL			VARCHAR2(100 BYTE)	대표 전화
//	BS_TYPE			VARCHAR2(100 BYTE)	업태
//	BS_ITEM			VARCHAR2(100 BYTE)	종목
//	CP_ADS_NUM		VARCHAR2(100 BYTE)	사업장 우편번호
//	CP_ADS			VARCHAR2(100 BYTE)	사업장 주소
//	CP_ADS_DETAIL	VARCHAR2(100 BYTE)	사업장 상세 주소
//	CP_EMAIL		VARCHAR2(100 BYTE)	이메일

	private int bsrNum;
	private String cpNm;
	private String rpNm;
	private String cpTel;
	private String bsType;
	private String bsItem;
	private String cpAdsNum;
	private String cpAds;
	private String cpAdsDetail;
	private String cpEmail;
	
	public CompanyDTO() {
	}

	public CompanyDTO(int bsrNum, String cpNm, String rpNm, String cpTel, String bsType, String bsItem, String cpAdsNum,
			String cpAds, String cpAdsDetail, String cpEmail) {
		this.bsrNum = bsrNum;
		this.cpNm = cpNm;
		this.rpNm = rpNm;
		this.cpTel = cpTel;
		this.bsType = bsType;
		this.bsItem = bsItem;
		this.cpAdsNum = cpAdsNum;
		this.cpAds = cpAds;
		this.cpAdsDetail = cpAdsDetail;
		this.cpEmail = cpEmail;
	}

	public int getBsrNum() {
		return bsrNum;
	}

	public void setBsrNum(int bsrNum) {
		this.bsrNum = bsrNum;
	}

	public String getCpNm() {
		return cpNm;
	}

	public void setCpNm(String cpNm) {
		this.cpNm = cpNm;
	}

	public String getRpNm() {
		return rpNm;
	}

	public void setRpNm(String rpNm) {
		this.rpNm = rpNm;
	}

	public String getCpTel() {
		return cpTel;
	}

	public void setCpTel(String cpTel) {
		this.cpTel = cpTel;
	}

	public String getBsType() {
		return bsType;
	}

	public void setBsType(String bsType) {
		this.bsType = bsType;
	}

	public String getBsItem() {
		return bsItem;
	}

	public void setBsItem(String bsItem) {
		this.bsItem = bsItem;
	}

	public String getCpAdsNum() {
		return cpAdsNum;
	}

	public void setCpAdsNum(String cpAdsNum) {
		this.cpAdsNum = cpAdsNum;
	}

	public String getCpAds() {
		return cpAds;
	}

	public void setCpAds(String cpAds) {
		this.cpAds = cpAds;
	}

	public String getCpAdsDetail() {
		return cpAdsDetail;
	}

	public void setCpAdsDetail(String cpAdsDetail) {
		this.cpAdsDetail = cpAdsDetail;
	}

	public String getCpEmail() {
		return cpEmail;
	}

	public void setCpEmail(String cpEmail) {
		this.cpEmail = cpEmail;
	}

	@Override
	public String toString() {
		return "CompanyDTO [bsrNum=" + bsrNum + ", cpNm=" + cpNm + ", rpNm=" + rpNm + ", cpTel=" + cpTel + ", bsType="
				+ bsType + ", bsItem=" + bsItem + ", cpAdsNum=" + cpAdsNum + ", cpAds=" + cpAds + ", cpAdsDetail="
				+ cpAdsDetail + ", cpEmail=" + cpEmail + "]";
	}

}
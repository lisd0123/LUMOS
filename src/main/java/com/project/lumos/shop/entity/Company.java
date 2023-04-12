package com.project.lumos.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_COMPANY")
public class Company {

	@Id
	@Column(name = "BSR_NUM")
	private int bsrNum;
	
	@Column(name = "CP_NM")
	private String cpNm;
	
	@Column(name = "RP_NM")
	private String rpNm;
	
	@Column(name = "CP_TEL")
	private String cpTel;
	
	@Column(name = "BS_TYPE")
	private String bsType;
	
	@Column(name = "BS_ITEM")
	private String bsItem;
	
	@Column(name = "CP_ADS_NUM")
	private String cpAdsNum;

	@Column(name = "CP_ADS")
	private String cpAds;
	
	@Column(name = "CP_ADS_DETAIL")
	private String cpAdsDetail;
	
	@Column(name = "CP_EMAIL")
	private String cpEmail;

	public Company() {
	}

	public Company(int bsrNum, String cpNm, String rpNm, String cpTel, String bsType, String bsItem, String cpAdsNum,
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
		return "Company [bsrNum=" + bsrNum + ", cpNm=" + cpNm + ", rpNm=" + rpNm + ", cpTel=" + cpTel + ", bsType="
				+ bsType + ", bsItem=" + bsItem + ", cpAdsNum=" + cpAdsNum + ", cpAds=" + cpAds + ", cpAdsDetail="
				+ cpAdsDetail + ", cpEmail=" + cpEmail + "]";
	}

}
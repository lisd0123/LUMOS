package com.project.lumos.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_SHOP")
public class Shop {

	@Id
	@Column(name = "SHOP_NM")
	private String shopNm;
	
	@Column(name = "SHOP_WEB_ADS")
	private String shopWebAds;
	
	@Column(name = "SHOP_EMAIL")
	private String shopEmail;
	
	@Column(name = "SHOP_DESC")
	private String shopDesc;
	
	@Column(name = "OM_ST")
	private String omSt;
	
	@Column(name = "OM_NUM")
	private String omNum;
	
	@Column(name = "CS_TEL")
	private String csTel;
	
	@Column(name = "CS_EMAIL")
	private String csEmail;
	
	@Column(name = "CS_HOUR")
	private String csHour;
	
	@Column(name = "PIC_NM")
	private String picNm;
	
	@Column(name = "PIC_TEL")
	private String picTel;
	
	@Column(name = "PIC_EMAIL")
	private String picEmail;

	public Shop() {
	}

	public Shop(String shopNm, String shopWebAds, String shopEmail, String shopDesc, String omSt, String omNum,
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
		return "Shop [shopNm=" + shopNm + ", shopWebAds=" + shopWebAds + ", shopEmail=" + shopEmail + ", shopDesc="
				+ shopDesc + ", omSt=" + omSt + ", omNum=" + omNum + ", csTel=" + csTel + ", csEmail=" + csEmail
				+ ", csHour=" + csHour + ", picNm=" + picNm + ", picTel=" + picTel + ", picEmail=" + picEmail + "]";
	}

}
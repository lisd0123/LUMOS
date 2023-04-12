package com.project.lumos.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_IMAGE")
public class ImageAndProduct {
	
	@Id
	@Column(name = "IMG_NUM")
	private int imgNum;
	
	@Column(name = "PD_IMG_PATH")
	private String pdImgPath;
	
	@Column(name = "PD_CODE")
	private int pdCode;
	
	@Column(name = "MAIN_IMG")
	private String mainImg;
	
	@ManyToOne
	@JoinColumn(name = "PD_CODE", insertable=false, updatable = false)
	private Product product;

	public ImageAndProduct() {
		super();
	}

	public ImageAndProduct(int imgNum, String pdImgPath, int pdCode, String mainImg, Product product) {
		super();
		this.imgNum = imgNum;
		this.pdImgPath = pdImgPath;
		this.pdCode = pdCode;
		this.mainImg = mainImg;
		this.product = product;
	}

	public int getImgNum() {
		return imgNum;
	}

	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}

	public String getPdImgPath() {
		return pdImgPath;
	}

	public void setPdImgPath(String pdImgPath) {
		this.pdImgPath = pdImgPath;
	}

	public int getPdCode() {
		return pdCode;
	}

	public void setPdCode(int pdCode) {
		this.pdCode = pdCode;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ImageAndProduct [imgNum=" + imgNum + ", pdImgPath=" + pdImgPath + ", pdCode=" + pdCode + ", mainImg="
				+ mainImg + ", product=" + product + "]";
	}
	
	
	
}

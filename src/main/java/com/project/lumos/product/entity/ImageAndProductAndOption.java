package com.project.lumos.product.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_IMAGE")
public class ImageAndProductAndOption {

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
	
	@ManyToOne
	@JoinColumn(name = "PD_CODE", insertable=false, updatable = false)
	private Option option;

	public ImageAndProductAndOption() {
	}

	public ImageAndProductAndOption(int imgNum, String pdImgPath, int pdCode, String mainImg, Product product,
			Option option) {
		this.imgNum = imgNum;
		this.pdImgPath = pdImgPath;
		this.pdCode = pdCode;
		this.mainImg = mainImg;
		this.product = product;
		this.option = option;
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

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	@Override
	public String toString() {
		return "ImageAndProductAndOption [imgNum=" + imgNum + ", pdImgPath=" + pdImgPath + ", pdCode=" + pdCode
				+ ", mainImg=" + mainImg + ", product=" + product + ", option=" + option + "]";
	}

	
	
}

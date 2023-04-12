package com.project.lumos.product.dto;

import com.project.lumos.product.entity.Product;

public class ImageAndProductDTO {

	private int imgNum;
	private String pdImgPath;
	private int pdCode;
	private String mainImg;
	private Product product;
	public ImageAndProductDTO() {
		super();
	}
	public ImageAndProductDTO(int imgNum, String pdImgPath, int pdCode, String mainImg, Product product) {
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
		return "ImageAndProductDTO [imgNum=" + imgNum + ", pdImgPath=" + pdImgPath + ", pdCode=" + pdCode + ", mainImg="
				+ mainImg + ", product=" + product + "]";
	}
	
	
}

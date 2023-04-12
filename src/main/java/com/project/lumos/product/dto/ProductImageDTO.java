package com.project.lumos.product.dto;

public class ProductImageDTO {

	private String pdImgPath;
	private int pdCode;
	private int imgNum;
	private String mainImg;
	public ProductImageDTO() {
	}
	public ProductImageDTO(String pdImgPath, int pdCode, int imgNum, String mainImg) {
		this.pdImgPath = pdImgPath;
		this.pdCode = pdCode;
		this.imgNum = imgNum;
		this.mainImg = mainImg;
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
	public int getImgNum() {
		return imgNum;
	}
	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}
	public String getMainImg() {
		return mainImg;
	}
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	@Override
	public String toString() {
		return "ProductImageDTO [pdImgPath=" + pdImgPath + ", pdCode=" + pdCode + ", imgNum=" + imgNum + ", mainImg="
				+ mainImg + "]";
	}
	
	
}

package com.project.lumos.product.dto;

import java.util.List;

import com.project.lumos.product.entity.Option;
import com.project.lumos.product.entity.Product;

public class ImageAndProductAndOptionDTO {

	private int imgNum;
	private String pdImgPath;
	private int pdCode;
	private String mainImg;
	private Product product;
	private Option option;
	public ImageAndProductAndOptionDTO() {
	}
	public ImageAndProductAndOptionDTO(int imgNum, String pdImgPath, int pdCode, String mainImg, Product product,
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
		return "ImageAndProductAndOptionDTO [imgNum=" + imgNum + ", pdImgPath=" + pdImgPath + ", pdCode=" + pdCode
				+ ", mainImg=" + mainImg + ", product=" + product + ", option=" + option + "]";
	}
	
}

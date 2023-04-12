package com.project.lumos.product.dto;

import java.util.List;

import com.project.lumos.product.entity.Option;
import com.project.lumos.product.entity.ProductImage;

public class ProductAndImageDTO {
	
	private int pdCode;
	private String pdName;
	private int pdPrice;
	private String pdDesc;
	private String catMain;
	private String catSub;
	private List<ProductImage> productImage;
	private List<Option> productOption;
	public ProductAndImageDTO() {
		super();
	}
	public ProductAndImageDTO(int pdCode, String pdName, int pdPrice, String pdDesc, String catMain, String catSub,
			List<ProductImage> productImage, List<Option> productOption) {
		super();
		this.pdCode = pdCode;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.pdDesc = pdDesc;
		this.catMain = catMain;
		this.catSub = catSub;
		this.productImage = productImage;
		this.productOption = productOption;
	}
	public int getPdCode() {
		return pdCode;
	}
	public void setPdCode(int pdCode) {
		this.pdCode = pdCode;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public int getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}
	public String getPdDesc() {
		return pdDesc;
	}
	public void setPdDesc(String pdDesc) {
		this.pdDesc = pdDesc;
	}
	public String getCatMain() {
		return catMain;
	}
	public void setCatMain(String catMain) {
		this.catMain = catMain;
	}
	public String getCatSub() {
		return catSub;
	}
	public void setCatSub(String catSub) {
		this.catSub = catSub;
	}
	public List<ProductImage> getProductImage() {
		return productImage;
	}
	public void setProductImage(List<ProductImage> productImage) {
		this.productImage = productImage;
	}
	public List<Option> getProductOption() {
		return productOption;
	}
	public void setProductOption(List<Option> productOption) {
		this.productOption = productOption;
	}
	@Override
	public String toString() {
		return "ProductAndImageDTO [pdCode=" + pdCode + ", pdName=" + pdName + ", pdPrice=" + pdPrice + ", pdDesc="
				+ pdDesc + ", catMain=" + catMain + ", catSub=" + catSub + ", productImage=" + productImage
				+ ", productOption=" + productOption + "]";
	}
	
	
}

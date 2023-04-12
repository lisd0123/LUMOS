package com.project.lumos.product.dto;

import java.util.List;

public class ProductAndImageAndOptionDTO {
	
	private int pdCode;
	private String pdName;
	private int pdPrice;
	private String pdDesc;
	private String catMain;
	private String catSub;
	private List<ProductImageDTO> productImageList;
	private List<OptionDTO> optionList;
	
	public ProductAndImageAndOptionDTO() {
	}

	public ProductAndImageAndOptionDTO(int pdCode, String pdName, int pdPrice, String pdDesc, String catMain, String catSub,
			List<ProductImageDTO> productImageList, List<OptionDTO> optionList) {
		this.pdCode = pdCode;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.pdDesc = pdDesc;
		this.catMain = catMain;
		this.catSub = catSub;
		this.productImageList = productImageList;
		this.optionList = optionList;
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

	public List<ProductImageDTO> getProductImageList() {
		return productImageList;
	}

	public void setProductImageList(List<ProductImageDTO> productImageList) {
		this.productImageList = productImageList;
	}

	public List<OptionDTO> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<OptionDTO> optionList) {
		this.optionList = optionList;
	}

	@Override
	public String toString() {
		return "ProductAndImageDTO [pdCode=" + pdCode + ", pdName=" + pdName + ", pdPrice=" + pdPrice + ", pdDesc="
				+ pdDesc + ", catMain=" + catMain + ", catSub=" + catSub + ", productImageList=" + productImageList
				+ ", optionList=" + optionList + "]";
	}
	
}
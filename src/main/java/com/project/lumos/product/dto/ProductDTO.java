package com.project.lumos.product.dto;

public class ProductDTO {
	
	private int pdCode;
	private String pdName;
	private int pdPrice;
	private String pdDesc;
	private String catMain;
	private String catSub;
	public ProductDTO() {
	}
	public ProductDTO(int pdCode, String pdName, int pdPrice, String pdDesc, String catMain, String catSub) {
		this.pdCode = pdCode;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.pdDesc = pdDesc;
		this.catMain = catMain;
		this.catSub = catSub;
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
	@Override
	public String toString() {
		return "ProductDTO [pdCode=" + pdCode + ", pdName=" + pdName + ", pdPrice=" + pdPrice + ", pdDesc=" + pdDesc
				+ ", catMain=" + catMain + ", catSub=" + catSub + "]";
	}
	
	
}

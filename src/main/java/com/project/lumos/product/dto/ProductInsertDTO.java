package com.project.lumos.product.dto;

public class ProductInsertDTO {

	private int pdCode;
	private String pdName;
	private int pdPrice;
	private String pdDesc;
	private String catMain;
	private String catSub;
	private String PdImgPath;
	private String mainImg;
	private String optionNm;
	private int optionStock;
	public ProductInsertDTO() {
	}
	public ProductInsertDTO(int pdCode, String pdName, int pdPrice, String pdDesc, String catMain, String catSub,
			String pdImgPath, String mainImg, String optionNm, int optionStock) {
		this.pdCode = pdCode;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.pdDesc = pdDesc;
		this.catMain = catMain;
		this.catSub = catSub;
		PdImgPath = pdImgPath;
		this.mainImg = mainImg;
		this.optionNm = optionNm;
		this.optionStock = optionStock;
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
	public String getPdImgPath() {
		return PdImgPath;
	}
	public void setPdImgPath(String pdImgPath) {
		PdImgPath = pdImgPath;
	}
	public String getMainImg() {
		return mainImg;
	}
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	public String getOptionNm() {
		return optionNm;
	}
	public void setOptionNm(String optionNm) {
		this.optionNm = optionNm;
	}
	public int getOptionStock() {
		return optionStock;
	}
	public void setOptionStock(int optionStock) {
		this.optionStock = optionStock;
	}
	@Override
	public String toString() {
		return "ProductInsertDTO [pdCode=" + pdCode + ", pdName=" + pdName + ", pdPrice=" + pdPrice + ", pdDesc="
				+ pdDesc + ", catMain=" + catMain + ", catSub=" + catSub + ", PdImgPath=" + PdImgPath + ", mainImg="
				+ mainImg + ", optionNm=" + optionNm + ", optionStock=" + optionStock + "]";
	}
	
	
}

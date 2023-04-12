package com.project.lumos.product.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_PRODUCT")
public class ProductAndImageAndOption {

	@Id
	@Column(name = "PD_CODE")
	private int pdCode;
	
	@Column(name = "PD_NAME")
	private String pdName;
	
	@Column(name = "PD_PRICE")
	private int pdPrice;
	
	@Column(name = "PD_DESC")
	private String pdDesc;
	
	@Column(name = "CAT_MAIN")
	private String catMain;
	
	@Column(name = "CAT_SUB")
	private String catSub;
	
	@OneToMany
	@JoinColumn(name = "PD_CODE", insertable=false, updatable = false)
	private List<ProductImage> productImageList;
	
	@OneToMany
	@JoinColumn(name = "PD_CODE", insertable=false, updatable = false)
	private List<Option> optionList;

	public ProductAndImageAndOption() {
	}

	public ProductAndImageAndOption(int pdCode, String pdName, int pdPrice, String pdDesc, String catMain, String catSub,
			List<ProductImage> productImageList, List<Option> optionList) {
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

	public List<ProductImage> getProductImageList() {
		return productImageList;
	}

	public void setProductImageList(List<ProductImage> productImageList) {
		this.productImageList = productImageList;
	}

	public List<Option> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}

	@Override
	public String toString() {
		return "ProductAndImage [pdCode=" + pdCode + ", pdName=" + pdName + ", pdPrice=" + pdPrice + ", pdDesc="
				+ pdDesc + ", catMain=" + catMain + ", catSub=" + catSub + ", productImageList=" + productImageList
				+ ", optionList=" + optionList + "]";
	}

}
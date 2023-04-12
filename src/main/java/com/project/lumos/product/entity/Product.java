package com.project.lumos.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TBL_PRODUCT")
@SequenceGenerator(
	name = "PRODUCT_SEQ_GENERATOR",
	sequenceName = "SEQ_PD_CODE",
	initialValue = 118, allocationSize = 1
)
public class Product {
	
	@Id
	@Column(name = "PD_CODE")
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "PRODUCT_SEQ_GENERATOR"
	)
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

	public Product() {
	}

	public Product(int pdCode, String pdName, int pdPrice, String pdDesc, String catMain, String catSub) {
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
		return "Product [pdCode=" + pdCode + ", pdName=" + pdName + ", pdPrice=" + pdPrice + ", pdDesc=" + pdDesc
				+ ", catMain=" + catMain + ", catSub=" + catSub + "]";
	}
	
	
}

package com.project.lumos.product.dto;

public class OptionDTO {
	private int opCode;
	private String optionNm;
	private int optionStock;
	private int pdCode;
	public OptionDTO() {
	}
	public OptionDTO(int opCode, String optionNm, int optionStock, int pdCode) {
		this.opCode = opCode;
		this.optionNm = optionNm;
		this.optionStock = optionStock;
		this.pdCode = pdCode;
	}
	public int getOpCode() {
		return opCode;
	}
	public void setOpCode(int opCode) {
		this.opCode = opCode;
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
	public int getPdCode() {
		return pdCode;
	}
	public void setPdCode(int pdCode) {
		this.pdCode = pdCode;
	}
	@Override
	public String toString() {
		return "OptionDTO [opCode=" + opCode + ", optionNm=" + optionNm + ", optionStock=" + optionStock + ", pdCode="
				+ pdCode + "]";
	}
	
	
}

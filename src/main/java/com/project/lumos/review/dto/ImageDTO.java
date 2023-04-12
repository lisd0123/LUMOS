package com.project.lumos.review.dto;

public class ImageDTO {

	private int imageCode;
	private String originNm;
	private String newNm;
	private int reviewCode;
	
	public ImageDTO() {
		super();
	}

	public ImageDTO(int imageCode, String originNm, String newNm, int reviewCode) {
		super();
		this.imageCode = imageCode;
		this.originNm = originNm;
		this.newNm = newNm;
		this.reviewCode = reviewCode;
	}

	public int getImageCode() {
		return imageCode;
	}

	public void setImageCode(int imageCode) {
		this.imageCode = imageCode;
	}

	public String getOriginNm() {
		return originNm;
	}

	public void setOriginNm(String originNm) {
		this.originNm = originNm;
	}

	public String getNewNm() {
		return newNm;
	}

	public void setNewNm(String newNm) {
		this.newNm = newNm;
	}

	public int getReviewCode() {
		return reviewCode;
	}

	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}

	@Override
	public String toString() {
		return "ReviewImageDTO [imageCode=" + imageCode + ", originNm=" + originNm + ", newNm=" + newNm
				+ ", reviewCode=" + reviewCode + "]";
	}
	
	
}

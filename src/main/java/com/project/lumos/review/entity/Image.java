package com.project.lumos.review.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "REVIEW_IMAGE")
@SequenceGenerator(
		name = "IMAGE_SEQ_GENERATOR",
		sequenceName = "SEQ_IMAGE_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Image {

	@Id
	@Column(name = "IMAGE_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "IMAGE_SEQ_GENERATOR")
	private int imageCode;
	
	@Column(name = "ORIGIN_NM")
	private String originNm;
	
	@Column(name = "NEW_NM")
	private String newNm;
	
	@Column(name = "REVIEW_CODE")
	private int reviewCode;

	public Image() {
		super();
	}

	public Image(int imageCode, String originNm, String newNm, int reviewCode) {
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
		return "Image [imageCode=" + imageCode + ", originNm=" + originNm + ", newNm=" + newNm + ", reviewCode="
				+ reviewCode + "]";
	}
	
	
}

package com.project.lumos.review.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REVIEW_IMAGE")
public class ReviewAndImage {

	@Id
	@Column(name = "IMAGE_CODE")
	private int imageCode;
	
	@Column(name = "ORIGIN_NM")
	private String originNm;
	
	@Column(name = "NEW_NM")
	private String newNm;
	
	@ManyToOne
	@JoinColumn(name = "REVIEW_CODE")
	private Review review;

	public ReviewAndImage() {
		super();
	}

	public ReviewAndImage(int imageCode, String originNm, String newNm, Review review) {
		super();
		this.imageCode = imageCode;
		this.originNm = originNm;
		this.newNm = newNm;
		this.review = review;
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

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "ReviewAndImage [imageCode=" + imageCode + ", originNm=" + originNm + ", newNm=" + newNm + ", review="
				+ review + "]";
	}
	
	
}

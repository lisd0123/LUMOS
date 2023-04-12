package com.project.lumos.review.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_REVIEW")
@SequenceGenerator(
		name = "REVIEW_SEQ_GENERATOR",
		sequenceName = "SEQ_REVIEW_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Review {

	@Id
	@Column(name = "REVIEW_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "REVIEW_SEQ_GENERATOR"
	)
	private int reviewCode;
	
	@Column(name = "PD_CODE")
	private int pdCode;
	
	@Column(name = "PD_GRADE")
	private int pdGrade;
	
	@Column(name = "REVIEW_CONTENT")
	private String reviewContent;
	
	@Column(name = "UPLOAD_DATE")
	private String uploadDate;
	
	@Column(name = "REVIEW_COMMENT")
	private String reviewComment;
	
	@Column(name = "REVIEW_TITLE")
	private String reviewTitle;
	
	@Column(name = "MEMBER_CODE")
	private int memberCode;

	public Review() {
		super();
	}

	public Review(int reviewCode, int pdCode, int pdGrade, String reviewContent, String uploadDate, String reviewComment,
			String reviewTitle, int memberCode) {
		super();
		this.reviewCode = reviewCode;
		this.pdCode = pdCode;
		this.pdGrade = pdGrade;
		this.reviewContent = reviewContent;
		this.uploadDate = uploadDate;
		this.reviewComment = reviewComment;
		this.reviewTitle = reviewTitle;
		this.memberCode = memberCode;
	}

	public int getReviewCode() {
		return reviewCode;
	}

	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}

	public int getPdCode() {
		return pdCode;
	}

	public void setPdCode(int pdCode) {
		this.pdCode = pdCode;
	}

	public int getPdGrade() {
		return pdGrade;
	}

	public void setPdGrade(int pdGrade) {
		this.pdGrade = pdGrade;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	@Override
	public String toString() {
		return "Review [reviewCode=" + reviewCode + ", pdCode=" + pdCode + ", pdGrade=" + pdGrade + ", reviewContent="
				+ reviewContent + ", uploadDate=" + uploadDate + ", reviewComment=" + reviewComment + ", reviewTitle="
				+ reviewTitle + ", memberCode=" + memberCode + "]";
	}
	
	
}

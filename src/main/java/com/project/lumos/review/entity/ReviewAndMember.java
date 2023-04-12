package com.project.lumos.review.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.lumos.member.entity.Member;

@Entity
@Table(name = "TBL_REVIEW")
public class ReviewAndMember {

	@Id
	@Column(name = "REVIEW_CODE")
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
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member member;

	public ReviewAndMember() {
		super();
	}

	public ReviewAndMember(int reviewCode, int pdCode, int pdGrade, String reviewContent, String uploadDate,
			String reviewComment, String reviewTitle, Member member) {
		super();
		this.reviewCode = reviewCode;
		this.pdCode = pdCode;
		this.pdGrade = pdGrade;
		this.reviewContent = reviewContent;
		this.uploadDate = uploadDate;
		this.reviewComment = reviewComment;
		this.reviewTitle = reviewTitle;
		this.member = member;
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "ReviewAndMember [reviewCode=" + reviewCode + ", pdCode=" + pdCode + ", pdGrade=" + pdGrade
				+ ", reviewContent=" + reviewContent + ", uploadDate=" + uploadDate + ", reviewComment=" + reviewComment
				+ ", reviewTitle=" + reviewTitle + ", member=" + member + "]";
	}
	
	
}

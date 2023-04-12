package com.project.lumos.review.dto;

public class ReviewDTO {
	private int reviewCode;
	private int pdCode;
	private int pdGrade;
	private String reviewContent;
	private String uploadDate;
	private String reviewComment;
	private String reviewTitle;
	private int memberCode;
	
	public ReviewDTO() {
		super();
	}

	public ReviewDTO(int reviewCode, int pdCode, int pdGrade, String reviewContent, String uploadDate,
			String reviewComment, String reviewTitle, int memberCode) {
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
		return "ReviewDTO [reviewCode=" + reviewCode + ", pdCode=" + pdCode + ", pdGrade=" + pdGrade
				+ ", reviewContent=" + reviewContent + ", uploadDate=" + uploadDate + ", reviewComment=" + reviewComment
				+ ", reviewTitle=" + reviewTitle + ", memberCode=" + memberCode + "]";
	}
	
}

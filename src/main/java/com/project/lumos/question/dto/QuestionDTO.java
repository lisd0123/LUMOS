package com.project.lumos.question.dto;

import com.project.lumos.member.dto.MemberDTO;

public class QuestionDTO {
	private int questionCode;
	private String questionTitle;
	private String questionCategory;
	private String questionContent;
	private String answerContent;
	private String questionStatus;
	private String questionCreateDate;
	private int memberCode;
	
	public QuestionDTO(int questionCode, String questionTitle, String questionCategory, String questionContent,
			String answerContent, String questionStatus, String questionCreateDate, int memberCode) {
		super();
		this.questionCode = questionCode;
		this.questionTitle = questionTitle;
		this.questionCategory = questionCategory;
		this.questionContent = questionContent;
		this.answerContent = answerContent;
		this.questionStatus = questionStatus;
		this.questionCreateDate = questionCreateDate;
		this.memberCode = memberCode;
	}
	
	public QuestionDTO() {
		super();
	}

	public int getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(int questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(String questionStatus) {
		this.questionStatus = questionStatus;
	}

	public String getQuestionCreateDate() {
		return questionCreateDate;
	}

	public void setQuestionCreateDate(String questionCreateDate) {
		this.questionCreateDate = questionCreateDate;
	}

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	@Override
	public String toString() {
		return "QuestionDTO [questionCode=" + questionCode + ", questionTitle=" + questionTitle + ", questionCategory="
				+ questionCategory + ", questionContent=" + questionContent + ", answerContent=" + answerContent
				+ ", questionStatus=" + questionStatus + ", questionCreateDate=" + questionCreateDate + ", memberCode="
				+ memberCode + "]";
	}
	
	 
	
	
}

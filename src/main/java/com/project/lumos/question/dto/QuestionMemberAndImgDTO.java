package com.project.lumos.question.dto;

import com.project.lumos.member.entity.Member;
import com.project.lumos.question.entity.QuestionImg;

public class QuestionMemberAndImgDTO {
	private int questionCode;
	private String questionTitle;
	private String questionCategory;
	private String questionContent;
	private String answerContent;
	private String questionStatus;
	private int memberCode;
	private String questionCreateDate;
	private Member member;
	private QuestionImg questionImg;
	
	public QuestionMemberAndImgDTO(int questionCode, String questionTitle, String questionCategory,
			String questionContent, String answerContent, String questionStatus, String questionCreateDate,
			int memberCode, Member member, QuestionImg questionImg) {
		super();
		this.questionCode = questionCode;
		this.questionTitle = questionTitle;
		this.questionCategory = questionCategory;
		this.questionContent = questionContent;
		this.answerContent = answerContent;
		this.questionStatus = questionStatus;
		this.questionCreateDate = questionCreateDate;
		this.memberCode = memberCode;
		this.member = member;
		this.questionImg = questionImg;
	}
	public QuestionMemberAndImgDTO() {
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
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public QuestionImg getQuestionImg() {
		return questionImg;
	}
	public void setQuestionImg(QuestionImg questionImg) {
		this.questionImg = questionImg;
	}
	@Override
	public String toString() {
		return "QuestionMemberAndImgDTO [questionCode=" + questionCode + ", questionTitle=" + questionTitle
				+ ", questionCategory=" + questionCategory + ", questionContent=" + questionContent + ", answerContent="
				+ answerContent + ", questionStatus=" + questionStatus + ", questionCreateDate=" + questionCreateDate
				+ ", memberCode=" + memberCode + ", member=" + member + ", questionImg=" + questionImg + "]";
	}
	
	
	
}

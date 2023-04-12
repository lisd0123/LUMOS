package com.project.lumos.question.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTION")
@SequenceGenerator(
	name = "QUESTION_SEQ_GENERATOR",
	sequenceName = "SEQ_QUESTION_CODE",
	initialValue = 1, allocationSize = 1
)
public class QuestionAndImage {
	@Id
	@Column(name = "QUESTION_CODE")
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "QUESTION_SEQ_GENERATOR"
	)
	private int questionCode;
	
	@Column(name = "QUESTION_TITLE")
	private String questionTitle;
	
	@Column(name = "QUESTION_CATEGORY")
	private String questionCategory;
	
	@Column(name = "QUESTION_CONTENT")
	private String questionContent;
	
	@Column(name = "ANSWER_CONTENT")
	private String answerContent;
	
	@Column(name = "QUESTION_STATUS")
	private String questionStatus;
	
	@Column(name = "MEMBER_CODE")
	private int memberCode;
	
	@Column(name = "QUESTION_CREATE_DATE")
	private String questionCreateDate;
	
	@OneToOne
	@JoinColumn(name = "QUESTION_CODE", referencedColumnName = "QUESTION_CODE", insertable = false, updatable = false)
	private QuestionImg questionImg;

	public QuestionAndImage(int questionCode, String questionTitle, String questionCategory, String questionContent,
			String answerContent, String questionStatus, int memberCode, String questionCreateDate,
			QuestionImg questionImg) {
		super();
		this.questionCode = questionCode;
		this.questionTitle = questionTitle;
		this.questionCategory = questionCategory;
		this.questionContent = questionContent;
		this.answerContent = answerContent;
		this.questionStatus = questionStatus;
		this.memberCode = memberCode;
		this.questionCreateDate = questionCreateDate;
		this.questionImg = questionImg;
	}

	public QuestionAndImage() {
		super();
	}

	@Override
	public String toString() {
		return "QuestionAndImage [questionCode=" + questionCode + ", questionTitle=" + questionTitle
				+ ", questionCategory=" + questionCategory + ", questionContent=" + questionContent + ", answerContent="
				+ answerContent + ", questionStatus=" + questionStatus + ", memberCode=" + memberCode
				+ ", questionCreateDate=" + questionCreateDate + ", questionImg=" + questionImg + "]";
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

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public String getQuestionCreateDate() {
		return questionCreateDate;
	}

	public void setQuestionCreateDate(String questionCreateDate) {
		this.questionCreateDate = questionCreateDate;
	}

	public QuestionImg getQuestionImg() {
		return questionImg;
	}

	public void setQuestionImg(QuestionImg questionImg) {
		this.questionImg = questionImg;
	}
	
	
}

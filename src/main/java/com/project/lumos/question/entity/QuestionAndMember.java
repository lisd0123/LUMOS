package com.project.lumos.question.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.project.lumos.member.entity.Member;

@Entity
@Table(name = "QUESTION")
@SequenceGenerator(
	name = "QUESTION_SEQ_GENERATOR",
	sequenceName = "SEQ_QUESTION_CODE",
	initialValue = 1, allocationSize = 1
)
public class QuestionAndMember {
	
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
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member member;
	
	@Column(name = "QUESTION_CREATE_DATE")
	private String questionCreateDate;

	public QuestionAndMember(int questionCode, String questionTitle, String questionCategory, String questionContent,
			String answerContent, String questionStatus, Member member, String questionCreateDate) {
		super();
		this.questionCode = questionCode;
		this.questionTitle = questionTitle;
		this.questionCategory = questionCategory;
		this.questionContent = questionContent;
		this.answerContent = answerContent;
		this.questionStatus = questionStatus;
		this.member = member;
		this.questionCreateDate = questionCreateDate;
	}

	public QuestionAndMember() {
		super();
	}

	@Override
	public String toString() {
		return "QuestionAndMember [questionCode=" + questionCode + ", questionTitle=" + questionTitle
				+ ", questionCategory=" + questionCategory + ", questionContent=" + questionContent + ", answerContent="
				+ answerContent + ", questionStatus=" + questionStatus + ", member=" + member + ", questionCreateDate="
				+ questionCreateDate + "]";
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getQuestionCreateDate() {
		return questionCreateDate;
	}

	public void setQuestionCreateDate(String questionCreateDate) {
		this.questionCreateDate = questionCreateDate;
	}

	
}

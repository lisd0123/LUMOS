package com.project.lumos.question.dto;

import com.project.lumos.question.entity.Question;

public class QuestionImgDTO {
	private int questionImgCode;
	private String originalName;
	private String newName;
	private int questionCode;
	
	public QuestionImgDTO(int questionImgCode, String originalName, String newName, int questionCode) {
		super();
		this.questionImgCode = questionImgCode;
		this.originalName = originalName;
		this.newName = newName;
		this.questionCode = questionCode;
	}
	
	public QuestionImgDTO() {
		super();
	}
	
	public int getQuestionImgCode() {
		return questionImgCode;
	}
	public void setQuestionImgCode(int questionImgCode) {
		this.questionImgCode = questionImgCode;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public int getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(int questionCode) {
		this.questionCode = questionCode;
	}
	@Override
	public String toString() {
		return "QuestionImgDTO [questionImgCode=" + questionImgCode + ", originalName=" + originalName + ", newName="
				+ newName + ", questionCode=" + questionCode + "]";
	}
	
}

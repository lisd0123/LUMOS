package com.project.lumos.member.entity;

import java.io.Serializable;

public class MemberRolePk implements Serializable {

	private int memberNo;
	private int authorityCode;
	
	public MemberRolePk() {
	}
	public MemberRolePk(int memberNo, int authorityCode) {
		this.memberNo = memberNo;
		this.authorityCode = authorityCode;
	}
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getAuthorityCode() {
		return authorityCode;
	}
	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}
	
	@Override
	public String toString() {
		return "MemberRolePk [memberNo=" + memberNo + ", authorityCode=" + authorityCode + "]";
	}
}
	


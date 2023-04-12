package com.project.lumos.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_MEMBER_ROLE")
@IdClass(MemberRolePk.class)
public class MemberRole {

	@Id
	@Column(name = "MEMBER_CODE")
	private int memberNo;
	
	@Id
	@Column(name = "AUTHORITY_CODE")
	private int authorityCode;
	
	@ManyToOne
	@JoinColumn(name = "AUTHORITY_CODE", insertable = false, updatable = false)
	private Authority authority;

	public MemberRole() {
	}

	//두개만 초기화하는 생성자 생성
	public MemberRole(int memberNo, int authorityCode) {
		super();
		this.memberNo = memberNo;
		this.authorityCode = authorityCode;
	}


	public MemberRole(int memberNo, int authorityCode, Authority authority) {
		this.memberNo = memberNo;
		this.authorityCode = authorityCode;
		this.authority = authority;
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

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "MemberRole [memberNo=" + memberNo + ", authorityCode=" + authorityCode + ", authority=" + authority
				+ "]";
	}
}

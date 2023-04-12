package com.project.lumos.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_AUTHORITY")
public class Authority {
	
	@Id
	@Column(name = "AUTHORITY_CODE")
	private int authorityCode;
	
	@Column(name = "AUTHORITY_NAME")
	private String authorityName;
	
	@Column(name = "AUTHORITY_DESC")
	private String authorityDesc;

	public Authority() {
	}

	public Authority(int authorityCode, String authorityName, String authorityDesc) {
		this.authorityCode = authorityCode;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
	}

	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	@Override
	public String toString() {
		return "Authority [authorityCode=" + authorityCode + ", authorityName=" + authorityName + ", authorityDesc="
				+ authorityDesc + "]";
	}
}


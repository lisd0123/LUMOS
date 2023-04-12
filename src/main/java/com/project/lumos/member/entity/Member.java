package com.project.lumos.member.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_MEMBER")
@SequenceGenerator(
	name = "MEMBER_SEQ_GENERATOR",
	sequenceName = "SEQ_MEMBER_CODE",
	initialValue = 1, allocationSize = 1
)
public class Member {
	@Id
	@Column(name = "MEMBER_CODE")
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "MEMBER_SEQ_GENERATOR"
	)
	private int memberCode;
	
	@Column(name = "MEMBER_ID")
	private String memberId;
	
	@Column(name = "MEMBER_PASSWORD")
	private String memberPassword;
	
	@Column(name = "MEMBER_NAME")
	private String memberName;
	
	@Column(name = "MEMBER_BIRTH")
	private String memberBirth;
	
	@Column(name = "MEMBER_GEN")
	private String memberGen;
	
	@Column(name = "MEMBER_PHONE")
	private String memberPhone;
	
	@Column(name = "MEMBER_EMAIL")
	private String memberEmail;
	
	@Column(name = "MEMBER_ADS_NUM")
	private String memberAdsNum;
	
	@Column(name = "MEMBER_ADS")
	private String memberAds;
	
	@Column(name = "MEMBER_ADS_DETAIL")
	private String memberAdsDetail;
	
	@OneToMany
	@JoinColumn(name = "MEMBER_CODE")
	private List<MemberRole> memberRole;

	public Member() {
		super();
	}

	public Member(int memberCode, String memberId, String memberPassword, String memberName, String memberBirth,
			String memberGen, String memberPhone, String memberEmail, String memberAdsNum, String memberAds,
			String memberAdsDetail, List<MemberRole> memberRole) {
		super();
		this.memberCode = memberCode;
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberBirth = memberBirth;
		this.memberGen = memberGen;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAdsNum = memberAdsNum;
		this.memberAds = memberAds;
		this.memberAdsDetail = memberAdsDetail;
		this.memberRole = memberRole;
	}

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberGen() {
		return memberGen;
	}

	public void setMemberGen(String memberGen) {
		this.memberGen = memberGen;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberAdsNum() {
		return memberAdsNum;
	}

	public void setMemberAdsNum(String memberAdsNum) {
		this.memberAdsNum = memberAdsNum;
	}

	public String getMemberAds() {
		return memberAds;
	}

	public void setMemberAds(String memberAds) {
		this.memberAds = memberAds;
	}

	public String getMemberAdsDetail() {
		return memberAdsDetail;
	}

	public void setMemberAdsDetail(String memberAdsDetail) {
		this.memberAdsDetail = memberAdsDetail;
	}

	public List<MemberRole> getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(List<MemberRole> memberRole) {
		this.memberRole = memberRole;
	}

	@Override
	public String toString() {
		return "Member [memberCode=" + memberCode + ", memberId=" + memberId + ", memberPassword=" + memberPassword
				+ ", memberName=" + memberName + ", memberBirth=" + memberBirth + ", memberGen=" + memberGen
				+ ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail + ", memberAdsNum=" + memberAdsNum
				+ ", memberAds=" + memberAds + ", memberAdsDetail=" + memberAdsDetail + ", memberRole=" + memberRole
				+ "]";
	}
	
	
	
}

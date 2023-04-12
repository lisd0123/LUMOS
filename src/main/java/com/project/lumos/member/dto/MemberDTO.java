package com.project.lumos.member.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberDTO implements UserDetails{
	private int memberCode;
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberBirth;
	private String memberGen;
	private String memberPhone;
	private String memberEmail;
	private String memberAdsNum;
	private String memberAds;
	private String memberAdsDetail;
	private List<MemberRoleDTO> memberRole;
	
	public MemberDTO() {
		super();
	}

	public MemberDTO(int memberCode, String memberId, String memberPassword, String memberName, String memberBirth,
			String memberGen, String memberPhone, String memberEmail, String memberAdsNum, String memberAds,
			String memberAdsDetail, List<MemberRoleDTO> memberRole) {
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

	public List<MemberRoleDTO> getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(List<MemberRoleDTO> memberRole) {
		this.memberRole = memberRole;
	}

	@Override
	public String toString() {
		return "MemberDTO [memberCode=" + memberCode + ", memberId=" + memberId + ", memberPassword=" + memberPassword
				+ ", memberName=" + memberName + ", memberBirth=" + memberBirth + ", memberGen=" + memberGen
				+ ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail + ", memberAdsNum=" + memberAdsNum
				+ ", memberAds=" + memberAds + ", memberAdsDetail=" + memberAdsDetail + ", memberRole=" + memberRole
				+ "]";
	}
	
	/* MemberDTO는 UserDetails로써 속성을 추가로 가지므로 UserDetails로부터 물려받는 추상메소드들을 오버라이딩한다. */
	private Collection<GrantedAuthority> authorities; 
	
	/* setter 추가*/
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	@Override
	public String getPassword() {
		return this.memberPassword;
	}
	@Override
	public String getUsername() {
		return this.memberId;
	}
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	@Override
	public boolean isEnabled() {
		return false;
	}
}

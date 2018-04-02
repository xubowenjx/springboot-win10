package com.xbw.website.bo;

public class UserBo {
	private Integer userId;
	private String userName;
	private String userPassword;
	private String userType;
	private String gmtCreateDate;
	private String gmtModifyDate;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getGmtCreateDate() {
		return gmtCreateDate;
	}
	public void setGmtCreateDate(String gmtCreateDate) {
		this.gmtCreateDate = gmtCreateDate;
	}
	public String getGmtModifyDate() {
		return gmtModifyDate;
	}
	public void setGmtModifyDate(String gmtModifyDate) {
		this.gmtModifyDate = gmtModifyDate;
	}
	
}

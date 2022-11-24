package com.infosys.infytel.customer.dto;

public class LoginDTO {

	long phoneNo;
	String password;
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginDTO() {
		super();
	}
	@Override
	public String toString() {
		return "LoginDTO [phoneNo=" + phoneNo + ", password=" + password + "]";
	}

	
}

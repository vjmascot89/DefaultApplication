package com.example.napster.model;

public class LoginDataModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6834447851124297127L;
	private String userMobile;
	private String userEmail;
	private String userPassword;
	private Integer userId;
	private String userOtpHashcode;
	private UserDataModel userDataModel;
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}


	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public UserDataModel getUserDataModel() {
		return userDataModel;
	}

	public void setUserDataModel(UserDataModel userDataModel) {
		this.userDataModel = userDataModel;
	}

	public String getUserOtpHashcode() {
		return userOtpHashcode;
	}

	public void setUserOtpHashcode(String userOtpHashcode) {
		this.userOtpHashcode = userOtpHashcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			return this.getUserEmail().equals(
					((LoginDataModel) obj).getUserEmail())
					|| this.getUserMobile().equals(
							((LoginDataModel) obj).getUserMobile());
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return userEmail + "  " + userMobile + "  " + userPassword + " "
				+ userId;
	}
}

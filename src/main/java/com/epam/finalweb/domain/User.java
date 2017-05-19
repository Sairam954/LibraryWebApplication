package com.epam.finalweb.domain;

public class User {

	private int id;
	private String userName;

	private UserType userType;
	private String userEmail;
	private Long phoneNumber;

	public User() {

	}

	public User(int id, String userName, String password, UserType userType, String userEmail, Long phoneNumber) {

		this.id = id;
		this.userName = userName;

		this.userType = userType;
		this.userEmail = userEmail;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public UserType getUserType() {
		return userType;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userType != other.userType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userType=" + userType + ", userEmail=" + userEmail
				+ ", phoneNumber=" + phoneNumber + "]";
	}

}

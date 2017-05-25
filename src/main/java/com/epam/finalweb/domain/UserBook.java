package com.epam.finalweb.domain;

public class UserBook {

	Book book;
	String userList;
	Boolean isAdded;
	public UserBook(){
		
		
	}
	
	public UserBook(Book book, String userList,Boolean isAdded) {
		
		this.book = book;
		this.userList = userList;
		this.isAdded=isAdded;
	}
	
	public Boolean getIsAdded() {
		return isAdded;
	}

	public void setIsAdded(Boolean isAdded) {
		this.isAdded = isAdded;
	}

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getUserList() {
		return userList;
	}
	public void setUserList(String userList) {
		this.userList = userList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((userList == null) ? 0 : userList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBook other = (UserBook) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (userList == null) {
			if (other.userList != null)
				return false;
		} else if (!userList.equals(other.userList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBook [book=" + book + ", userList=" + userList +isAdded+ "]";
	}
	

}

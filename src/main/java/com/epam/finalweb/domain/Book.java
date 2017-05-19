package com.epam.finalweb.domain;

public class Book {

	private int bookId;
	private String bookTitle;
	private String bookAuthor;
	private BookType bookType;
	private String bookLanguage;
	private String description; 
	
	public Book(){
		
	}

	public Book(int bookId, String bookTitle, String bookAuthor, BookType bookType, String bookLanguage,
			String description) {
		
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookType = bookType;
		this.bookLanguage = bookLanguage;
		this.description = description;
	}

	public int getBookId() {
		return bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public BookType getBookType() {
		return bookType;
	}

	public String getBookLanguage() {
		return bookLanguage;
	}

	public String getDescription() {
		return description;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public void setBookLanguage(String bookLanguage) {
		this.bookLanguage = bookLanguage;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookAuthor == null) ? 0 : bookAuthor.hashCode());
		result = prime * result + bookId;
		result = prime * result + ((bookLanguage == null) ? 0 : bookLanguage.hashCode());
		result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
		result = prime * result + ((bookType == null) ? 0 : bookType.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookAuthor == null) {
			if (other.bookAuthor != null)
				return false;
		} else if (!bookAuthor.equals(other.bookAuthor))
			return false;
		if (bookId != other.bookId)
			return false;
		if (bookLanguage == null) {
			if (other.bookLanguage != null)
				return false;
		} else if (!bookLanguage.equals(other.bookLanguage))
			return false;
		if (bookTitle == null) {
			if (other.bookTitle != null)
				return false;
		} else if (!bookTitle.equals(other.bookTitle))
			return false;
		if (bookType != other.bookType)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor + ", bookType="
				+ bookType + ", bookLanguage=" + bookLanguage + ", description=" + description + "]";
	}
	
	
	



}

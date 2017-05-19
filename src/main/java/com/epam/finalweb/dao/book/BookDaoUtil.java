package com.epam.finalweb.dao.book;

public class BookDaoUtil {
	
	public static final String BOOKS_OFUSER = "select book_id,book_title,book_author,"
			+ "book_type,book_language,book_description from book  join "
			+ "book_has_user_details on book.book_id=book_has_user_details.book_book_id  " + "join user_details on "
			+ "book_has_user_details.user_details_user_id=user_details.user_id where user_id=?"
			+ " and book_language=?";

	public static final String BOOK_SEARCH = "select book_id,book_title,book_author,book_type,"
			+ "book_language,book_description from (SELECT * FROM book WHERE MATCH"
			+ " (book_title,book_author,book_description) AGAINST (?)) as"
			+ " t join book_has_user_details on t.book_id=book_has_user_details.book_book_id " + " join user_details"
			+ " on book_has_user_details.user_details_user_id=user_details.user_id where user_id=?";
	

}

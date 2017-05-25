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
	public static final String ALL_BOOK = "select group_concat(user_details_user_id) as "
			+ "'users', book.* from book  left outer join book_has_user_details "
			+ "on book.book_id=book_book_id group by book_book_id having book_language=?";
	public static final String ADD_BOOK = "INSERT INTO `book_has_user_details` (`book_book_id`, `user_details_user_id`) VALUES (?,?)";

	public static final String REMOVE_BOOK = "DELETE FROM `book_has_user_details` WHERE `book_book_id`=? and`user_details_user_id`=?";

	public static final String CREATE_BOOK = "INSERT INTO `book` ( `book_title`, `book_author`,"
			+ " `book_type`, `book_language`, `book_description`) VALUES (?,?,?,?,?)";
	public static final String ALL_BOOKADMIN = "select * from book where book_language=?";
	public static final String DELETE_BOOK = "DELETE FROM `book` WHERE `book_id`=?";
	public static final String UPDATE_BOOK = "UPDATE `book` SET `book_title`=?, `book_author`=?, `book_type`=?, `book_language`=?, `book_description`=? WHERE `book_id`=?";

	
	
}

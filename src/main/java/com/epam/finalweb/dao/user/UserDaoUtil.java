package com.epam.finalweb.dao.user;

public class UserDaoUtil {
	
	public  static final String USER_DETAILS = "select * from user_details where user_email=? and user_password=? ";
	public static final String EMAIL_VALIDATE = "select exists(select * from user_details where user_email=?)";
	public static final String USER_DETAILS_ALL = "select * from user_details  ";
	public static final String CREATE_USER = "INSERT INTO `user_details` (`user_name`, `user_email`, `user_password`, `user_type`, `user_phonenumber`) VALUES (?, ?, ?, ?, ?)";


}

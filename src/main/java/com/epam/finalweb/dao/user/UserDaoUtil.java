package com.epam.finalweb.dao.user;

public class UserDaoUtil {

	public static final String USER_DETAILS = "select * from user_details where user_email=? and user_password=? ";
	public static final String EMAIL_VALIDATE = "select exists(select * from user_details where user_email=?)";
	public static final String USER_DETAILS_ALL = "select * from user_details  ";
	public static final String CREATE_USER = "INSERT INTO `user_details` (`user_name`, `user_email`, `user_password`, `user_type`, `user_phonenumber`) VALUES (?, ?, ?, ?, ?)";
	public static final String GET_USER = "select * from user_details where user_id=?";
	public static final String UPDATE_USER = "UPDATE `user_details` SET `user_name`=?, `user_password`=?, `user_phonenumber`=? WHERE `user_id`=?";
	public static final String VERIFY_PASSWORD="select exists(select * from user_details where user_id=? and user_password=?)";
	public static final String ALL_USER="select * from user_details where user_type='user'";


}

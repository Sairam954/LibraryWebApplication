package com.epam.finalweb.dao;

import java.util.ResourceBundle;

public class ConfigParam {

	private static final String DB_NAME = "DB_NAME";
	private static final String DB_USER = "DB_USER";
	private static final String DB_PASSWORD = "DB_PASSWORD";
	private static final String DB_URL = "DB_URL";
	private static final String DB_CLASS = "DB_CLASS";
	private static final String DB_URI="DB_URI";
	private static final String BASE_NAME = "connectionconfig";
	
	private static final ResourceBundle RESOURCE = ResourceBundle.getBundle(BASE_NAME);

	public static String getDB_NAME() {
		return RESOURCE.getString(DB_NAME);
	}

	public static String getDB_USER() {
		return RESOURCE.getString(DB_USER);
	}

	public static String getDB_PASSWORD() {
		return RESOURCE.getString(DB_PASSWORD);
	}

	public static String getDB_URL() {
		return RESOURCE.getString(DB_URL);
	}

	public static String getDB_CLASS() {
		return RESOURCE.getString(DB_CLASS);
	}

	public static String getDB_URI() {
		return RESOURCE.getString(DB_URI);
	}

	

}

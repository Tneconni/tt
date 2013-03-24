package com.example.dbtest.db;

public class DataSource {
	public static String[] tabs = new String[]{
		"Create table midnight(id INTEGER PRIMARY KEY AUTOINCREMENT,name text,budget DOUBLE);",
		"CREATE TABLE articles(id INTEGER PRIMARY KEY AUTOINCREMENT, author text DEFAULT 'guest'," +
		" title text DEFAULT NULL, content text , publishTime datetime DEFAULT NULL, typeId text DEFAULT NULL , status INTEGER DEFAULT '0' );"
	};

}

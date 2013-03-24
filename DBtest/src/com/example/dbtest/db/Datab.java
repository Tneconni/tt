package com.example.dbtest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;


public class Datab {
	private SQLiteDatabase sqldb;
	
	private static int version = 1;//数据库版本号	  
	private static String myDBName = "mydb";
	private static mydbHelper mHelper;
	private SQLiteDatabase mDb;
	private final Context mctx;
	private static int tableCreated = 0;
	
	
	public void onCreate(Bundle savedInstanceState){
		
		//db = SQLiteDatabase.openOrCreateDatabase( factory, errorHandler);
		
	}
	
	public Datab(Context ctx){
		this.mctx = ctx;
	}
	
	public static Datab getInstance(Context c){
		
		return new Datab(c);
	}
	
	public class mydbHelper extends SQLiteOpenHelper{

		public mydbHelper(Context context) {
			super(context, myDBName, null, version);
			
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
//			String sql = "";
//			int l = DataSource.tabs.length;
//			for(int i = 0; i < l; i ++){
//				sql += DataSource.tabs[i];	
//			}
//			String sql = "Create table midnight(id INTEGER PRIMARY KEY AUTOINCREMENT,name text,budget DOUBLE)";
			String sql = "Create table articles(id INTEGER PRIMARY KEY AUTOINCREMENT, author text , title text , content text )";
			System.out.println("start create table");
			db.execSQL(sql); 
			System.out.println("end create table");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void open(){

		mHelper = new mydbHelper(mctx);
		sqldb = mHelper.getWritableDatabase();
		// I think that when the app was closed, the table must be droped, but it not
		if(tableCreated == 0){
			String tableTestSql = "DROP TABLE IF EXISTS articles";
			sqldb.execSQL(tableTestSql);
			
			String sql = "Create table articles(id INTEGER PRIMARY KEY AUTOINCREMENT, author text , title text , content text )";
			sqldb.execSQL(sql); 		
			tableCreated = 1;
		}
		
		
	}

	public void close(){
		sqldb.close();

	}
	
	public void insert(String table, String[] fields, String[] vals){
		ContentValues v = new ContentValues();
		int l = fields.length;
		for(int i = 0; i < l; i ++){
			v.put(fields[i], vals[i]);
		}
		sqldb.insert(table, null, v);
		
	}
	
	/**查询数据*/
	public Cursor select(String table, String[] columns)
	{
		Cursor cursor = sqldb.query
		(
				table, columns, null, null, null, null, null
		);
		return cursor;
	}
	
}

package com.example.dbtest;

import java.util.ArrayList;
import java.util.List;

import com.example.dbtest.db.Datab;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity {
	
	private Datab db;
	public ListView lv;
	DataAdapter data;
	private Button writeRecordBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView)findViewById(R.id.lv);
		writeRecordBtn = (Button) findViewById(R.id.writeRecord);
		
		testdb();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private void initView(){

		writeRecordBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent itt = new Intent(MainActivity.this, WriteNewRecord.class);
				startActivity(itt);
			}
			
		});
		
	}
	
	private void testdb(){
		db = Datab.getInstance(this.getApplicationContext());
		db.open();
		
//		Cursor c = db.select("midnight", new String[]{"name", "budget"});
		Cursor c = db.select("articles", new String[]{"title", "content"});
		
		List<String> list = new ArrayList<String>();
		while(c.moveToNext()){
			list.add(c.getString(0) +"::"+ c.getString(1));
			
			System.out.println(c.getString(0));
			System.out.println(c.getString(1));
		}
		
		ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,R.layout.data,list);
		
		lv.setAdapter(adapter);
		c.close();
		db.close();
	}

}

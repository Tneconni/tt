package com.example.dbtest;

import com.example.dbtest.db.Datab;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

public class WriteNewRecord extends Activity {
	private Datab db;
	private EditText recordTitle;
	private EditText recordContent;
	private Button recordSave;
	private Button recordCancel;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_record);
		initView();
		initDB();
	}
	
	
	private void initView(){
		recordTitle = (EditText)findViewById(R.id.recordTitle);
		recordContent = (EditText)findViewById(R.id.recordContent);
		recordSave = (Button)findViewById(R.id.recordSave);
		recordCancel = (Button)findViewById(R.id.recordCancel);
		
		recordSave.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				db.open();
				// TODO Auto-generated method stub
				String titleValue = recordTitle.getText().toString();
				String contentValue = recordContent.getText().toString();
				db.insert("articles", new String[]{"title","content"}, new String[]{titleValue,contentValue});
//				db.insert("midnight", new String[]{"name","budget"}, new String[]{titleValue,contentValue});
				db.close();
			}
			
		});
	}
	
	private void initDB(){
		db = Datab.getInstance(this.getApplicationContext());
		
	}

}

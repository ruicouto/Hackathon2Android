package com.example.helloworld;

import com.example.business.Nota;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class NewNoteActivity extends Activity {
	
	/**
	 * Called when activity is created
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_new_note);
		super.onCreate(savedInstanceState);
		findViewById(R.id.activity_new_note_save).setOnClickListener(save);
		//check previous activity has send data
		if(getIntent().getExtras()!=null && getIntent().getExtras().get("nota")!=null) {
			Nota n = (Nota) getIntent().getExtras().get("nota");
			EditText title = (EditText) findViewById(R.id.activity_new_note_title);
			EditText body = (EditText) findViewById(R.id.activity_new_note_body);
			title.setText(n.getTitle());
			body.setText(n.getBody());
		}
	}
	
	/**
	 * Callback for save button
	 */
	OnClickListener save = new OnClickListener() {
		@Override
		public void onClick(View v) {
			EditText title = (EditText) findViewById(R.id.activity_new_note_title);
			EditText body = (EditText) findViewById(R.id.activity_new_note_body);
			Nota n = new Nota(title.getText().toString(), body.getText().toString());
			//return the note to the previous activity
			Intent resultIntent = new Intent();
			resultIntent.putExtra("nota", n);
			setResult(Activity.RESULT_OK, resultIntent);
			NewNoteActivity.this.finish();
		}
	};

}

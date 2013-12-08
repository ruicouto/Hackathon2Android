package com.example.helloworld;

import com.example.business.Nota;
import com.example.business.Notas;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	//intent identifiers
	private final int NEW_NOTE = 1;
	private final int VIEW_NOTE = 2;
	
	//memory instance of notes
	private Notas notas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View button = findViewById(R.id.activity_main_new);
		button.setOnClickListener(click);
		notas = new Notas();
		
		ListView lv = (ListView) findViewById(R.id.activity_main_list);
		lv.setOnItemClickListener(itemPicked);
		
	}

	/**
	 * Create options menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Click listener for new note button
	 */
	private OnClickListener click = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, NewNoteActivity.class);
			startActivityForResult(i, NEW_NOTE);
		}
	};
	
	/**
	 * Called when called activity is finished with result.
	 * Only called if intent is launch with startActivityForResult
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
			case NEW_NOTE:
				if (resultCode == Activity.RESULT_OK) {
			        Nota n = (Nota) data.getExtras().get("nota");
			        notas.addNota(n);
			        refreshList();
			    }; break;
			case VIEW_NOTE:
				if (resultCode == Activity.RESULT_OK) {
			        Nota n = (Nota) data.getExtras().get("nota");
			        updateNote(n);
			        refreshList();
			    }; break;
		}
	};
	
	/**
	 * Update a note
	 * @param n
	 */
	private void updateNote(Nota n) {
		int p=-1;
		//if we have a note with the same title, remove it and add the returned note
        for(int i=0;i<notas.getNotas().size();i++) {
        	if(notas.getNotas().get(i).getTitle().equals(n.getTitle())) {
        		p=i;
        		break;
        	}
        }
        if(p!=-1) {
        	notas.getNotas().remove(p);
        } 
        notas.addNota(n);
	}
	
	/**
	 * Update list with the notes
	 */
	private void refreshList() {
		ArrayAdapter<Nota> lista = new ArrayAdapter<Nota>(this,
				android.R.layout.simple_list_item_1,
				notas.getNotas());
		ListView lv = (ListView) findViewById(R.id.activity_main_list);
		lv.setAdapter(lista);
	}
	
	/**
	 * Click listener for list item click
	 */
	OnItemClickListener itemPicked = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Nota n = notas.getNotas().get(arg2);
			Intent i = new Intent(MainActivity.this,NewNoteActivity.class);
			i.putExtra("nota", n);
			startActivityForResult(i, VIEW_NOTE);
			
		}
	};
	
}

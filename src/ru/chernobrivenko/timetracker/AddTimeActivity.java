package ru.chernobrivenko.timetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddTimeActivity extends Activity {

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_time);
	}
	
	public void onCancel(View view)
	{
//		Intent intent = new Intent(this,MainActivity.class);
//		startActivity(intent);
		finish();
	}
	
	public void onSave(View view)
	{
		Intent intent = getIntent();
		
		EditText timeView = (EditText)findViewById(R.id.time_view);
		intent.putExtra("time", timeView.getText().toString());
		
		EditText notesView = (EditText)findViewById(R.id.notes_view);
		intent.putExtra("notes", notesView.getText().toString());
		
		setResult(RESULT_OK, intent);
		
		finish();
	}
}

package ru.chernobrivenko.timetracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {
	private TimeTrackerAdapter timeTrackerAdapter;
	private TimeListDatabaseHelper databaseHelper;
	public static final int TIME_ENTRY_REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		databaseHelper = new TimeListDatabaseHelper(this);
		
		ListView lW = (ListView) findViewById(R.id.times_list);
		timeTrackerAdapter = new TimeTrackerAdapter(this, databaseHelper.getAllTimeRecords());
		lW.setAdapter(timeTrackerAdapter);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.time_list_menu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		if(item.getItemId() == R.id.add_time_menu_time)
		{
			Intent intent = new Intent(this, AddTimeActivity.class);
			//startActivity(intent);
			startActivityForResult(intent,TIME_ENTRY_REQUEST_CODE);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == TIME_ENTRY_REQUEST_CODE)
		{
			if(resultCode == RESULT_OK)
			{
				String notes = data.getStringExtra("notes");
				String time = data.getStringExtra("time");

				databaseHelper.saveRecord(time, notes);
				
				refreshActivity();
			}
		}
	}

	private void refreshActivity()
	{
		timeTrackerAdapter.changeCursor(databaseHelper.getAllTimeRecords());
	}
	
}


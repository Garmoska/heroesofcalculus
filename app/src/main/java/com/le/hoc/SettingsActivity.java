package com.le.hoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {
	public static final String BASE_VALUE_KEY = "com.le.hoc.BASE_VALUE_KEY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}

	public void buttonLaunchClick(View view){
		int num = Integer.parseInt((String) ((Button)findViewById(view.getId())).getText());
		Intent intent = new Intent(this, BattleActivity.class);
		intent.putExtra(BASE_VALUE_KEY, num);
		startActivity(intent);
	}
}
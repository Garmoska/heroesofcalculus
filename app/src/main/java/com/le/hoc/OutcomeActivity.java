package com.le.hoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OutcomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_outcome);
		String key = getIntent().getStringExtra(BattleActivity.OUTCOME_KEY);
		final TextView tvResult = (TextView)findViewById(R.id.tvResult);
		final ImageView ivOutcome = (ImageView)findViewById(R.id.ivOutcome);
		if (key.equals("hero")){
			tvResult.setText(R.string.outcome_victory);
			ivOutcome.setImageResource( R.drawable.dragon_win);
		}
		else{
			tvResult.setText(R.string.outcome_defeat);
			ivOutcome.setImageResource( R.drawable.dragon_dead);
		}
	}

	public void buttonTryAgainClick(View view){
		startActivity(new Intent(this, BattleActivity.class));
	}
}
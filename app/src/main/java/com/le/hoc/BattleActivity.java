package com.le.hoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;

import com.le.hoc.bl.Sprite;

import java.util.concurrent.ThreadLocalRandom;

public class BattleActivity extends AppCompatActivity {
	private Sprite heroSprite;
	private Sprite monsterSprite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);
	}

	@Override
	protected void onStart() {
		super.onStart();
		//set background picture
		int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
		final String mDrawableName = "battleground" + randomNum;// + ".png";
		int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
		ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.mainLayout);
		layout.setBackgroundResource(resID);
		//init both sprites
		heroSprite = new Sprite(R.id.ivHero, 700, 700);
		monsterSprite = new Sprite(R.id.ivMonster, 500, 500);
		heroSprite.setAnimation(this, R.drawable.dragon_idle);
		monsterSprite.setAnimation(this, R.drawable.jinn_idle);
		heroSprite.runAnimation();
		monsterSprite.runAnimation();
	}

}
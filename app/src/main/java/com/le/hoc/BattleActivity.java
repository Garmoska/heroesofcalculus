package com.le.hoc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.le.hoc.bl.CalculusFactory;
import com.le.hoc.bl.CalculusInfo;
import com.le.hoc.bl.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleActivity extends AppCompatActivity {
	private Sprite heroSprite;
	private Sprite monsterSprite;
	private List<CalculusInfo> tasks;
	private int currentTask;
	public static final String OUTCOME_KEY = "com.le.hoc.OUTCOME_KEY";
	private final Random rnd = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);
		//set background picture
		int randomNum = rnd.nextInt(4) + 1;
		int resID = getResourceByName("battleground" + randomNum);
		ConstraintLayout layout = findViewById(R.id.mainLayout);
		layout.setBackgroundResource(resID);
		//init both sprites
		List<String> monsters = new ArrayList<>();
		monsters.add("demon");
		monsters.add("lizard");
		monsters.add("jinn");
		monsters.add("medusa");
		randomNum = rnd.nextInt(monsters.size());
		heroSprite = new Sprite(R.id.ivHero, "dragon", 700, 700);
		monsterSprite = new Sprite(R.id.ivMonster, monsters.get(randomNum), 600, 600);
		heroSprite.setIdleAnimation(this);
		monsterSprite.setIdleAnimation(this);
		//
		int baseValue = getIntent().getIntExtra(SettingsActivity.BASE_VALUE_KEY, 2);
		//int baseValue = rnd.nextInt(8) + 1;
		//if (baseValue == 1) baseValue = 2;
		tasks = CalculusFactory.prepareTasks(baseValue, 5);
		currentTask = 0;
	}

	private void displayTask(){
		final TextView txtCalculus = findViewById(R.id.txtCalculus);
		final Button btnAnswer1 = findViewById(R.id.btnAnswer1);
		final Button btnAnswer2 = findViewById(R.id.btnAnswer2);
		final Button btnAnswer3 = findViewById(R.id.btnAnswer3);
		final Button btnAnswer4 = findViewById(R.id.btnAnswer4);
		final CalculusInfo ci = tasks.get(currentTask);
		txtCalculus.setText(ci.getCalculus());
		btnAnswer1.setText(ci.getAnswer(0));
		btnAnswer2.setText(ci.getAnswer(1));
		btnAnswer3.setText(ci.getAnswer(2));
		btnAnswer4.setText(ci.getAnswer(3));
	}

	@Override
	protected void onStart() {
		super.onStart();
		heroSprite.runAnimation();
		monsterSprite.runAnimation();
		displayTask();
	}

	public void buttonAnswerClick(View view){
		int answer = Integer.parseInt((String) ((Button)findViewById(view.getId())).getText());
		final CalculusInfo ci = tasks.get(currentTask);
		if (ci.getCorrectAnswer() == answer){
			executeAttack(heroSprite, monsterSprite);
		}
		else{
			executeAttack(monsterSprite, heroSprite);
		}
	}

	private int getResourceByName(String mDrawableName){
		return getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
	}

	private int getResourceById(String resName){
		return getResources().getIdentifier(resName, "id", getPackageName());
	}

	private AppCompatActivity getActivity(){
		return this;
	}

	private void executeAttack(Sprite attacker, Sprite defender){
		//remove hearth
		boolean isHeroAttacker = attacker.getResName().equals("dragon");
		//defender looses heart
		final String rn = isHeroAttacker ? "Monster" : "Hero";
		final String ivName = "iv" + rn + "Heart" + defender.getHearts();
		int idImageView = getResourceById(ivName);
		final ImageView iv = (ImageView)findViewById(idImageView);
		iv.setVisibility(View.INVISIBLE);
		defender.setHearts(defender.getHearts() - 1);
		attacker.setAttackAnimation(this);
		attacker.runAnimation();
		//play animation and redirect if needed
		if (defender.getHearts() <= 0){
			defender.setDieAnimation(getActivity());
			defender.runAnimation();
			final Handler handler = new Handler(Looper.getMainLooper());
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					//show outcome activity
					Intent intent = new Intent(getActivity(), OutcomeActivity.class);
					intent.putExtra(OUTCOME_KEY, isHeroAttacker );//? "hero" : "monster"
					startActivity(intent);
				}
			}, 3500);
		}
		else{
			defender.setHurtAnimation(this);
			attacker.runAnimation();
			final Handler handler = new Handler(Looper.getMainLooper());
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					attacker.setIdleAnimation(getActivity());
					defender.setIdleAnimation(getActivity());
					attacker.runAnimation();
					defender.runAnimation();
					currentTask++;
					displayTask();
				}
			}, 2000);
		}
	}
}
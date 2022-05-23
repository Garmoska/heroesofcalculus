package com.le.hoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.le.hoc.bl.CalculusFactory;
import com.le.hoc.bl.CalculusInfo;
import com.le.hoc.bl.Sprite;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BattleActivity extends AppCompatActivity {
	private Sprite heroSprite;
	private Sprite monsterSprite;
	private List<CalculusInfo> tasks;
	private int currentTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);
		//set background picture
		int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
		int resID = getResourceByName("battleground" + randomNum);
		ConstraintLayout layout = findViewById(R.id.mainLayout);
		layout.setBackgroundResource(resID);
		//init both sprites
		heroSprite = new Sprite(R.id.ivHero, "dragon", 700, 700);
		monsterSprite = new Sprite(R.id.ivMonster, "jinn", 500, 500);
		heroSprite.runIdleAnimation(this);
		monsterSprite.runIdleAnimation(this);
		//
		int baseValue = ThreadLocalRandom.current().nextInt(2, 9 + 1);
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
		btnAnswer1.setText(ci.getAnswers().get(0).toString());
		btnAnswer2.setText(ci.getAnswers().get(1).toString());
		btnAnswer3.setText(ci.getAnswers().get(2).toString());
		btnAnswer4.setText(ci.getAnswers().get(3).toString());
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

	private void executeAttack(Sprite attacker, Sprite defender){
		attacker.runAttackAnimation(this);
		defender.runHurtAnimation(this);
		attacker.runAnimation();
		defender.runAnimation();
		defender.setHearts(defender.getHearts() - 1);
		if (defender.getHearts() <= 0){
			//TODO end screen
		}
		else{
			currentTask++;
			displayTask();
		}
	}
}
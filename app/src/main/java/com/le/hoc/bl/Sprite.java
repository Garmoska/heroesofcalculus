package com.le.hoc.bl;

import android.graphics.drawable.AnimationDrawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.le.hoc.R;

public class Sprite {
	public void runAnimation(){
		if (animationDrawable != null) animationDrawable.start();
	}

	private int getResourceByName(AppCompatActivity activity, String mDrawableName){
		return activity.getResources().getIdentifier(mDrawableName, "drawable", activity.getPackageName());
	}

	public int getHearts() {
		return hearts;
	}

	public void setHearts(int hearts) {
		this.hearts = hearts;
	}

	private int hearts;

	private void runAnimationRoutines(AppCompatActivity activity, String action){
		ImageView iv = activity.findViewById(getIdImageView());
		iv.getLayoutParams().width = getWidth();
		iv.getLayoutParams().height = getHeight();
		//iv.requestLayout();
		//iv.setImageResource(idAnimation);
		int idAnimation = getResourceByName(activity, getResName() + "_" + action);
		iv.setBackgroundResource(idAnimation);
		animationDrawable = (AnimationDrawable) iv.getBackground();
	}

	public void runIdleAnimation(AppCompatActivity activity){
		runAnimationRoutines(activity, "idle");
	}

	public void runHurtAnimation(AppCompatActivity activity){
		runAnimationRoutines(activity, "hurt");
	}

	public void runAttackAnimation(AppCompatActivity activity){
		runAnimationRoutines(activity, "attack");
	}

	public void runDieAnimation(AppCompatActivity activity){
		runAnimationRoutines(activity, "die");
	}

	public Sprite(int idImageView, String resName, int width, int height){
		setResName(resName);
		setWidth(width);
		setHeight(height);
		setIdImageView(idImageView);
		setHearts(3);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	private int width;
	private AnimationDrawable animationDrawable;

	public int getIdImageView() {
		return idImageView;
	}

	public void setIdImageView(int idImageView) {
		this.idImageView = idImageView;
	}

	private int idImageView;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private int height;

	public String getResName() {
		return resName;
	}

	private void setResName(String resName) {
		this.resName = resName;
	}

	private String resName;
}

package com.le.hoc.bl;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Sprite {
	public void setAnimation(AppCompatActivity activity, int idAnimation){
		//TODO set w, h
		ImageView iv = (ImageView) activity.findViewById(getIdImageView());
		iv.setBackgroundResource(idAnimation);
		animationDrawable = (AnimationDrawable) iv.getBackground();
	}

	public void runAnimation(){
		if (animationDrawable != null) animationDrawable.start();
	}

	public Sprite(int idImageView, int width, int height){
		setWidth(width);
		setHeight(height);
		setIdImageView(idImageView);
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
}

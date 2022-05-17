package com.le.hoc.bl;

import java.util.ArrayList;
import java.util.List;

public class CalculusInfo {
	private String calculus;
	private int correctAnswer;

	public String getCalculus() {
		return calculus;
	}

	public void setCalculus(String calculus) {
		this.calculus = calculus;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public List<Integer> getAnswers() {
		return answers;
	}

	private List<Integer> answers;

	public CalculusInfo(){
		answers = new ArrayList<>();
	}
}

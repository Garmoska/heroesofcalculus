package com.le.hoc.bl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CalculusFactory {
	public static List<CalculusInfo> prepareTasks(int baseValue, int count){
		List<CalculusInfo> result = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			CalculusInfo ci = new CalculusInfo();
			int randomNum = ThreadLocalRandom.current().nextInt(1, 9 + 1);
			ci.setCalculus(baseValue + " x " + randomNum + " = ?");
			ci.setCorrectAnswer(baseValue * randomNum);
			final List<Integer> la = ci.getAnswers();
			final int numberOfAnswers = 4;
			int correctAnswerPosition = ThreadLocalRandom.current().nextInt(1, 4 + 1);
			for (int j = 0; j < numberOfAnswers; j++) {
				if (j == correctAnswerPosition - 1)
					la.add(ci.getCorrectAnswer());
				else {
					int wrongAnswer = ThreadLocalRandom.current().nextInt(1, 90 + 1);
					la.add(wrongAnswer);
				}
			}
			result.add(ci);
		}
		return result;
	}
}

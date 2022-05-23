package com.le.hoc;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import com.le.hoc.bl.CalculusFactory;
import com.le.hoc.bl.CalculusInfo;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() {
		assertEquals(4, 2 + 2);
	}

	@Test
	public void calculus_generation_isCorrect(){
		final List<CalculusInfo> lst = CalculusFactory.prepareTasks(8, 4);
		assertTrue(lst.size() > 0);
		for (CalculusInfo calculusInfo: lst) {
			assertNotNull(calculusInfo.getCalculus());
			assertTrue(calculusInfo.getCorrectAnswer() > 0);
			boolean found = false;
			for (int a: calculusInfo.getAnswers()){
				if (a == calculusInfo.getCorrectAnswer()){
					found = true;
					break;
				}
			}
			assertTrue(found);
		}
	}

	@Test
	public void random_generation(){
		for(int i = 0; i < 10; i++){
			int v = ThreadLocalRandom.current().nextInt(1, 9 + 1);
			System.out.println(v);
		}
	}
}
package com.le.hoc;

import org.junit.Test;

import static org.junit.Assert.*;

import com.le.hoc.bl.CalculusFactory;
import com.le.hoc.bl.CalculusInfo;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
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
}
package org.brahim.question;

import junit.framework.TestCase;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;

public class QuestionManagerTest extends TestCase {

    public void testGetNextQuestion() {
        QuestionManager questionManager = new QuestionManager();
        for (Category category : Category.values()) {
            for (int i = 0; i < 50; i++) {
                questionManager.getNextQuestion(category);
            }
            assertThrows(NoSuchElementException.class, () -> questionManager.getNextQuestion(category));
        }
    }
}
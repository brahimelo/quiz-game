package org.brahim.question;

import junit.framework.TestCase;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;

public class QuestionDeckManagerTest extends TestCase {

    public void testGetNextQuestion() {
        QuestionDeckManager questionDeckManager = new QuestionDeckManager();
        for (Category category : Category.values()) {
            assertTrue(questionDeckManager.getNextQuestion(category).toString().contains("0"));
            for (int i = 1; i < 49; i++) {
                questionDeckManager.getNextQuestion(category);
            }
            assertTrue(questionDeckManager.getNextQuestion(category).toString().contains("49"));
            assertTrue(questionDeckManager.getNextQuestion(category).toString().contains("0"));
            assertTrue(questionDeckManager.getNextQuestion(category).toString().contains("1"));
        }
    }
}
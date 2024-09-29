package org.brahim.question;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuestionManager {

    private static final int NUMBER_OF_QUESTIONS = 50;
    private final EnumMap<Category, LinkedList<Question>> questions = new EnumMap<>(Category.class);

    public QuestionManager() {
        initQuestions();
    }

    private void initQuestions() {
        for (Category category : Category.values()) {
            LinkedList<Question> questionList = IntStream.range(0, NUMBER_OF_QUESTIONS)
                    .mapToObj(i -> new Question(category.name() + " example question " + i + " ?"))
                    .collect(Collectors.toCollection(LinkedList::new));
            questions.put(category, questionList);
        }
    }

    public Question getNextQuestion(Category category) {
        return this.questions.get(category).stream()
                .filter(question -> !question.hasBeenAsked())
                .findFirst()
                .map(question -> {
                    question.setHasBeenAsked(true);
                    return question;
                })
                .orElseThrow(() -> new NoSuchElementException("No questions available for category: " + category));
    }
}

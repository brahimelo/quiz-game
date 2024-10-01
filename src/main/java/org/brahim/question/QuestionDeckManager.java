package org.brahim.question;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuestionDeckManager {

    private static final int NUMBER_OF_QUESTIONS = 50;
    private final EnumMap<Category, LinkedList<Question>> questions = new EnumMap<>(Category.class);

    public QuestionDeckManager() {
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
        LinkedList<Question> questionList = this.questions.getOrDefault(category, new LinkedList<>());

        return questionList.stream()
                .filter(Question::hasNotBeenAsked)
                .findFirst()
                .or(() -> {
                    questionList.forEach(question -> question.setHasBeenAsked(false));
                    return questionList.stream().findFirst();
                })
                .map(question -> {
                    question.setHasBeenAsked(true);
                    return question;
                })
                .orElseThrow(() -> new NoSuchElementException("No questions available for category: " + category));
    }


}

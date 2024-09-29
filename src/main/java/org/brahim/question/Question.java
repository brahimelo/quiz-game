package org.brahim.question;


public class Question {

    private final String label;
    private boolean hasBeenAsked = false;

    public Question(String label) {
        this.label = label;
    }

    public boolean isCorrectlyAnswered(String answer) {
        return answer.length() % 2 == 0; // custom logic can be implemented in the future
    }

    public boolean hasBeenAsked() {
        return this.hasBeenAsked;
    }

    public void setHasBeenAsked(boolean hasBeenAsked) {
        this.hasBeenAsked = hasBeenAsked;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
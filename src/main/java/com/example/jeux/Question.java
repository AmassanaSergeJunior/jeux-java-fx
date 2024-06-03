package com.example.jeux;

import java.util.List;

public class Question {
    private String question;
    private String correctAnswer;
    private List<String> distractors;

    public Question(String question, String correctAnswer, List<String> distractors) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.distractors = distractors;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getDistractors() {
        return distractors;
    }
}


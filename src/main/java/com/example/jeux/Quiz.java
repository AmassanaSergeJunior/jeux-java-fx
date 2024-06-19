package com.example.jeux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Quiz {
    private List<Question> questions;
    private int score;
    private int currentQuestionIndex;


    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        Collections.shuffle(this.questions);
    }

    public Question getNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex++);
        }
        return null;
    }

    public boolean checkAnswer(String answer, Question question) {
        if (question.getCorrectAnswer().equals(answer)) {
            score++;
            return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }
}

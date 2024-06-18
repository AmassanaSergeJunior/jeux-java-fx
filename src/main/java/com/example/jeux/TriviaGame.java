package com.example.jeux;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriviaGame {
    private Quiz quiz;
    private Question currentQuestion;
    private Label questionLabel;
    private List<RadioButton> answerButtons;
    private ToggleGroup answerGroup;
    private Label scoreLabel;

    public void start(Stage primaryStage) throws IOException {
        List<Question> questions = QuestionReader.readQuestions("/questions.txt");
        quiz = new Quiz(questions);

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        questionLabel = new Label();
        root.getChildren().add(questionLabel);

        answerGroup = new ToggleGroup();
        answerButtons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            RadioButton answerButton = new RadioButton();
            answerButton.setToggleGroup(answerGroup);
            answerButtons.add(answerButton);
            root.getChildren().add(answerButton);
        }

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> handleSubmit());
        root.getChildren().add(submitButton);

        scoreLabel = new Label("Score: 0");
        root.getChildren().add(scoreLabel);

        loadNextQuestion();

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setTitle("Trivia Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadNextQuestion() {
        currentQuestion = quiz.getNextQuestion();
        if (currentQuestion == null) {
            questionLabel.setText("Quiz Over!");
            for (RadioButton button : answerButtons) {
                button.setVisible(false);
            }
            return;
        }

        questionLabel.setText(currentQuestion.getQuestion());
        List<String> answers = new ArrayList<>(currentQuestion.getDistractors());
        answers.add(currentQuestion.getCorrectAnswer());
        Collections.shuffle(answers);

        for (int i = 0; i < answerButtons.size(); i++) {
            answerButtons.get(i).setText(answers.get(i));
            answerButtons.get(i).setVisible(true);
        }

        answerGroup.selectToggle(null);
    }

    private void handleSubmit() {
        RadioButton selectedButton = (RadioButton) answerGroup.getSelectedToggle();
        if (selectedButton != null) {
            String selectedAnswer = selectedButton.getText();
            quiz.checkAnswer(selectedAnswer, currentQuestion);
            scoreLabel.setText("Score: " + quiz.getScore());
            loadNextQuestion();
        }
    }
}

package com.example.jeux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionReader {
    public static List<Question> readQuestions(String fileName) throws IOException {
        List<Question> questions = new ArrayList<>();
        InputStream is = QuestionReader.class.getResourceAsStream(fileName);
        if (is == null) {
            throw new IOException("File not found: " + fileName);

        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts.length < 3) continue;
            String questionText = parts[0];
            String correctAnswer = parts[1];
            List<String> distractors = new ArrayList<>();
            for (int i = 2; i < parts.length; i++) {
                distractors.add(parts[i]);
            }
            questions.add(new Question(questionText, correctAnswer, distractors));
        }
        reader.close();
        return questions;
    }
}

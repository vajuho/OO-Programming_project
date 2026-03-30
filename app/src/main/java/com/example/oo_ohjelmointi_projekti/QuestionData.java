package com.example.oo_ohjelmointi_projekti;

import java.util.ArrayList;

public class QuestionData {
    private String questionText;
    private ArrayList<String> options;
    private int correctAnswerIndex;

    public QuestionData(String questionText, ArrayList<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

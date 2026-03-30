package com.example.oo_ohjelmointi_projekti;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuizViewHolder extends RecyclerView.ViewHolder {

    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        questionText = itemView.findViewById(R.id.QuestionId);
        optionsGroup = itemView.findViewById(R.id.OptionsGroup);

    }

    public void setQuestion(QuestionData question, int position) {
        questionText.setText(question.getQuestionText());

    }



}
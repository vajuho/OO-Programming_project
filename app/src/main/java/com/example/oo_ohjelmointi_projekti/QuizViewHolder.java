package com.example.oo_ohjelmointi_projekti;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizViewHolder extends RecyclerView.ViewHolder {

    private TextView questionText;
    private RadioGroup optionsGroup;

    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        questionText = itemView.findViewById(R.id.QuestionId);
        optionsGroup = itemView.findViewById(R.id.OptionsGroup);

    }

    public void setQuestion(QuestionData question, int position, ArrayList<Integer> selectedAnswers) {
        questionText.setText(question.getQuestionText());
        ArrayList<String> options = question.getOptions();

        optionsGroup.setOnCheckedChangeListener(null);
        optionsGroup.clearCheck();

        for (int i = 0; i < optionsGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) optionsGroup.getChildAt(i);
            radioButton.setText(options.get(i));
            radioButton.setId(i);
        }

        if (selectedAnswers.get(position) != null) {
            optionsGroup.check(selectedAnswers.get(position));
        }

        optionsGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            // checkedId on nyt 0, 1, 2 tai 3
            selectedAnswers.set(position, checkedId);
        });
}

    public int getSelectedAnswerIndex() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();
        View radioButton = optionsGroup.findViewById(selectedId);
        return optionsGroup.indexOfChild(radioButton);
    }
}
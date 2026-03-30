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

        ((RadioButton)optionsGroup.getChildAt(0)).setText(options.get(0));
        ((RadioButton)optionsGroup.getChildAt(1)).setText(options.get(1));
        ((RadioButton)optionsGroup.getChildAt(2)).setText(options.get(2));
        ((RadioButton)optionsGroup.getChildAt(3)).setText(options.get(3));

        optionsGroup.setOnCheckedChangeListener(null);
        optionsGroup.clearCheck();

        if (selectedAnswers.get(position) != null) {
            optionsGroup.check(selectedAnswers.get(position));
        }

        optionsGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            selectedAnswers.set(position, checkedId);
        });
}

    public int getSelectedAnswerIndex() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();
        View radioButton = optionsGroup.findViewById(selectedId);
        return optionsGroup.indexOfChild(radioButton);
    }
}
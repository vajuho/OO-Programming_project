package com.example.oo_ohjelmointi_projekti;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraExtensionSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private Context context;
    private ArrayList<QuestionData> questionList;
    private ArrayList<Integer> selectedAnswers;

    public QuizAdapter(Context context, ArrayList<QuestionData> questionList) {
        this.context = context;
        this.questionList = questionList;

        selectedAnswers = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            selectedAnswers.add(null);
        }
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuizViewHolder(LayoutInflater.from(context).inflate(R.layout.question_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.setQuestion(questionList.get(position), position, selectedAnswers);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public int getScore() {
        int score = 0;
        for(int i = 0; i < questionList.size(); i++) {
            Integer selected = selectedAnswers.get(i);
            if (selected != null && selected == questionList.get(i).getCorrectAnswerIndex()) {
                score++;
            }
        }
        return score;
    }
}


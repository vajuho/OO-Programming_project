package com.example.oo_ohjelmointi_projekti.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oo_ohjelmointi_projekti.ComparedCityData;
import com.example.oo_ohjelmointi_projekti.MunicipalityData;
import com.example.oo_ohjelmointi_projekti.PopulationData;
import com.example.oo_ohjelmointi_projekti.QuestionData;
import com.example.oo_ohjelmointi_projekti.R;

import java.util.ArrayList;


public class QuizFragment extends Fragment {

    private int currentScore = 0;

    public QuizFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        ArrayList<QuestionData> questionsList = new ArrayList<>();
        MunicipalityData municipalityData = MunicipalityData.getInstance();

        if (municipalityData.getPopulations() != null) {
            ArrayList<PopulationData> populationList = municipalityData.getPopulations();
            int population = populationList.get(populationList.size() - 1).getAmount();
            int year = populationList.get(populationList.size() - 1).getYear();

            //questionsList.add(new QuestionData());

        }


        return view;
    }
}
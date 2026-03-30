package com.example.oo_ohjelmointi_projekti.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oo_ohjelmointi_projekti.ComparedCityData;
import com.example.oo_ohjelmointi_projekti.MunicipalityData;
import com.example.oo_ohjelmointi_projekti.PopulationData;
import com.example.oo_ohjelmointi_projekti.PopulationDataStorage;
import com.example.oo_ohjelmointi_projekti.QuestionData;
import com.example.oo_ohjelmointi_projekti.R;

import java.util.ArrayList;
import java.util.Collections;


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
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        ArrayList<QuestionData> questionsList = new ArrayList<>();
        ArrayList<String> optionsList = new ArrayList<>();
        MunicipalityData municipalityData = MunicipalityData.getInstance();
        PopulationDataStorage populationDataStorage = PopulationDataStorage.getInstance();

        if (municipalityData.getPopulations() != null) {
            ArrayList<PopulationData> populationList = municipalityData.getPopulations();
            int population = populationList.get(populationList.size() - 1).getAmount();
            int year = populationList.get(populationList.size() - 1).getYear();

            optionsList.add(String.valueOf(population));
            optionsList.add(String.valueOf(population * (0.6 + (Math.random() * (0.7)))));
            optionsList.add(String.valueOf(population * (0.6 + (Math.random() * (0.7)))));
            optionsList.add(String.valueOf(population * (0.6 + (Math.random() * (0.7)))));
            Collections.shuffle(optionsList);
            int correctAnswerIndex = optionsList.indexOf(population);

            questionsList.add(new QuestionData("Mikä oli väkiluku kunnassa " + populationDataStorage.getMunicipality() + " vuonna " + year,
                    optionsList, correctAnswerIndex));




        }


        return view;
    }
}
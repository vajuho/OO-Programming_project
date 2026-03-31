package com.example.oo_ohjelmointi_projekti.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.oo_ohjelmointi_projekti.ComparedCityData;
import com.example.oo_ohjelmointi_projekti.MunicipalityData;
import com.example.oo_ohjelmointi_projekti.PopulationData;
import com.example.oo_ohjelmointi_projekti.PopulationDataStorage;
import com.example.oo_ohjelmointi_projekti.QuestionData;
import com.example.oo_ohjelmointi_projekti.QuizAdapter;
import com.example.oo_ohjelmointi_projekti.R;

import java.util.ArrayList;
import java.util.Arrays;
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

    private void addQuestionToList(ArrayList<QuestionData> questionsList, ArrayList<String> optionsList,
                                   String questionText, double correctAnswer, double low, double high) {
        optionsList.add(String.valueOf(correctAnswer));
        for (int i = 0; i < 3; i++) {
            double fakeValue = correctAnswer * (low + (Math.random() * high));
            optionsList.add(String.valueOf(fakeValue));
        }
        Collections.shuffle(optionsList);
        int correctAnswerIndex = optionsList.indexOf(String.valueOf(correctAnswer));
        questionsList.add(new QuestionData(questionText, new ArrayList<>(optionsList), correctAnswerIndex));
        optionsList.clear();
        System.out.println(questionsList);
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
            int populationLatest = populationList.get(populationList.size() - 1).getAmount();
            int populationChange = populationList.get(populationList.size() - 1).getPopulationIncrease();
            int yearLatest = populationList.get(populationList.size() - 1).getYear();
            int yearRandomIndex = (int) (Math.random() * populationList.size());
            int yearRandom = populationList.get(yearRandomIndex).getYear();
            int populationDuringRandomYear = populationList.get(yearRandomIndex).getAmount();
            double temperature = municipalityData.getWeather().getTemperature();
            String weatherDescription = municipalityData.getWeather().getDescription();
            int carAmount = Integer.parseInt(municipalityData.getCarData().getCarAmount());
            String yearPlusEmployment = municipalityData.getEmploymentData().getEmploymentRate();
            int employmentRate = (int) Double.parseDouble(yearPlusEmployment.split(":")[1]);
            String yearPlusSufficiency = municipalityData.getEmploymentData().getEmploymentSelfSufficiency();
            int employmentSelfSufficiency = (int) Double.parseDouble(yearPlusSufficiency.split(":")[1]);

            int correctAnswerIndex = 0;
            double low = 0.6;
            double high = 0.7;
            ArrayList<String> weatherDescriptionsList = new ArrayList<>(Arrays.asList(
                    "thunderstorm with light rain", "thunderstorm with rain", "thunderstorm with heavy rain",
                    "light thunderstorm", "thunderstorm", "heavy thunderstorm", "ragged thunderstorm",
                    "thunderstorm with light drizzle", "thunderstorm with drizzle", "thunderstorm with heavy drizzle",
                    "light intensity drizzle", "drizzle", "heavy intensity drizzle", "light intensity drizzle rain", "drizzle rain",
                    "heavy intensity drizzle rain", "shower rain and drizzle", "heavy shower rain and drizzle", "shower drizzle",
                    "light rain", "moderate rain", "heavy intensity rain", "very heavy rain", "extreme rain", "freezing rain",
                    "light intensity shower rain", "shower rain", "heavy intensity shower rain", "ragged shower rain",
                    "light snow", "snow", "heavy snow", "sleet", "light shower sleet", "shower sleet",
                    "light rain and snow", "rain and snow", "light shower snow", "shower snow", "heavy shower snow",
                    "mist", "smoke", "haze", "sand/dust whirls", "fog", "sand", "dust", "volcanic ash", "squalls", "tornado",
                    "clear sky", "few clouds", "scattered clouds", "broken clouds", "overcast clouds"));
            int descriptionListLength = weatherDescriptionsList.size();

            // question 1
            addQuestionToList(questionsList, optionsList,
                    "Mikä oli väkiluku kunnassa " + populationDataStorage.getMunicipality() + " vuonna " + yearLatest + "?",
                    populationLatest, low, high);

            // question 2
            addQuestionToList(questionsList, optionsList,
                    "Mikä oli väkiluvun muutos kunnassa " + populationDataStorage.getMunicipality() + " vuonna " + yearLatest + "?",
                    populationChange, low, high);

            // question 3
            addQuestionToList(questionsList, optionsList,
                    "Mikä on tämänhetkinen lämpötila kunnassa " + populationDataStorage.getMunicipality() + "?",
                    temperature, low, high);

            // question 4
            addQuestionToList(questionsList, optionsList,
                    "Kuinka monta henkilöautoa on liikennekäytössä kunnassa " + populationDataStorage.getMunicipality() + "?",
                    carAmount, low, high);

            // question 5
            addQuestionToList(questionsList, optionsList,
                    "Mikä oli työllisyysaste kunnassa " + populationDataStorage.getMunicipality() + "?",
                    employmentRate, low, high);

            // question 6
            addQuestionToList(questionsList, optionsList,
                    "Mikä oli työpaikkojen omavaraisuus kunnassa " + populationDataStorage.getMunicipality() + "?",
                    employmentSelfSufficiency, low, high);

            // question 7
            addQuestionToList(questionsList, optionsList,
                    "Mikä oli väkiluku kunnassa " + populationDataStorage.getMunicipality() + " vuonna " + yearRandom + "?",
                    populationDuringRandomYear, low, high);

            // question 8
            optionsList.add(String.valueOf(weatherDescription));
            optionsList.add(weatherDescriptionsList.get((int) (Math.random() * descriptionListLength)));
            optionsList.add(weatherDescriptionsList.get((int) (Math.random() * descriptionListLength)));
            optionsList.add(weatherDescriptionsList.get((int) (Math.random() * descriptionListLength)));
            Collections.shuffle(optionsList);
            correctAnswerIndex = optionsList.indexOf(weatherDescription);
            System.out.println(optionsList + " " + correctAnswerIndex);

            questionsList.add(new QuestionData("Minkälainen on tämänhetkinen sää kunnassa " + populationDataStorage.getMunicipality() + "?",
                    optionsList, correctAnswerIndex));
            optionsList.clear();


            RecyclerView recyclerView = view.findViewById(R.id.QuizRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            QuizAdapter adapter = new QuizAdapter(getContext(), questionsList);
            recyclerView.setAdapter(adapter);

            TextView scoreResult = view.findViewById(R.id.ScoreResultText);
            Button checkButton = view.findViewById(R.id.CheckButton);
            if (checkButton != null) {
                checkButton.setOnClickListener(v -> {
                    int score = adapter.getScore();
                    if (scoreResult != null) {
                        scoreResult.setText("Pisteet: " + score + " / " + questionsList.size());
                    }
                });
            }
        }
        return view;
    }
}
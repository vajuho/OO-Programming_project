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

    public QuizFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    private void addQuestionToList(ArrayList<QuestionData> questionsList, ArrayList<String> optionsList,
                                   String questionText, Number correctAnswer) {
        String correctString = String.valueOf(correctAnswer);
        double low = 0.3;
        double high = 1.2;
        optionsList.add(correctString);

        double correctDouble = correctAnswer.doubleValue();

        for (int i = 0; i < 3; i++) {
            double fakeDouble = correctDouble * (low + (Math.random() * high));
            String fakeString;

            if (correctAnswer instanceof Integer) {
                fakeString = String.valueOf((int) fakeDouble);
            } else {
                fakeString = String.format("%.1f", fakeDouble);
            }

            if (fakeString.equals(correctString)) {
                fakeString = String.valueOf(correctAnswer instanceof Integer ?
                          (int)fakeDouble + 1 : fakeDouble + 0.5);
            }
            optionsList.add(fakeString);
        }
        Collections.shuffle(optionsList);

        int findCorrectAnswerIndex = optionsList.indexOf(correctString);

        questionsList.add(new QuestionData(questionText, new ArrayList<>(optionsList), findCorrectAnswerIndex));
        optionsList.clear();
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
            double carsPerPopulation = Math.round(((((double) carAmount / populationLatest) * 100) * 10.0) / 10.0);
            int correctAnswerIndex = 0;

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
                    populationLatest);

            // question 2
            addQuestionToList(questionsList, optionsList,
                    "Mikä oli väkiluvun muutos kunnassa " + populationDataStorage.getMunicipality() + " vuonna " + yearLatest + "?",
                    populationChange);

            // question 3
            addQuestionToList(questionsList, optionsList,
                    "Kuinka monta henkilöautoa on liikennekäytössä kunnassa " + populationDataStorage.getMunicipality() + "?",
                    carAmount);

            // question 4
            addQuestionToList(questionsList, optionsList,
                    "Mikä oli työllisyysaste kunnassa " + populationDataStorage.getMunicipality() + "?",
                    employmentRate);

            // question 5
            addQuestionToList(questionsList, optionsList,
                    "Mikä oli työpaikkojen omavaraisuus kunnassa " + populationDataStorage.getMunicipality() + "?",
                    employmentSelfSufficiency);

            // question 6
            addQuestionToList(questionsList, optionsList,
                    "Mikä oli väkiluku kunnassa " + populationDataStorage.getMunicipality() + " vuonna " + yearRandom + "?",
                    populationDuringRandomYear);

            //question 7
            addQuestionToList(questionsList, optionsList,
                    "Mikä on tämänhetkinen lämpötila kunnassa " + populationDataStorage.getMunicipality() + "?",
                    temperature);

            // question 8
            optionsList.clear();
            optionsList.add(String.valueOf(weatherDescription));
            int counter = 0;
            while (counter < 3) {
                String option = weatherDescriptionsList.get((int) (Math.random() * descriptionListLength));
                if (!option.equals(weatherDescription)) {
                    optionsList.add(option);
                    counter++;
                }
            }
            Collections.shuffle(optionsList);
            correctAnswerIndex = optionsList.indexOf(weatherDescription);

            questionsList.add(new QuestionData("Minkälainen on tämänhetkinen sää kunnassa " + populationDataStorage.getMunicipality() + "?",
                    new ArrayList<>(optionsList), correctAnswerIndex));
            optionsList.clear();

            // question 9
            addQuestionToList(questionsList, optionsList,
                    "Kuinka monta autoa sataa asukasta kohden on kunnassa " + populationDataStorage.getMunicipality() + "?",
                    carsPerPopulation);


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
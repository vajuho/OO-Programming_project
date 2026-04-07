package com.example.oo_ohjelmointi_projekti.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Area;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.example.oo_ohjelmointi_projekti.MunicipalityData;
import com.example.oo_ohjelmointi_projekti.PopulationData;
import com.example.oo_ohjelmointi_projekti.PopulationDataStorage;
import com.example.oo_ohjelmointi_projekti.R;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.ValueDataEntry;

import java.util.ArrayList;
import java.util.List;

public class CityInfoFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_info, container, false);

        TextView cityNameText = view.findViewById(R.id.CityOneNameText);
        TextView cityDescriptionText = view.findViewById(R.id.CityDescriptionText);
        TextView cityWikiUrlText = view.findViewById(R.id.CityWikiUrlText);
        TextView populationAmountText = view.findViewById(R.id.CityOnePopulationAmountText);
        TextView populationChangeText = view.findViewById(R.id.PopulationChangeText);
        TextView employmentRateText = view.findViewById(R.id.EmploymentRateText);
        TextView employmentSelfSufficiencyText = view.findViewById(R.id.EmploymentSelfSufficiencyText);
        TextView temperatureText = view.findViewById(R.id.TemperatureText);
        TextView weatherDescriptionText = view.findViewById(R.id.WeatherDescriptionText);
        TextView carAmountText = view.findViewById(R.id.CarAmountText);
        ImageView carImageView = view.findViewById(R.id.CarImageView);
        AnyChartView populationChart = view.findViewById(R.id.populationChart);

        PopulationDataStorage populationDataStorage = PopulationDataStorage.getInstance();
        MunicipalityData municipalityData = MunicipalityData.getInstance();

        cityNameText.setText(populationDataStorage.getMunicipality());

        // Below this we have multiple null checkers so that the program does not crash.

        if (municipalityData.getWikiData() != null) {
            cityDescriptionText.setText(municipalityData.getWikiData().getWikiUrlAndDescription().get(0));
            cityWikiUrlText.setText(municipalityData.getWikiData().getWikiUrlAndDescription().get(1));
        }

        if (municipalityData.getPopulations() != null) {
            populationAmountText.setText("Väkiluku: " + municipalityData.getPopulations().get(municipalityData.getPopulations().size() - 1).getAmount());
            populationChangeText.setText("Väestönmuutos: " + municipalityData.getPopulations().get(municipalityData.getPopulations().size() - 1).getPopulationIncrease());

            // Data visualization part begins
            // The code below draws some inspiration from AnyChart's GitHub samples.
            ArrayList<PopulationData> populations = municipalityData.getPopulations();
            List<DataEntry> data = new ArrayList<>();
            for (PopulationData i : populations) {
                data.add(new ValueDataEntry(String.valueOf(i.getYear()), Integer.valueOf(i.getAmount())));
            }

            Cartesian cartesian = AnyChart.area();

            cartesian.animation(true);  // Cool animation from AnyChart.

            cartesian.title("Väkiluku vuosittain");

            cartesian.yAxis(0).title("Väkiluku");
            cartesian.xAxis(0).title("Vuosi");

            cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

            Area series = cartesian.area(data);
            series.name("Väkiluku");
            series.hovered().markers().enabled(true).type(MarkerType.CIRCLE).size(5d);

            cartesian.background().stroke("#444444");

            populationChart.setChart(cartesian);
        }

        if (municipalityData.getEmploymentData() != null) {
            employmentRateText.setText("Työllisyysaste vuonna " + municipalityData.getEmploymentData().getEmploymentRate() + "%");
            employmentSelfSufficiencyText.setText("Työpaikkojen omavaraisuus vuonna " + municipalityData.getEmploymentData().getEmploymentSelfSufficiency() + "%");
        }

        if (municipalityData.getWeather() != null) {
            temperatureText.setText(String.format("Lämpötila nyt: %.1f °C", municipalityData.getWeather().getTemperature()));
            weatherDescriptionText.setText("Sää nyt: " + municipalityData.getWeather().getDescription());
        }

        if (municipalityData.getCarData() != null && municipalityData.getCarData().getCarAmount() != null) {
            carAmountText.setText("Autojen määrä: " + municipalityData.getCarData().getCarAmount());

            int carAmount = Integer.parseInt(municipalityData.getCarData().getCarAmount());
            int tierOne = 2000;
            int tierTwo = 10000;
            int tierThree = 50000;

            if (carAmount < tierOne) {
                carImageView.setImageResource(R.drawable.car_tier_1);
            } else if (tierOne <= carAmount && carAmount < tierTwo) {
                carImageView.setImageResource(R.drawable.car_tier_2);
            } else if (tierTwo <= carAmount && carAmount < tierThree) {
                carImageView.setImageResource(R.drawable.car_tier_3);
            } else if (carAmount >= tierThree) {
                carImageView.setImageResource(R.drawable.car_tier_4);
            }
        } else {
            carImageView.setImageResource(R.drawable.car_unknown);
        }
        return view;
    }
}
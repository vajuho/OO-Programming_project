package com.example.oo_ohjelmointi_projekti;
public class WeatherData {
    private String description;
    private String temperature;

    public WeatherData(String description, String temp) {
        this.description = description;
        this.temperature = temp;
    }
    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        double temperatureKelvin = Double.parseDouble(temperature);
        double temperatureCelsius = temperatureKelvin - 273.15;
        return temperatureCelsius;
    }
}

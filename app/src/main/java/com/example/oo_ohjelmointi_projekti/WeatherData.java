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
        double temperatureCelsius = Math.round((temperatureKelvin - 273.15) * 10.0) / 10.0;
        return temperatureCelsius;
    }
}

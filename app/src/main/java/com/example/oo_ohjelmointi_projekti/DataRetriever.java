package com.example.oo_ohjelmointi_projekti;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DataRetriever {
    private final String API_KEY = "1a6f8d56679d0a39964b76ff40cc557c";
    private final String CONVERTER_BASE_URL = "https://api.openweathermap.org/geo/1.0/direct?q=%s&limit=5&appid=%s";
    private final String WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";

    public ArrayList<PopulationData> getPopulation(Context context, String area) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode areas = null;
        try {
            areas = objectMapper.readTree(new URL("https://statfin.stat.fi/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (JsonNode node : areas.get("variables").get(1).get("values")) {
            values.add(node.asText());
        }
        for (JsonNode node : areas.get("variables").get(1).get("valueTexts")) {
            keys.add(node.asText());
        }

        HashMap<String, String> municipalityCodes = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            municipalityCodes.put(keys.get(i), values.get(i));
        }
        String code = null;
        code = null;
        code = municipalityCodes.get(area);

        try {
            URL url = new URL("https://pxdata.stat.fi/PxWeb/api/v1/fi/StatFin/synt/statfin_synt_pxt_12dy.px");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            JsonNode jsonInputString = objectMapper.readTree(context.getResources().openRawResource(R.raw.populationsearch));

            ((ObjectNode) jsonInputString.get("query").get(0).get("selection")).putArray("values").add(code);

            byte[] input = objectMapper.writeValueAsBytes(jsonInputString);
            OutputStream os = con.getOutputStream();
            os.write(input, 0, input.length);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            JsonNode data = objectMapper.readTree(response.toString());

            ArrayList<String> years = new ArrayList<>();
            ArrayList<String> populations = new ArrayList<>();
            ArrayList<String> populationIncrease = new ArrayList<>();

            int counter = 0;

            for (JsonNode node : data.get("dimension").get("Vuosi").get("category").get("label")) {
                years.add(node.asText());
            }

            for (JsonNode node : data.get("value")) {
                if (counter % 2 == 0) {
                    populationIncrease.add(node.asText());
                }
                else {
                    populations.add(node.asText());
                }
                counter ++;
            }

            ArrayList<PopulationData> populationData = new ArrayList<>();

            for (int i = 0; i < years.size(); i++) {
                populationData.add(new PopulationData(Integer.valueOf(years.get(i)), Integer.valueOf(populations.get(i)), Integer.valueOf(populationIncrease.get(i))));
            }
            return populationData;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEmploymentRate(Context context, String area) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode areas = null;
        try {
            areas = objectMapper.readTree(new URL("https://statfin.stat.fi/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (JsonNode node : areas.get("variables").get(1).get("values")) {
            values.add(node.asText());
        }
        for (JsonNode node : areas.get("variables").get(1).get("valueTexts")) {
            keys.add(node.asText());
        }

        HashMap<String, String> municipalityCodes = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            municipalityCodes.put(keys.get(i), values.get(i));
        }
        String code = null;
        code = null;
        code = municipalityCodes.get(area);

        try {
            URL url = new URL("https://pxdata.stat.fi/PxWeb/pxweb/fi/StatFin/StatFin__tyokay/statfin_tyokay_pxt_115x.px");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            JsonNode jsonInputString = objectMapper.readTree(context.getResources().openRawResource(R.raw.employmentratesearch));

            ((ObjectNode) jsonInputString.get("query").get(0).get("selection")).putArray("values").add(code);

            byte[] input = objectMapper.writeValueAsBytes(jsonInputString);
            OutputStream os = con.getOutputStream();
            os.write(input, 0, input.length);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            JsonNode data = objectMapper.readTree(response.toString());

            ArrayList<String> years = new ArrayList<>();
            ArrayList<String> populations = new ArrayList<>();
            ArrayList<String> populationIncrease = new ArrayList<>();
            int counter = 0;

            JsonNode labels = data.get("dimension").get("Vuosi").get("category").get("label");
            String year = labels.get(String.valueOf(labels.size() - 1)).asText();

            JsonNode rates = data.get("value");
            String rate = values.get(rates.size() - 1);
            String yearPlusRate = year + ": " + rate;

            return yearPlusRate;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEmploymentSelfSufficiency(Context context, String area) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode areas = null;
        try {
            areas = objectMapper.readTree(new URL("https://statfin.stat.fi/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (JsonNode node : areas.get("variables").get(1).get("values")) {
            values.add(node.asText());
        }
        for (JsonNode node : areas.get("variables").get(1).get("valueTexts")) {
            keys.add(node.asText());
        }

        HashMap<String, String> municipalityCodes = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            municipalityCodes.put(keys.get(i), values.get(i));
        }
        String code = null;
        code = null;
        code = municipalityCodes.get(area);

        try {
            URL url = new URL("https://pxdata.stat.fi/PxWeb/api/v1/fi/StatFin/tyokay/statfin_tyokay_pxt_125s.px");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            JsonNode jsonInputString = objectMapper.readTree(context.getResources().openRawResource(R.raw.employmentselfsufficiency));

            ((ObjectNode) jsonInputString.get("query").get(0).get("selection")).putArray("values").add(code);

            byte[] input = objectMapper.writeValueAsBytes(jsonInputString);
            OutputStream os = con.getOutputStream();
            os.write(input, 0, input.length);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            JsonNode data = objectMapper.readTree(response.toString());

            JsonNode labels = data.get("dimension").get("Vuosi").get("category").get("label");
            String year2 = labels.get(String.valueOf(labels.size() - 1)).asText();

            JsonNode sufficiencyValues = data.get("value");
            String sufficiency = sufficiencyValues.get(sufficiencyValues.size() - 1).asText();
            String yearPlusSufficiency = year2 + ": " + sufficiency;

            return yearPlusSufficiency ;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCarAmount(Context context, String area) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode areas = null;
        try {
            areas = objectMapper.readTree(new URL("https://statfin.stat.fi/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (JsonNode node : areas.get("variables").get(1).get("values")) {
            values.add(node.asText());
        }
        for (JsonNode node : areas.get("variables").get(1).get("valueTexts")) {
            keys.add(node.asText());
        }

        HashMap<String, String> municipalityCodes = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            municipalityCodes.put(keys.get(i), values.get(i));
        }
        String code = null;
        code = null;
        code = municipalityCodes.get(area);

        try {
            URL url = new URL("https://trafi2.stat.fi/PXWeb/api/v1/fi/TraFi/Liikennekaytossa_olevat_ajoneuvot/010_kanta_tau_101.px");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            JsonNode jsonInputString = objectMapper.readTree(context.getResources().openRawResource(R.raw.caramountseach));

            ((ObjectNode) jsonInputString.get("query").get(0).get("selection")).putArray("values").add(code);

            byte[] input = objectMapper.writeValueAsBytes(jsonInputString);
            OutputStream os = con.getOutputStream();
            os.write(input, 0, input.length);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            JsonNode data = objectMapper.readTree(response.toString());

            JsonNode value = data.get("value").get(0);
            String carAmount = value.toString();

            return carAmount;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getWikiData(String area) {
        try {
            String urlString = "https://fi.wikipedia.org/api/rest_v1/page/summary/" + area;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.toString());

            String description = json.get("description").asText();
            String pageUrl = json.get("content_urls").get("desktop").get("page").asText();

            ArrayList<String> wikiData = new ArrayList<>();
            wikiData.add(description);
            wikiData.add(pageUrl);

            return wikiData;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public WeatherData getWeather(String area) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode areas = null;
            areas = objectMapper.readTree(new URL(String.format(CONVERTER_BASE_URL, area, API_KEY)));

            String lat= areas.get(0).get("lat").toString();
            String longi = areas.get(0).get("lon").toString();

            JsonNode weatherData;
            weatherData = objectMapper.readTree(new URL(String.format(WEATHER_BASE_URL, lat, longi, API_KEY)));

            WeatherData wData = new WeatherData(
                    weatherData.get("name").asText(),
                    weatherData.get("weather").get(0).get("main").asText(),
                    weatherData.get("weather").get(0).get("description").asText(),
                    weatherData.get("main").get("temp").asText()
            );
            return wData;

        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
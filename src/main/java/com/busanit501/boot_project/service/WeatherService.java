package com.busanit501.boot_project.service;

import com.busanit501.boot_project.dto.WeatherDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    private final String serviceKey = "1mk1O6r04EO4B2%2B%2Fdy1VA%2FvFrKRorSF8kxV0u4us85ne59tI38wBt9KApDBWC3bC%2FCpCMpw5XQ3IFfcSgUVymQ%3D%3D";

    public WeatherDTO getWeatherData() {
        RestTemplate restTemplate = new RestTemplate();

        String baseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        URI uri = UriComponentsBuilder
                .fromUriString("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst")
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", "1")
                .queryParam("numOfRows", "100")
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", "0500")
                .queryParam("nx", "98")
                .queryParam("ny", "76")
                .build(true)
                .toUri();

        String jsonString = restTemplate.getForObject(uri, String.class);

        WeatherDTO weather = new WeatherDTO();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonString);
            JsonNode items = root.path("response").path("body").path("items").path("item");

            for (JsonNode item : items) {
                String category = item.path("category").asText();
                String value = item.path("fcstValue").asText();

                switch(category) {
                    case "TMP":
                        weather.setTemperature(value);
                        break;
                    case "SKY":
                        weather.setSky(value);
                        break;
                    case "POP":
                        weather.setPrecipitationProbability(value);
                        break;
                    case "REH":
                        weather.setHumidity(value);
                        break;
                    case "PCP":
                        weather.setPrecipitationAmount(value);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weather;
    }
}

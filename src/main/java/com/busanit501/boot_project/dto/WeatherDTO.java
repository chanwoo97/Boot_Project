package com.busanit501.boot_project.dto;

public class WeatherDTO {
    private String temperature;
    private String sky;
    private String precipitationProbability;
    private String humidity;
    private String precipitationAmount;

    // 하늘 상태 설명 추가 (맑음, 구름 많음, 흐림 등)
    private String skyDescription;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;

        // sky 숫자에 따라 설명 자동 지정
        switch (sky) {
            case "1":
                this.skyDescription = "맑음";
                break;
            case "3":
                this.skyDescription = "구름 많음";
                break;
            case "4":
                this.skyDescription = "흐림";
                break;
            default:
                this.skyDescription = "정보 없음";
        }
    }

    public String getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(String precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPrecipitationAmount() {
        return precipitationAmount;
    }

    public void setPrecipitationAmount(String precipitationAmount) {
        this.precipitationAmount = precipitationAmount;
    }

    public String getSkyDescription() {
        return skyDescription;
    }

    public void setSkyDescription(String skyDescription) {
        this.skyDescription = skyDescription;
    }
}

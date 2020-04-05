package com.example.autismdiaryapp.ui.Games.Game4Scenarios;

public class Scenario {

    private String scenario;
    private String response1;
    private String response2;
    private String response3;
    private String response4;
    private String bestResponse;
    private String worstReponse;


    public Scenario() {
    }

    public Scenario(String scenario, String response1, String response2, String response3, String response4, String bestResponse, String worstResponse) {
        this.scenario = scenario;
        this.response1 = response1;
        this.response2 = response2;
        this.response3 = response3;
        this.response4 = response4;
        this.bestResponse = bestResponse;
        this.worstReponse = worstResponse;

    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getResponse1() {
        return response1;
    }

    public void setResponse1(String response1) {
        this.response1 = response1;
    }

    public String getResponse2() {
        return response2;
    }

    public void setResponse2(String response2) {
        this.response2 = response2;
    }

    public String getResponse3() {
        return response3;
    }

    public void setResponse3(String response3) {
        this.response3 = response3;
    }

    public String getResponse4() {
        return response4;
    }

    public void setResponse4(String response4) {
        this.response4 = response4;
    }

    public String getBestResponse() {return bestResponse;}

    public void setBestResponse(String bestResponse) { this.bestResponse = bestResponse;}

    public String getWorstReponse() { return worstReponse; }

    public void setWorstReponse(String worstReponse) { this.worstReponse = worstReponse;}
}



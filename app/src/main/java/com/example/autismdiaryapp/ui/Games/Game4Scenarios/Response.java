package com.example.autismdiaryapp.ui.Games.Game4Scenarios;

public class Response {

    private String firstResponse;
    private String secondResponse;
    private String thirdResponse;
    private String fourthResponse;

    public Response(){}

    public Response(String firstResponse, String secondResponse, String thirdResponse, String fourthResponse) {
        this.firstResponse = firstResponse;
        this.secondResponse = secondResponse;
        this.thirdResponse = thirdResponse;
        this.fourthResponse = fourthResponse;
    }

    public String getFirstResponse() {
        return firstResponse;
    }

    public void setFirstResponse(String firstResponse) {
        this.firstResponse = firstResponse;
    }

    public String getSecondResponse() {
        return secondResponse;
    }

    public void setSecondResponse(String secondResponse) {
        this.secondResponse = secondResponse;
    }

    public String getThirdResponse() {
        return thirdResponse;
    }

    public void setThirdResponse(String thirdResponse) {
        this.thirdResponse = thirdResponse;
    }

    public String getFourthResponse() {
        return fourthResponse;
    }

    public void setFourthResponse(String fourthResponse) {
        this.fourthResponse = fourthResponse;
    }
}

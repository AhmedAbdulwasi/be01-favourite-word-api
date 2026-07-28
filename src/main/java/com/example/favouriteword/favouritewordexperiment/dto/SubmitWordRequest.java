package com.example.favouriteword.favouritewordexperiment.dto;

public class SubmitWordRequest {
    private String word;
    private String clientToken;

    public SubmitWordRequest() {
    }

    public SubmitWordRequest(String word, String clientToken) {
        this.word = word;
        this.clientToken = clientToken;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }
}

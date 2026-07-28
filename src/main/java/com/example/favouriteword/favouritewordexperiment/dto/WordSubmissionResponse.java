package com.example.favouriteword.favouritewordexperiment.dto;

public class WordSubmissionResponse {
    private String word;
    private String sampleDefinition;
    private boolean hasVoted;

    public WordSubmissionResponse() {
    }

    public WordSubmissionResponse(String word, String sampleDefinition, boolean hasVoted) {
        this.word = word;
        this.sampleDefinition = sampleDefinition;
        this.hasVoted = hasVoted;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSampleDefinition() {
        return sampleDefinition;
    }

    public void setSampleDefinition(String sampleDefinition) {
        this.sampleDefinition = sampleDefinition;
    }

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}

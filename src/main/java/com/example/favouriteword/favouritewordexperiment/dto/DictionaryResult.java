package com.example.favouriteword.favouritewordexperiment.dto;

public class DictionaryResult {
    private boolean valid;
    private String word;
    private String sampleDefinition;
    private DictionaryErrorType errorType;

    public DictionaryResult() {

    }

    public DictionaryResult(boolean valid,
                            String word,
                            String sampleDefinition,
                            DictionaryErrorType errorType) {
        this.valid = valid;
        this.word = word;
        this.sampleDefinition = sampleDefinition;
        this.errorType = errorType;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
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

    public DictionaryErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(DictionaryErrorType errorType) {
        this.errorType = errorType;
    }
}

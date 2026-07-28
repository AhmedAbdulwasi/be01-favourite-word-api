package com.example.favouriteword.favouritewordexperiment.service;

import com.example.favouriteword.favouritewordexperiment.dto.DictionaryResult;
import com.example.favouriteword.favouritewordexperiment.dto.DictionaryErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class DictionaryService {
    private final RestTemplate restTemplate;
    private static final String DICTIONARY_API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    public DictionaryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DictionaryResult validateWord(String word) {
        String url = DICTIONARY_API_URL + word;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("API response: " + url);
                String sampleDefinition = "Sample definition for " + word.toLowerCase();
                return new DictionaryResult(true, word.toLowerCase(), sampleDefinition, DictionaryErrorType.NONE);
            }

            return new DictionaryResult(false, null, null, DictionaryErrorType.API_FAILURE);
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return new DictionaryResult(false, null, null, DictionaryErrorType.INVALID_WORD);
            }

            System.out.println("Dictionary API failure: " + e.getStatusCode());
            return new DictionaryResult(false, null, null, DictionaryErrorType.API_FAILURE);
        } catch (RestClientException e) {
            System.out.println("Dictionary API failure: " + e.getMessage());
            return new DictionaryResult(false, null, null, DictionaryErrorType.API_FAILURE);
        }
    }
}

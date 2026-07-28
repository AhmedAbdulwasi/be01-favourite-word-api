package com.example.favouriteword.favouritewordexperiment.dto;

import java.util.List;

public class WordStatsResponse {
    private final long totalVotes;
    private final List<WordCount> words;

    public WordStatsResponse(long totalVotes, List<WordCount> words) {
        this.totalVotes = totalVotes;
        this.words = words;
    }

    public long getTotalVotes() {
        return totalVotes;
    }

    public List<WordCount> getWords() {
        return words;
    }

    public static class WordCount {
        private final String word;
        private final long count;

        public WordCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public long getCount() {
            return count;
        }
    }
}

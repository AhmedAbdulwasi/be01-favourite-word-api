package com.example.favouriteword.favouritewordexperiment.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "word_entries")
public class WordEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String word;

    @Column
    private String sampleDefinition;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected WordEntry() {
    }

    public WordEntry(String word, String sampleDefinition, LocalDateTime createdAt) {
        this.word = word;
        this.sampleDefinition = sampleDefinition;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

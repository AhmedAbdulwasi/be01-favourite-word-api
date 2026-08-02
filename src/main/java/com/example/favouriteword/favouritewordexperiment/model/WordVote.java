package com.example.favouriteword.favouritewordexperiment.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "word_vote")
public class WordVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "word_entry_id", nullable = false)
    private WordEntry wordEntry;

    @Column(nullable = false, unique = true)
    private String clientToken;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected WordVote() {
    }

    public WordVote(WordEntry wordEntry, String clientToken, LocalDateTime createdAt) {
        this.wordEntry = wordEntry;
        this.clientToken = clientToken;
        this.word = wordEntry != null ? wordEntry.getWord() : null;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public WordEntry getWordEntry() {
        return wordEntry;
    }

    public void setWordEntry(WordEntry wordEntry) {
        this.wordEntry = wordEntry;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

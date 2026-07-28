package com.example.favouriteword.favouritewordexperiment.repository;

import com.example.favouriteword.favouritewordexperiment.model.WordEntry;
import com.example.favouriteword.favouritewordexperiment.model.WordVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordVoteRepository extends JpaRepository<WordVote, Long> {
    boolean existsByClientToken(String clientToken);
    long countByWordEntry(WordEntry wordEntry);
}
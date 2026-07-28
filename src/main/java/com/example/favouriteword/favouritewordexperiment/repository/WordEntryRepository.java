package com.example.favouriteword.favouritewordexperiment.repository;

import com.example.favouriteword.favouritewordexperiment.model.WordEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WordEntryRepository extends JpaRepository<WordEntry, Long> {
    Optional<WordEntry> findByWord(String word);
}

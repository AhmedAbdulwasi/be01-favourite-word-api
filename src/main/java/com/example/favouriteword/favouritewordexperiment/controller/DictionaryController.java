package com.example.favouriteword.favouritewordexperiment.controller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.favouriteword.favouritewordexperiment.dto.DictionaryErrorType;
import com.example.favouriteword.favouritewordexperiment.dto.DictionaryResult;
import com.example.favouriteword.favouritewordexperiment.dto.SubmitWordRequest;
import com.example.favouriteword.favouritewordexperiment.dto.WordSubmissionResponse;
import com.example.favouriteword.favouritewordexperiment.model.WordEntry;
import com.example.favouriteword.favouritewordexperiment.model.WordVote;
import com.example.favouriteword.favouritewordexperiment.repository.WordEntryRepository;
import com.example.favouriteword.favouritewordexperiment.repository.WordVoteRepository;
import com.example.favouriteword.favouritewordexperiment.service.DictionaryService;
import com.example.favouriteword.favouritewordexperiment.dto.WordStatsResponse;

@RestController
public class DictionaryController {
    private final DictionaryService dictionaryService;
    private final WordEntryRepository wordEntryRepository;
    private final WordVoteRepository wordVoteRepository;

    public DictionaryController(DictionaryService dictionaryService,
                                WordEntryRepository wordEntryRepository,
                                WordVoteRepository wordVoteRepository) {
        this.dictionaryService = dictionaryService;
        this.wordEntryRepository = wordEntryRepository;
        this.wordVoteRepository = wordVoteRepository;
    }

    @GetMapping("/api/words/check")
    public DictionaryResult checkWord(@RequestParam("word") String word) {
        return dictionaryService.validateWord(word);
    }

    @GetMapping("/api/words/stats")
    public WordStatsResponse getWordStats() {
        long totalVotes = wordVoteRepository.count();

        List<WordVote> votes = wordVoteRepository.findAll();

        Map<String, Long> counts = votes.stream()
                .collect(Collectors.groupingBy(vote -> vote.getWord() != null ? vote.getWord() : vote.getWordEntry().getWord(), Collectors.counting()));

        List<WordStatsResponse.WordCount> words = counts.entrySet().stream()
                .map(entry -> new WordStatsResponse.WordCount(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingLong(WordStatsResponse.WordCount::getCount).reversed())
                .collect(Collectors.toList());

        return new WordStatsResponse(totalVotes, words);
    }

    @PostMapping("/api/words/submit")
    public ResponseEntity<?> submitWord(@RequestBody SubmitWordRequest request) {
        String theWord = request.getWord().trim().toLowerCase();
        String clientToken = request.getClientToken().trim();

        if (wordVoteRepository.existsByClientToken(clientToken)) {
            DictionaryResult errorResult = new DictionaryResult(false, null, null, DictionaryErrorType.ALREADY_VOTED);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResult);
        }

        DictionaryResult dictionaryResult = dictionaryService.validateWord(theWord);
        if (!dictionaryResult.isValid()) {
            if (dictionaryResult.getErrorType() == DictionaryErrorType.INVALID_WORD) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dictionaryResult);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dictionaryResult);
        }

        WordEntry wordEntry = wordEntryRepository.findByWord(theWord).orElse(null);
        if (wordEntry == null) {
            wordEntry = wordEntryRepository.save(new WordEntry(theWord, dictionaryResult.getSampleDefinition(), LocalDateTime.now()));
        }

        wordVoteRepository.save(new WordVote(wordEntry, clientToken, LocalDateTime.now()));

        WordSubmissionResponse response = new WordSubmissionResponse(
                dictionaryResult.getWord(),
                dictionaryResult.getSampleDefinition(),
                true);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

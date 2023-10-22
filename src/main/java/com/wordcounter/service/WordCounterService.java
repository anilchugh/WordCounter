package com.wordcounter.service;

import com.wordcounter.model.WordCounterResponse;

import java.util.List;

public interface WordCounterService {

    /**
     * Count words. Matching translated words will also increment count
     *
     * @param words String instance
     * @return WordCounterResponse containing map of word and corresponding counts
     */
    WordCounterResponse countWords(List<String> words);

    void setWordTranslationService(WordTranslationService wordTranslationService);


}

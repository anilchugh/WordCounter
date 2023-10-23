package com.wordcounter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class WordTranslationClient {

    @Autowired
    WordTranslationService wordTranslationService;

    /**
     * Translate word
     *
     * @param word
     * @return English translation of word
     */
    @Cacheable("translations")
    public String translate(String word) {
        return wordTranslationService.translate(word);
    }

}

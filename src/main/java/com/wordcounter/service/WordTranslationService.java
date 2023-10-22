package com.wordcounter.service;

public interface WordTranslationService {

    /**
     * Translate word
     *
     * @param word
     * @return English translation of word
     */
    String translate(String word);

}

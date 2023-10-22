package com.wordcounter.service;

import org.springframework.stereotype.Service;

/**
 * Default Word Translation Service returns original word
 */
@Service
public class DefaultWordTranslationServiceImpl implements WordTranslationService {

    public String translate(String word) {
        return word;
    }
}

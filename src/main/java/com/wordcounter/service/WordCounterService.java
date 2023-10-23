package com.wordcounter.service;

import com.wordcounter.model.WordCounterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordCounterService {

    @Autowired
    private WordTranslationClient wordTranslationClient;

    /**
     * Count words. Matching translated words will also increment count
     *
     * @param words String instance
     * @return WordCounterResponse containing map of word and corresponding counts
     */
    public WordCounterResponse countWords(List<String> words) {
        WordCounterResponse wordCounterResponse = new WordCounterResponse();
        for (String word : words) {
            word = word.toLowerCase();
            String translation = wordTranslationClient.translate(word);
            Long count = wordCounterResponse.getWordCountMap().get(translation);
            if (count != null) {
                count++;
                wordCounterResponse.getWordCountMap().put(translation, count);
            } else {
                wordCounterResponse.getWordCountMap().put(translation, 1l);
            }
        }
        return wordCounterResponse;
    }

}

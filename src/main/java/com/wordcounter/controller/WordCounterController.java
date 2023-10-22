package com.wordcounter.controller;

import com.wordcounter.exception.ValidationException;
import com.wordcounter.model.WordCounterResponse;
import com.wordcounter.service.WordCounterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/wordcounter")
public class WordCounterController {

    private static final String BLANK_SPACE_REGEX = "\\s+";

    @Autowired
    private WordCounterService wordCounterService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WordCounterResponse> getWordCount(@RequestParam(name = "words") String words) throws ValidationException {
        WordCounterResponse wordCounterResponse = null;
        if (!StringUtils.isEmpty(words)) {
            List<String> wordList = Arrays.asList(words.split(BLANK_SPACE_REGEX));
            if (!wordList.isEmpty()) {
                for (String word : wordList) {
                    validateWord(word);
                }
                wordCounterResponse = wordCounterService.countWords(wordList);
            }
        }
        return new ResponseEntity<WordCounterResponse>(wordCounterResponse, HttpStatus.OK);

    }

    private boolean validateWord(String word) throws ValidationException {
        if (!StringUtils.isAlphanumeric(word)) {
            throw new ValidationException(word);
        }
        return true;
    }

}

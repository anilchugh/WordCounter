package com.wordounter.service;

import com.wordcounter.model.WordCounterResponse;
import com.wordcounter.service.WordCounterService;
import com.wordcounter.service.WordCounterServiceImpl;
import com.wordcounter.service.WordTranslationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class WordCounterServiceUnitTest {

    @Autowired
    private WordCounterService wordCounterService;

    @MockBean
    private WordTranslationService mockWordTranslationService;

    @TestConfiguration
    static class WordCounterServiceTestContextConfiguration {
        @Bean
        public WordCounterService wordCounterService() {
            return new WordCounterServiceImpl();
        }
    }

    @Before
    public void setup() {
        Mockito.when(mockWordTranslationService.translate(Mockito.anyString())).thenAnswer(
                new Answer<String>() {
                    @Override
                    public String answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return (String) args[0];
                    }
                });
        wordCounterService.setWordTranslationService(mockWordTranslationService);
    }

    @Test
    public void countWordsTest() {
        WordCounterResponse wordCounterResponse = wordCounterService.countWords(Arrays.asList("The", "fact", "of", "the", "matter"));

        assertEquals("Word count does not match", 2, wordCounterResponse.getWordCountMap().get("the").longValue());
        assertEquals("Word count does not match", 1, wordCounterResponse.getWordCountMap().get("fact").longValue());
        assertEquals("Word count does not match", 1, wordCounterResponse.getWordCountMap().get("of").longValue());
        assertEquals("Word count does not match", 1, wordCounterResponse.getWordCountMap().get("matter").longValue());
    }
}

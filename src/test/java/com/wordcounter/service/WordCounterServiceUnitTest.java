package com.wordcounter.service;

import com.wordcounter.model.WordCounterResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class WordCounterServiceUnitTest {

    @InjectMocks
    private WordCounterService wordCounterService;

    @Mock
    private WordTranslationClient wordTranslationClient;

    @Before
    public void setup() {
        Mockito.when(wordTranslationClient.translate(Mockito.anyString())).thenAnswer(
                new Answer<String>() {
                    @Override
                    public String answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return (String) args[0];
                    }
                });
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

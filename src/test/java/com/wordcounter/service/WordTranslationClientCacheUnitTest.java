package com.wordcounter.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class WordTranslationClientCacheUnitTest {

    @Autowired
    private WordTranslationClient wordTranslationClient;

    @MockBean
    private WordTranslationService wordTranslationService;

    @Before
    public void setup() {
        Mockito.when(wordTranslationService.translate(Mockito.anyString())).thenAnswer(
                new Answer<String>() {
                    @Override
                    public String answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return (String) args[0];
                    }
                });
    }

    @Test
    public void countWordsTestWithCachingEnabled() {
        wordTranslationClient.translate("the");
        Mockito.verify(wordTranslationService, Mockito.times(1)).translate("the");

        Mockito.reset(wordTranslationService);

        wordTranslationClient.translate("the");
        Mockito.verify(wordTranslationService, Mockito.times(0)).translate("the");
    }
}

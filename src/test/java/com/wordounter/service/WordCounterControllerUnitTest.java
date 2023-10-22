package com.wordounter.service;

import com.wordcounter.model.WordCounterResponse;
import com.wordcounter.service.WordCounterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WordCounterControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WordCounterService wordCounterService;

    private WordCounterResponse prepareWordCounterResponse(String word, long count) {
        WordCounterResponse wordCounterResponse = new WordCounterResponse();
        wordCounterResponse.getWordCountMap().put(word, count);
        return wordCounterResponse;
    }

    @Test
    public void countWordsTest() throws Exception {
        when(wordCounterService.countWords(Mockito.anyList())).thenReturn(prepareWordCounterResponse("the", 1l));
        mvc.perform(get("/wordcounter?words=the").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"the\":1}")));
    }
}

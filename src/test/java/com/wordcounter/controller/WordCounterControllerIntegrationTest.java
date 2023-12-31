package com.wordcounter.controller;

import com.wordcounter.WordCounterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = WordCounterApplication.class)
public class WordCounterControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void countWordsTest() throws Exception {
        mvc.perform(get("/wordcounter?words=the").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"the\":1}")));
    }

    @Test
    public void countWordsWithNonAlphanumericTest() throws Exception {
        mvc.perform(get("/wordcounter?words=the@").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.BAD_REQUEST.value())).andExpect(content().string(
                is("{\"status\":400,\"message\":\"Words must be alphanumeric: the@\"}")));
    }
}

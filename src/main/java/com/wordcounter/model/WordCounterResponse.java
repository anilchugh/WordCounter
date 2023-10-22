package com.wordcounter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class WordCounterResponse {

    @JsonProperty("wordCountMap")
    private Map<String, Long> wordCountMap = new HashMap<>();

    public Map<String, Long> getWordCountMap() {
        return wordCountMap;
    }

    public void setWordCountMap(Map<String, Long> wordCountMap) {
        this.wordCountMap = wordCountMap;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        WordCounterResponse that = (WordCounterResponse) object;
        return java.util.Objects.equals(wordCountMap, that.wordCountMap);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), wordCountMap);
    }
}

package com.example.firstapp;

import java.util.List;

public class RestStarWarsResponse {

    private Integer count;
    private String next;
    private List<People> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<People> getResults() {
        return results;
    }


}

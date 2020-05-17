package com.example.firstapp.presentation.model;

import com.example.firstapp.presentation.model.Character;

import java.util.List;

public class RestRickAndMortyResponse {


    private Info info;
    private List<Character> results;

    public Info getInfo() {
        return info;
    }

    public List<Character> getResults() {
        return results;
    }
}

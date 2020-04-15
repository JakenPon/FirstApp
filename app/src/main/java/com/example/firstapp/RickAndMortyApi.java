package com.example.firstapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RickAndMortyApi {

    @GET("/api/character")
    Call<RestRickAndMortyResponse> getRickAndMortyResponse();
}
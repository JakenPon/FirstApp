package com.example.firstapp.data;

import com.example.firstapp.presentation.model.RestRickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RickAndMortyApi {

    @GET("/api/character")
    Call<RestRickAndMortyResponse> getRickAndMortyResponse();
}
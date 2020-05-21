package com.example.firstapp.data;

import com.example.firstapp.presentation.model.RestRickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RickAndMortyApi {

    // https://rickandmortyapi.com/api/character/?page=20
    @GET("/api/character/")
    Call<RestRickAndMortyResponse> getRickAndMortyResponse(@Query("page") int ID);




}
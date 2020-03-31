package com.example.firstapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StarWarsApi {

    @GET("/api/people/")
    Call<RestStarWarsResponse> getPokemonResponse();
}

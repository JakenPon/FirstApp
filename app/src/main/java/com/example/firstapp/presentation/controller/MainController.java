package com.example.firstapp.presentation.controller;

import android.content.SharedPreferences;

import com.example.firstapp.Constants;
import com.example.firstapp.Singletons;
import com.example.firstapp.presentation.model.Character;
import com.example.firstapp.presentation.model.RestRickAndMortyResponse;
import com.example.firstapp.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;


    public MainController(MainActivity mainActivity,Gson gson, SharedPreferences sharedPreferences){
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void OnStart(){

        List<Character> characterList = getDataFromCache();


        if(characterList != null){
            view.showList(characterList);
        } else{
            makeApiCall();
        }
    }

    private List<Character> getDataFromCache() {
        String jsonCharacter = sharedPreferences.getString(Constants.KEY_CHARACTER, null);

        if(jsonCharacter == null){
            return null;
        }else {
            Type listType = new TypeToken<List<Character>>() {
            }.getType();
            return gson.fromJson(jsonCharacter, listType);
        }
    }

    private void makeApiCall(){

        Call<RestRickAndMortyResponse> call = Singletons.getRmapi().getRickAndMortyResponse();
        call.enqueue(new Callback<RestRickAndMortyResponse>() {
            @Override
            public void onResponse(Call<RestRickAndMortyResponse> call, Response<RestRickAndMortyResponse> response) {
                if(response.isSuccessful() &&  response.body() != null){
                    System.out.println(response.body());
                    List<Character> characterList = response.body().getResults();
                    for (int i = 0; i < characterList.size(); i++) {
                        System.out.println(characterList.get(i).getOrigin().getName_planet());
                    }
                    saveList(characterList);
                    view.showList(characterList);
                }else {
                    view.showError();
                    System.out.println(response.code());
                }
            }

            @Override
            public void onFailure(Call<RestRickAndMortyResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(List<Character> characterList) {
        String jsonString = gson.toJson(characterList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_CHARACTER, jsonString)
                .apply();
    }



}

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private int i;
    private boolean v;
    private int pageSize;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;


    public MainController(MainActivity mainActivity,Gson gson, SharedPreferences sharedPreferences){
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void OnStart(){
        i = 0;
        v = true;
        pageSize = 1;
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

    private void makeApiCall() {
        for(int i = 0; i < 30; i ++) {
        Call<RestRickAndMortyResponse> call = Singletons.getRmapi().getRickAndMortyResponse(i);
        call.enqueue(new Callback<RestRickAndMortyResponse>() {
            @Override
            public void onResponse(Call<RestRickAndMortyResponse> call, Response<RestRickAndMortyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Character> characterList = response.body().getResults();
                    System.out.println(Arrays.toString(characterList.toArray()));
                    addCharacterToList(characterList);
                    view.showList(getCharactersFromCache());
                } else {
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

    }


    private void addCharacterToList(List<Character> characterList) {
        List<Character> characterAlreadyInList = getCharactersFromCache();
        List<Character> allCharacters = new ArrayList<>();
        for (int i = 0; i < characterList.size(); i++) allCharacters.add(characterList.get(i));
        for (int i = 0; i < characterAlreadyInList.size(); i++) allCharacters.add(characterAlreadyInList.get(i));
        String jsonString = gson.toJson(allCharacters);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_CHARACTER, jsonString)
                .apply();
    }

    private List<Character> getCharactersFromCache() {
        Type listType = new TypeToken<List<Character>>() {}.getType();
        List<Character> characterList = gson.fromJson(sharedPreferences.getString(Constants.KEY_CHARACTER, ""), listType);
        if (characterList == null) return new ArrayList<>();
        else return characterList;
    }

    public void onItemClick(Character character){
        view.navigateDetails(character);
    }
}

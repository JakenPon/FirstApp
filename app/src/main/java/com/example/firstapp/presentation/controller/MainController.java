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

    private int i;
    private boolean v;
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
        List<Character> characterList = getDataFromCache();


/*        if(characterList != null){
            view.showList(characterList);
        } else{ */
            makeApiCall();
    //    }
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

        for(int i=0; i<3;i ++) {
            System.out.println("info : ");
        Call<RestRickAndMortyResponse> call = Singletons.getRmapi().getRickAndMortyResponse(getI());
        call.enqueue(new Callback<RestRickAndMortyResponse>() {
            @Override
            public void onResponse(Call<RestRickAndMortyResponse> call, Response<RestRickAndMortyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                  //  System.out.println(response.body().getInfo().getpagesint());
                    List<Character> characterList = response.body().getResults();
                    saveList(characterList);
                    view.showList(characterList);
                    incrI();
                    System.out.println("info : "+getI());
                    if (!((response.body().getInfo().getpagesint()) == getI())){
                        setV(false);
                    }
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
        List<Character> characterList = getDataFromCache();
        view.showList(characterList);
    }

    private void saveList(List<Character> characterList) {
        String jsonString = gson.toJson(characterList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_CHARACTER, jsonString)
                .apply();
    }

    public void onItemClick(Character character){
        view.navigateDetails(character);
    }


    public int getI() {
        return i;
    }

    public boolean isV() {
        return v;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setV(boolean v) {
        this.v = v;
    }

    public int incrI(){
        i++;
        return  i;
    }



}

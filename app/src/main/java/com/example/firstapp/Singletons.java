package com.example.firstapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.firstapp.data.RickAndMortyApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static RickAndMortyApi rmapiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
    return gsonInstance;
    }

    public static RickAndMortyApi getRmapi(){
        if(rmapiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            rmapiInstance = retrofit.create(RickAndMortyApi.class);
        }
        return rmapiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences("app", context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}

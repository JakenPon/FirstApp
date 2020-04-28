package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ListAdapter.OnItemClickListener {

    private static final String BASE_URL = "https://rickandmortyapi.com/";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Character> characterList = getDataFromCache();

        if(characterList != null){
            showList(characterList);
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

    private void showList(List<Character> CharacterList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(CharacterList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(MainActivity.this);

    }


    private void makeApiCall(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            RickAndMortyApi rickAndMortyApi = retrofit.create(RickAndMortyApi.class);

            Call<RestRickAndMortyResponse> call = rickAndMortyApi.getRickAndMortyResponse();
            call.enqueue(new Callback<RestRickAndMortyResponse>() {
                @Override
                public void onResponse(Call<RestRickAndMortyResponse> call, Response<RestRickAndMortyResponse> response) {

                    if(response.isSuccessful() &&  response.body() != null){
                        System.out.println(response.body());
                        List<Character> characterList = response.body().getResults();
                        saveList(characterList);
                        showList(characterList);

                    }else {
                        showError();
                    }
                }

                @Override
                public void onFailure(Call<RestRickAndMortyResponse> call, Throwable t) {
                    showError();
                }
            });
        }

    private void saveList(List<Character> characterList) {
        String jsonString = gson.toJson(characterList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_CHARACTER, jsonString)
                .apply();
        Toast.makeText(getApplicationContext(), "SAVE LIST", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent =  new Intent(this, CharacterDetail.class);
       // Character clickedItem = mAdapter.get(position);
        Toast.makeText(getApplicationContext(), "click ok", Toast.LENGTH_SHORT).show();
    }
}

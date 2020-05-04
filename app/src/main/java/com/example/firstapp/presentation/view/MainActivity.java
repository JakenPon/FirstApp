package com.example.firstapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.firstapp.R;
import com.example.firstapp.Singletons;
import com.example.firstapp.presentation.controller.MainController;
import com.example.firstapp.presentation.model.Character;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;


    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext()));
        controller.OnStart();
    }



    public void showList(List<Character> CharacterList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(CharacterList);
        recyclerView.setAdapter(mAdapter);


    }



    public void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }


}

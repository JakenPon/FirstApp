package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterDetail extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        System.out.println("here");
        Character character = (Character) getIntent().getSerializableExtra("EXTRA_CHARACTER");
        System.out.println(character.getName());
        tv = findViewById(R.id.detail_name);
        tv.setText(character.getName());
    }
}

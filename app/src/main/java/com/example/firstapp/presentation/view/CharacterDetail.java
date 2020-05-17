package com.example.firstapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.R;
import com.example.firstapp.Singletons;
import com.example.firstapp.presentation.model.Character;
import com.squareup.picasso.Picasso;

public class CharacterDetail extends AppCompatActivity {
    private TextView tv;
    private ImageView iv;
    private TextView status;
    private TextView species;
    private TextView gender;
    private TextView origin;
    private TextView last_location;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        Intent intent = getIntent();
        String characterJson = intent.getStringExtra("character");
        Character character = Singletons.getGson().fromJson(characterJson, Character.class);
        showDetail(character);
    }

    private void showDetail(Character character) {
        tv = findViewById(R.id.Name);
        tv.setText( character.getName());
        iv = findViewById(R.id.Image_character);
        Picasso.get()
                .load(character.getImage())
                .into(iv);
        status =  findViewById(R.id.Status);
        status.setText("Status : \n"+character.getStatus());
        species = findViewById(R.id.Species);
        species.setText("Species : \n"+character.getSpecies());
        gender =  findViewById(R.id.Gender);
        gender.setText("Gender : \n"+character.getGender());
        origin = findViewById(R.id.Origin);
        origin.setText("Origin : \n"+character.getOrigin().getName());
        last_location = findViewById(R.id.Location);
        last_location.setText("Last location : \n"+character.getLocation().getName());

    }

}

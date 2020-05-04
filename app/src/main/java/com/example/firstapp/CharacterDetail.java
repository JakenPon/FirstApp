package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.presentation.model.Character;
import com.squareup.picasso.Picasso;

public class CharacterDetail extends AppCompatActivity {
    private TextView tv;
    private ImageView iv;
    private TextView status;
    private TextView species;
    private TextView gender;
    private TextView origin;
    private TextView last_loc;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        Character character = (Character) getIntent().getSerializableExtra("EXTRA_CHARACTER");
        tv = findViewById(R.id.Name);
        tv.setText( character.getName());
        iv = findViewById(R.id.Image_character);
        Picasso.get()
                .load(character.getImage())
                .into(iv);
        status =  findViewById(R.id.Status);
        status.setText("Status : "+character.getStatus());
        species = findViewById(R.id.Species);
        species.setText("Species : "+character.getSpecies());
        gender =  findViewById(R.id.Gender);
        gender.setText("Gender : "+character.getGender());


    }
}

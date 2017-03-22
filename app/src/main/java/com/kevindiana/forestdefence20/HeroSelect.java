package com.kevindiana.forestdefence20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HeroSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_select);

        // this is getting the information to choose the map and diffuculty
        String map_difficulty = getIntent().getExtras().getString("Chooser");
    }
}

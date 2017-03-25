package com.kevindiana.forestdefence20;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HeroSelect extends AppCompatActivity {
    private String map_difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_select);

        // this is getting the information to choose the map and diffuculty
        map_difficulty = getIntent().getExtras().getString("Chooser");

        // get reference to gif image in layour
        ImageView heroView = (ImageView) findViewById(R.id.hero);
        // set animation gif as background resource for view
        heroView.setBackgroundResource(R.drawable.hero_select_screen_animation);
        // create an animation drawable object based on this background
        AnimationDrawable hero_selectAnimation = (AnimationDrawable) heroView.getBackground();
        // start animation
        hero_selectAnimation.start();

        // make map difficulty into int add 100 for knight, 200 for archer or 300 for wizard;

    }

    public void ButtonListener(View view){

        String extrainfo = map_difficulty;
        Intent intent = new Intent("com.kevindiana.forestdefence20.Game");

        switch(view.getId()){
            case R.id.knight:
                map_difficulty = Integer.toString((Integer.valueOf(map_difficulty) + 100));
                extrainfo = map_difficulty;
                break;
            case R.id.archer:
                map_difficulty = Integer.toString((Integer.valueOf(map_difficulty) + 200));
                extrainfo = map_difficulty;
                break;
            case R.id.wizard:
                map_difficulty = Integer.toString((Integer.valueOf(map_difficulty) + 300));
                extrainfo = map_difficulty;
                break;

        }
        intent.putExtra("Chooser", extrainfo);
        startActivity(intent);
    }
}

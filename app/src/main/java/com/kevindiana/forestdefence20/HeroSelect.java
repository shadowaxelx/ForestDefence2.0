package com.kevindiana.forestdefence20;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


/**
 class HeroSelect

 NAME

 class HeroSelect

 SYNOPSIS

 class HeroSelect


 DESCRIPTION

    An activity that lets the player choose which hero he or she wishes to use durring the
    infinite game mode

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 2:55pm 3/31/2017

 */
public class HeroSelect extends AppCompatActivity {
    private String map_difficulty;

    /**
     protected void onCreate(Bundle a_savedInstanceState)

     NAME

     onCreate - when the activity is launched everything within the function is called first

     SYNOPSIS

     protected void onCreate(Bundle a_savedInstanceState)

     a_savedInstanceState -> The saved state of the application being passed in as a bundle


     DESCRIPTION

     This function is called to start up the activity and to pass in extra information from
     previouse activity.  It allows for the animation of the background as well.

     RETURNS

     Nothing

     AUTHOR

     Kevin Diana

     DATE

     3:00pm 3/31/2017

     */
    @Override
    protected void onCreate(Bundle a_savedInstanceState) {
        super.onCreate(a_savedInstanceState);
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

    /**
     public void ButtonListener(View a_view){

     }

     NAME

     ButtonListener(View a_view)

     SYNOPSIS

     ButtonListener(View a_view)

     View a_view -> returns the information of the view that was clicked on


     DESCRIPTION

     Waits for player to click on a buton, finds the id of that button, then performs an action
     based on the button pressed which selects the hero of the players choice then continues to the
     Game activity

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     8:51pm 3/31/2017

     */
    public void ButtonListener(View a_view){

        String extrainfo = map_difficulty;
        Intent intent = new Intent("com.kevindiana.forestdefence20.Game");

        switch(a_view.getId()){
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

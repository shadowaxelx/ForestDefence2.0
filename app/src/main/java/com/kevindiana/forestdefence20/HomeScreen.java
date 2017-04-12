package com.kevindiana.forestdefence20;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import static com.kevindiana.forestdefence20.R.styleable.View;

/**
 class HomeScreen

 NAME

    HomeScreen

 SYNOPSIS

    class HomeScreen


 DESCRIPTION

    Activity that first opens up when the game is opened allowing the player to either start the game
        or see how to play the game with button clicks

 RETURNS

 Nothing

 AUTHOR

 Kevin Diana

 DATE

 1:00Am 2/17/2017

 */
public class HomeScreen extends AppCompatActivity {

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

     1:00Am 2/317/2017

     */
    @Override
    protected void onCreate(Bundle a_savedInstanceState) {
        super.onCreate(a_savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        // get reference to gif image in layour
        ImageView backgroundView = (ImageView) findViewById(R.id.background);
        // set animation gif as background resource for view
        backgroundView.setBackgroundResource(R.drawable.home_screen_animation);
        // create an animation drawable object based on this background
        AnimationDrawable endingAnimation = (AnimationDrawable) backgroundView.getBackground();
        // start animation
        endingAnimation.start();

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
     based on the button pressed which selects play or How2.
     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:20Am 2/17/2017

     */
    public void OnClickButtonListener(View a_view){
        switch(a_view.getId()){
            case R.id.imageButton:
                Intent intent = new Intent("com.kevindiana.forestdefence20.Game_Chooser");
                startActivity(intent);

                break;

            case R.id.imageButton2:
                Intent intent2 = new Intent("com.kevindiana.forestdefence20.How2");
                startActivity(intent2);
                break;
        }
    }

}

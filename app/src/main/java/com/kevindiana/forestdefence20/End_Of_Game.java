package com.kevindiana.forestdefence20;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import static com.kevindiana.forestdefence20.R.id.imageView;

/**
 class End_Of_Game

 NAME

 End_Of_Game

 SYNOPSIS

    End_Of_Game

 DESCRIPTION

    When the Player Wins or loses this activity will be shown

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 3:20pm 3/3/2017

 */
public class End_Of_Game extends AppCompatActivity {


    /**
     protected void onCreate(Bundle a_savedInstanceState)

     NAME

     onCreate - when the activity is launched everything within the function is called first

     SYNOPSIS

     protected void onCreate(Bundle a_savedInstanceState)

     a_savedInstanceState -> The saved state of the application being passed in as a bundle


     DESCRIPTION

     This function is called to start up the activity and to pass in extra information from
     previouse activity to know if the player has died or won

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     8:51pm 3/5/2017

     */
    @Override
    protected void onCreate(Bundle a_savedInstanceState) {
        super.onCreate(a_savedInstanceState);
        setContentView(R.layout.activity_end__of__game);
        // extra info being passed from other activity
        String passedLife = getIntent().getExtras().getString("Health");

        // get reference to gif image in layour
        ImageView endingView = (ImageView) findViewById(R.id.ending);
        // set animation gif as background resource for view
        endingView.setBackgroundResource(R.drawable.end_screen_animation);
        // create an animation drawable object based on this background
        AnimationDrawable endingAnimation = (AnimationDrawable) endingView.getBackground();
        // start animation
        endingAnimation.start();

        Score(Integer.parseInt(passedLife));

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
     based on the button pressed

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     8:51pm 3/5/2017

     */
    // The extra data from the game is the players health, -1 means they died
    // 1-20 is the health they had, make a 1-5 star system for it
    public void ButtonListener(View a_view){
        switch(a_view.getId()){
            case R.id.menu:
                Intent intent = new Intent(this, HomeScreen.class);
                startActivity(intent);

                break;
        }
    }

    /**
     public void Score(int a_health)

     }

     NAME

     Score(int a_health)

     SYNOPSIS

        Score(int a_health)

            int a_health -> The amount of health the player had when the activity was called

     DESCRIPTION

     If the player equal to or less than 0 hp then they died so show the dead screen.  If player
     had greater than 0 hp when the game ended the won so show the win screen.

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     8:51pm 3/5/2017

     */
    public void Score(int a_health){
        if(a_health == -1){
            ImageView dead =(ImageView)findViewById(R.id.end_dead_text);
            dead.setVisibility(View.VISIBLE);
        }
        // they did not die
        else{
            ImageView win =(ImageView)findViewById(R.id.five_star_win_text);
            win.setVisibility(View.VISIBLE);
        }
    }

}
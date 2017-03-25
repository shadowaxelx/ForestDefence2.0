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

public class End_Of_Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    // The extra data from the game is the players health, -1 means they died
    // 1-20 is the health they had, make a 1-5 star system for it
    public void ButtonListener(View view){
        switch(view.getId()){
            case R.id.menu:
                Intent intent = new Intent(this, HomeScreen.class);
                startActivity(intent);

                break;
        }
    }

    public void Score(int health){
        if(health == -1){
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
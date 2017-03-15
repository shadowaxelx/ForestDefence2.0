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

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void OnClickButtonListener(View view){
        switch(view.getId()){
            case R.id.imageButton:
                Intent intent = new Intent("com.kevindiana.forestdefence20.Game_Chooser");
                startActivity(intent);

                break;
        }
    }

}

package com.kevindiana.forestdefence20;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 class How2

 NAME

    How2

 SYNOPSIS

    class How2
        int counter -> keeps track of what page the player is on of the guide
        ImageView guideVIew -> the imageview which will hold the current background


 DESCRIPTION

    Activity that lets the player see how the game is played

 RETURNS

 Nothing

 AUTHOR

 Kevin Diana

 DATE

 1:00Am 3/25/2017

 */
public class How2 extends AppCompatActivity {

    private int counter = 0;
    ImageView guideView;

    /**
     protected void onCreate(Bundle a_savedInstanceState)

     NAME

     onCreate - when the activity is launched everything within the function is called first

     SYNOPSIS

     protected void onCreate(Bundle a_savedInstanceState)

     a_savedInstanceState -> The saved state of the application being passed in as a bundle


     DESCRIPTION

     This function is called to start up the activity and to set/ display the current background

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
        setContentView(R.layout.activity_how2);

        // get reference to gif image in layour
        guideView = (ImageView) findViewById(R.id.guide);
        // set animation gif as background resource for view
        guideView.setBackgroundResource(R.drawable.how2guide_08);



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
     based on button press which will scroll through the how2 guide
     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:20Am 2/17/2017

     */
    public void ButtonListener(View a_view){

        //RelativeLayout guide = new RelativeLayout(this);

        switch(a_view.getId()){

            case R.id.right_arrow:

                switch(counter){
                    case 0:
                        guideView.setBackgroundResource(R.drawable.hero_picker_guide_01);
                        counter++;
                        break;
                    case 1:
                        guideView.setBackgroundResource(R.drawable.hero_picker_guide_02);
                        counter++;
                        break;
                    case 2:
                        guideView.setBackgroundResource(R.drawable.how2guide_00);
                        counter++;
                        break;
                    case 3:
                        guideView.setBackgroundResource(R.drawable.how2guide_01);
                        counter++;
                        break;
                    case 4:
                        guideView.setBackgroundResource(R.drawable.how2guide_02);
                        counter++;
                        break;
                    case 5:
                        guideView.setBackgroundResource(R.drawable.how2guide_03);
                        counter++;
                        break;
                    case 6:
                        guideView.setBackgroundResource(R.drawable.how2guide_04);
                        counter++;
                        break;
                    case 7:
                        guideView.setBackgroundResource(R.drawable.how2guide_05);
                        counter++;
                        break;
                    case 8:
                        guideView.setBackgroundResource(R.drawable.how2guide_06);
                        counter++;
                        break;
                    case 9:
                        guideView.setBackgroundResource(R.drawable.how2guide_07);
                        counter++;
                        break;
                    case 10:
                        guideView.setBackgroundResource(R.drawable.how2guide_09);
                        counter++;
                        break;
                    case 11:
                        Intent intent = new Intent(this, HomeScreen.class);
                        startActivity(intent);
                        break;
                }

                //setContentView(guideView);

                break;
            case R.id.left_arrow:

                switch(counter){
                    case 0:
                        Intent intent = new Intent(this, HomeScreen.class);
                        startActivity(intent);
                        break;
                    case 1:
                        guideView.setBackgroundResource(R.drawable.how2guide_08);
                        counter--;
                        break;
                    case 2:
                        guideView.setBackgroundResource(R.drawable.hero_picker_guide_01);
                        counter--;
                        break;
                    case 3:
                        guideView.setBackgroundResource(R.drawable.hero_picker_guide_02);
                        counter--;
                        break;
                    case 4:
                        guideView.setBackgroundResource(R.drawable.how2guide_00);
                        counter--;
                        break;
                    case 5:
                        guideView.setBackgroundResource(R.drawable.how2guide_01);
                        counter--;
                        break;
                    case 6:
                        guideView.setBackgroundResource(R.drawable.how2guide_02);
                        counter--;
                        break;
                    case 7:
                        guideView.setBackgroundResource(R.drawable.how2guide_03);
                        counter--;
                        break;
                    case 8:
                        guideView.setBackgroundResource(R.drawable.how2guide_04);
                        counter--;
                        break;
                    case 9:
                        guideView.setBackgroundResource(R.drawable.how2guide_05);
                        counter--;
                        break;
                    case 10:
                        guideView.setBackgroundResource(R.drawable.how2guide_06);
                        counter--;
                        break;
                    case 11:
                        guideView.setBackgroundResource(R.drawable.how2guide_07);
                        counter--;
                        break;
                }

                break;
        }
    }

}

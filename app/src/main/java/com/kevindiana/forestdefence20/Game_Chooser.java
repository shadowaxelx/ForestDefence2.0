package com.kevindiana.forestdefence20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

/**
 class Game_Chooser

 NAME

    GameChooser

 SYNOPSIS

 DESCRIPTION

    An activity that allows the player to choose what map and what difficulter
    he or she wishes to play on and passes that information onto the Game class

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 4:24pm 3/10/2017

 */
public class Game_Chooser extends AppCompatActivity {

    /**
     protected void onCreate(Bundle a_savedInstanceState)

     NAME

     onCreate - when the activity is launched everything within the function is called first

     SYNOPSIS

     protected void onCreate(Bundle a_savedInstanceState)

     a_savedInstanceState -> The saved state of the application being passed in as a bundle


     DESCRIPTION

     This function is called to start up the activity and sets the layout to game_chooser.

     RETURNS

     Nothing

     AUTHOR

     Kevin Diana

     DATE

     4:24pm 3/10/2017

     */
    @Override
    protected void onCreate(Bundle a_savedInstanceState) {
        super.onCreate(a_savedInstanceState);
        setContentView(R.layout.activity_game__chooser);
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
    public void OnClickButtonListener(View a_view){

        Intent intent = new Intent("com.kevindiana.forestdefence20.Game");
        // the # in the tens place determins map
        // the # in the ones place determins the difficulty

        // preset to map 1 difuculty normal
        String extrainfo = "12";
        RadioButton easy, normal, hard, infinity;

        switch(a_view.getId()){
            case R.id.map1:

                easy = (RadioButton) findViewById(R.id.easy_map1);
                normal = (RadioButton) findViewById(R.id.normal_map1);
                hard = (RadioButton) findViewById(R.id.hard_map1);
                infinity = (RadioButton) findViewById(R.id.infinite_map1);
                if(easy.isChecked()){
                    extrainfo = "11";
                }
                else if(normal.isChecked()){
                    extrainfo ="12";
                }
                else if(hard.isChecked()){
                    extrainfo ="13";
                 }
                else if(infinity.isChecked()){
                    extrainfo = "14";

                    Intent intentIE = new Intent("com.kevindiana.forestdefence20.HeroSelect");
                    intentIE.putExtra("Chooser", extrainfo);
                    startActivity(intentIE);
                    break;
                }
                intent.putExtra("Chooser",extrainfo);
                startActivity(intent);

                break;
            case R.id.map2:

                easy = (RadioButton) findViewById(R.id.easy_map2);
                normal = (RadioButton) findViewById(R.id.normal_map2);
                hard = (RadioButton) findViewById(R.id.hard_map2);
                infinity = (RadioButton) findViewById(R.id.infinite_map2);
                if(easy.isChecked()){
                    extrainfo = "21";
                }
                else if(normal.isChecked()){
                    extrainfo ="22";
                }
                else if(hard.isChecked()){
                    extrainfo ="23";
                }
                else if(infinity.isChecked()){
                    extrainfo = "24";

                    Intent intentIE = new Intent("com.kevindiana.forestdefence20.HeroSelect");
                    intentIE.putExtra("Chooser", extrainfo);
                    startActivity(intentIE);
                    break;
                }
                intent.putExtra("Chooser",extrainfo);
                startActivity(intent);
                break;

            case R.id.map3:

                easy = (RadioButton) findViewById(R.id.easy_map3);
                normal = (RadioButton) findViewById(R.id.normal_map3);
                hard = (RadioButton) findViewById(R.id.hard_map3);
                infinity = (RadioButton) findViewById(R.id.infinite_map3);
                if(easy.isChecked()){
                    extrainfo = "31";
                }
                else if(normal.isChecked()){
                    extrainfo ="32";
                }
                else if(hard.isChecked()){
                    extrainfo ="33";
                }
                else if(infinity.isChecked()){
                    extrainfo = "34";

                    Intent intentIE = new Intent("com.kevindiana.forestdefence20.HeroSelect");
                    intentIE.putExtra("Chooser", extrainfo);
                    startActivity(intentIE);
                    break;
                }
                intent.putExtra("Chooser",extrainfo);
                startActivity(intent);

                break;
        }
    }
}

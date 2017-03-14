package com.kevindiana.forestdefence20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Game_Chooser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__chooser);
    }

    public void OnClickButtonListener(View view){

        Intent intent = new Intent("com.kevindiana.forestdefence20.Game");
        // the # in the tens place determins map
        // the # in the ones place determins the difficulty

        // preset to map 1 difuculty normal
        String extrainfo = "12";
        RadioButton easy, normal, hard;

        switch(view.getId()){
            case R.id.map1:

                easy = (RadioButton) findViewById(R.id.easy_map1);
                normal = (RadioButton) findViewById(R.id.normal_map1);
                hard = (RadioButton) findViewById(R.id.hard_map1);
                if(easy.isChecked()){
                    extrainfo = "11";
                }
                else if(normal.isChecked()){
                    extrainfo ="12";
                }
                else if(hard.isChecked()){
                    extrainfo ="13";
                 }
                intent.putExtra("Chooser",extrainfo);
                startActivity(intent);

                break;
            case R.id.map2:

                easy = (RadioButton) findViewById(R.id.easy_map1);
                normal = (RadioButton) findViewById(R.id.normal_map1);
                hard = (RadioButton) findViewById(R.id.hard_map1);
                if(easy.isChecked()){
                    extrainfo = "21";
                }
                else if(normal.isChecked()){
                    extrainfo ="22";
                }
                else if(hard.isChecked()){
                    extrainfo ="23";
                }
                intent.putExtra("Chooser",extrainfo);
                startActivity(intent);
                break;
        }
    }
}

package com.kevindiana.forestdefence20;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class How2 extends AppCompatActivity {

    private int counter = 0;
    ImageView guideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how2);

        // get reference to gif image in layour
        guideView = (ImageView) findViewById(R.id.guide);
        // set animation gif as background resource for view
        guideView.setBackgroundResource(R.drawable.how2guide_08);



    }

    public void ButtonListener(View view){

        //RelativeLayout guide = new RelativeLayout(this);

        switch(view.getId()){

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

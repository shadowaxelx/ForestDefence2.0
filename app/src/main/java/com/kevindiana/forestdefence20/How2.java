package com.kevindiana.forestdefence20;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class How2 extends AppCompatActivity {

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how2);



        // get reference to gif image in layour
        ImageView guideView = (ImageView) findViewById(R.id.guide);
        // set animation gif as background resource for view
        guideView.setBackgroundResource(R.drawable.how2guide_00);

    }
}

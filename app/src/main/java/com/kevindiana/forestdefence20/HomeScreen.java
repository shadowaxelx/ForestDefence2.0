package com.kevindiana.forestdefence20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static com.kevindiana.forestdefence20.R.styleable.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void OnClickButtonListener(View view){
        switch(view.getId()){
            case R.id.imageButton:
                Intent intent = new Intent("com.kevindiana.forestdefence20.Game");
                startActivity(intent);
                break;
        }
    }

}

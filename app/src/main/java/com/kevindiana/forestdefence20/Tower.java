package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static android.R.attr.bitmap;

/**
 * Created by kevin on 2/7/2017.
 */

public class Tower extends GameObject{
    private Bitmap image;

    // tower type 1 sniper, 2 shoots 2, 3 slows
    public Tower(Bitmap res, int x, int y, int towertype){
        super.x = x;
        super.y = y;
        super.width = 130;
        super.height = 130;
        image = res;
        // sets the tower lvl automatically to 1
        super.level = 1;

        switch(towertype){
            // sniper tower
            case 1:
                // setting the game objects states for this particular tower object
                super.power = 5;
                // 3.5 * 130
                super.range = 455;
                super.attack_speed = 1.25;
                super.upgrade = 8;
                super.sell = 5;

                break;

            // double shot tower
            case 2:
                break;
            // slow tower
            case 3:
                break;
        }


    }

    public void update(){

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }
}

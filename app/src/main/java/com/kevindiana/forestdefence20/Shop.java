package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by kevin on 1/4/2017.
 */

public class Shop extends GameObject {
    private Bitmap image;
    private int gold;

    // for now only 3 different towers
    public Shop(Bitmap res, int x, int y, int towertype){

        super.x = x;
        super.y = y;
        super.width = 130;
        super.height = 130;
        image = res;
        switch(towertype){
            case 1:
                gold = 10;
                setGold(gold);
                break;

            case 2:
                break;
            case 3:
                break;
        }

    }

    public void update(){

    }

    public void draw(Canvas canvas){

        // to color and make the grid
        Paint gray_paintbrush_fill;
        gray_paintbrush_fill = new Paint();
        gray_paintbrush_fill.setColor(Color.GRAY);
        gray_paintbrush_fill.setStyle(Paint.Style.FILL);
        //red_paintbrush_stroke.setStrokeWidth(10);

        canvas.drawBitmap(image, x, y, null);
    }
}

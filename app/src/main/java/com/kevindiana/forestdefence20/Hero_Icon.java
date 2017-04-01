package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 3/31/2017.
 */

public class Hero_Icon extends GameObject {

    private Bitmap image;

    Hero_Icon(Bitmap res, int x, int y){
        super.x = x;
        super.y = y;
        super.width = 128;
        super.height = 128;
        image = res;

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }
}

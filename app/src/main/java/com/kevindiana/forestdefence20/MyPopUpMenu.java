package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by kevin on 2/8/2017.
 */

public class MyPopUpMenu extends GameObject {

    private Bitmap image;

    //public PopUpMenu(Bitmap res, int towertype, int towerLvl){
    public MyPopUpMenu(Bitmap res){

        // x, y are the x, y coordinates
        super.x = 1170;
        super.y = 520;
        super.width = 520;
        super.height = 390;
        image = res;

    }

    // incase you want some kind of animation
    public void update(){

    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(image, x, y, null);
    }

}

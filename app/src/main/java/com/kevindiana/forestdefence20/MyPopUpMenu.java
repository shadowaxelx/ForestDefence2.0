package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by kevin on 2/8/2017.
 */

public class MyPopUpMenu extends GameObject {

    private Bitmap m_image;

    //public PopUpMenu(Bitmap res, int towertype, int towerLvl){
    public MyPopUpMenu(Bitmap a_res){

        // x, y are the x, y coordinates
        m_x = 1170;
        m_y = 520;
        m_width = 520;
        m_height = 390;
        m_image = a_res;

    }

    // incase you want some kind of animation
    public void update(){

    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(m_image, m_x, m_y, null);
    }

}

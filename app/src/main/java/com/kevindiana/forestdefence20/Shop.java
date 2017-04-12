package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by kevin on 1/4/2017.
 */

/**
 class Shop

 NAME

 Shop

 SYNOPSIS

    class Shop
        Bitmap m_image -> holds image

 DESCRIPTION

 Creates the shots that the heros will shoot depending on what type of hero has been selected

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 4:11pm 1/4/2017

 */
public class Shop extends GameObject {
    private Bitmap m_image;

    /**
     public Shop(Bitmap a_res, int a_x, int a_y, int a_towertype)

     NAME

     Shop

     SYNOPSIS

     public Shop(Bitmap a_res, int a_x, int a_y, int a_towertype)
        Bitmap a_res -> image being passed in
        int a_x -> the x coord of the image
        int a_y -> the y coord of the image
        int a_towertype -> the type of shop item


     DESCRIPTION

        Creates the shop item images to be displayed at the bottom of the screen

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     4:11pm 1/4/2017

     */
    public Shop(Bitmap a_res, int a_x, int a_y, int a_towertype){

        m_x = a_x;
        m_y = a_y;
        m_width = 130;
        m_height = 130;
        m_image = a_res;

    }

    // if you wanted to add animation
    public void update(){

    }

    /**
     public void draw(Canvas canvas)

     NAME

     draw

     SYNOPSIS

     public void draw(Canvas canvas)
     Canvas canvas -> the canvas the game is using(the screen) to draw things on

     DESCRIPTION

     is called in order to draw the images to the canvas

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     2:30pm 3/31/2017

     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(m_image, m_x, m_y, null);
    }

}

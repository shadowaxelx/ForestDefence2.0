package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 3/31/2017.
 */

/**
 class Hero_Icon

 NAME

    Hero_Icon

 SYNOPSIS

    class Hero_Icon
        Bitmap m_image -> holds the image for the Hero_Icon


 DESCRIPTION

    Creates the Hero icon so the player can select, move, and see hero stats

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 1:55pm 3/31/2017

 */
public class Hero_Icon extends GameObject {

    private Bitmap m_image;

    /**
     Hero_Icon(Bitmap a_res, int a_x, int a_y)

     NAME

     Hero_Icon

     SYNOPSIS

     Hero_Icon(Bitmap a_res, int a_x, int a_y)
        Bitmap a_res -> the image being used for the hero icon
        int a_x -> x coord for placement of hero icon
        int a_y -> y coord for placement of hero icon


     DESCRIPTION

        Constructor for the Hero_icon

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:55pm 3/31/2017

     */
    Hero_Icon(Bitmap a_res, int a_x, int a_y){
        m_x = a_x;
        m_y = a_y;
        m_width = 128;
        m_height = 128;
        m_image = a_res;

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

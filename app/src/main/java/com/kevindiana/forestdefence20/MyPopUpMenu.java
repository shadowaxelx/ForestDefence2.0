package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by kevin on 2/8/2017.
 */

/**
 class MyPopUpMenu

 NAME

 MyPopUpMenu

 SYNOPSIS
 class MyPopUpMenu
    Bitmap m_image -> hold image used for pop_up


 DESCRIPTION

    Creates a popup menu when towers are selected for player select what to do with tower

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 4:23Pm 2/8/2017

 */
public class MyPopUpMenu extends GameObject {

    private Bitmap m_image;

    /**
     public MyPopUpMenu(Bitmap a_res)

     NAME

     MyPopUpMenu

     SYNOPSIS
     public MyPopUpMenu(Bitmap a_res)
        Bitmap a_res -> the image being passed in to use


     DESCRIPTION

        Constructor for MyPopUpMenu

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     4:23Pm 2/8/2017

     */
    public MyPopUpMenu(Bitmap a_res){

        // x, y are the x, y coordinates where it will appear
        m_x = 1170;
        m_y = 520;
        m_width = 520;
        m_height = 390;
        m_image = a_res;

    }

    // incase you want some kind of animation
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

     4:30pm 2/8/2017

     */
    public void draw(Canvas canvas){

        canvas.drawBitmap(m_image, m_x, m_y, null);
    }

}

package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 3/10/2017.
 */

/**
 class Start_Monster_Wave_Icon

 NAME

 Start_Monster_Wave_Icon

 SYNOPSIS

 class Start_Monster_Wave_Icon
    Bitmap m_spritesheet -> where the images will be stored
    Animation m_animation -> creating an animation object

 DESCRIPTION

    Creates a flashing icon which when pressed will spawn the monsters

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 7:11pm 3/10/2017

 */
public class Start_Monster_Wave_Icon extends GameObject {
    private Bitmap m_spritesheet;
    private Animation m_animation = new Animation();

    /**
     Start_Monster_Wave_Icon(Bitmap a_res, int a_x, int a_y, int a_numframes )

     NAME

     Start_Monster_Wave_Icon

     SYNOPSIS

     Start_Monster_Wave_Icon(Bitmap a_res, int a_x, int a_y, int a_numframes )
        Bitmap a_res -> image being passed through
        int a_x -> x coord where the image will be
        int a_y -> y coord where the image will be
        int a_numFrames -> number image frames that will cycle through

     DESCRIPTION

     Creates a flashing icon which when pressed will spawn the monsters

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     7:11pm 3/10/2017

     */
    Start_Monster_Wave_Icon(Bitmap a_res, int a_x, int a_y, int a_numframes ){
        m_x = a_x;
        m_y = a_y;
        m_width = 128;
        m_height = 128;

        Bitmap image[] = new Bitmap[a_numframes];
        m_spritesheet = a_res;

        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(m_spritesheet, 0, i*m_height, m_width, m_height);
        }

        m_animation.setFrames(image);
        m_animation.setDelay(360);

    }

    // updates the animation
    public void update(){
        m_animation.update();
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
    public void draw(Canvas canvas) {
        try{
            canvas.drawBitmap(m_animation.getImage(),m_x,m_y,null);
        }catch(Exception e){}
    }

}

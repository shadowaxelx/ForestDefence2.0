package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 3/10/2017.
 */

public class Start_Monster_Wave_Icon extends GameObject {
    private Bitmap m_spritesheet;
    private Animation m_animation = new Animation();

    Start_Monster_Wave_Icon(Bitmap a_res, int a_x, int a_y, int a_numframes ){
        m_x = a_x;
        m_y = a_y;
       // super.width = 32;
        //super.height = 32;
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

    public void update(){
        m_animation.update();
    }

    public void draw(Canvas canvas) {
        try{
            canvas.drawBitmap(m_animation.getImage(),m_x,m_y,null);
        }catch(Exception e){}
    }

}

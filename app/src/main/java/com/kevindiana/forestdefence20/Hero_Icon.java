package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 3/31/2017.
 */

public class Hero_Icon extends GameObject {

    private Bitmap m_image;

    Hero_Icon(Bitmap a_res, int a_x, int a_y){
        m_x = a_x;
        m_y = a_y;
        m_width = 128;
        m_height = 128;
        m_image = a_res;

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(m_image, m_x, m_y, null);
    }
}

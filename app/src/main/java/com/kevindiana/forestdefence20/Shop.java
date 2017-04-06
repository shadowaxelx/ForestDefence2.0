package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by kevin on 1/4/2017.
 */

public class Shop extends GameObject {
    private Bitmap m_image;
    private int gold;
    private int m_cost;

    // for now only 3 different towers
    public Shop(Bitmap a_res, int a_x, int a_y, int a_towertype){

        m_x = a_x;
        m_y = a_y;
        m_width = 130;
        m_height = 130;
        m_image = a_res;

        switch(a_towertype){
            case 1:
                m_cost = 5;
                break;
            case 2:
                m_cost = 5;
                break;
            case 3:
                m_cost = 5;
                break;
        }
    }

    public void update(){

    }

    public void draw(Canvas canvas){

        // to color and make the grid
        //Paint gray_paintbrush_fill;
        //gray_paintbrush_fill = new Paint();
        //gray_paintbrush_fill.setColor(Color.GRAY);
        //gray_paintbrush_fill.setStyle(Paint.Style.FILL);
        //red_paintbrush_stroke.setStrokeWidth(10);

        canvas.drawBitmap(m_image, m_x, m_y, null);
    }

    public int getCost(){
        return m_cost;
    }
}

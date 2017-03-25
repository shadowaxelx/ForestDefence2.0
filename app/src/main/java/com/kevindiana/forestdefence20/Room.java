package com.kevindiana.forestdefence20;

/**
 * Created by kevin on 12/28/2016.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Room{
    private Bitmap image;


    // x is the x coord, y is the y coords and dx is the vector
    private int x, y, dx;

    //Room Guide
    /*
        0 for empty spot
        1 is for monster path
        2 is shop item 1
        3 is shop item 2
        4 is shop item 3
        5 is cant put towers there
        6 menu
        7 hero icon
        11 is tower 1
        12 is tower 2
        13 is tower 3
     */

    private int[][] room1 = new int [][]{
            {5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 6, 5},
            {5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0},
            {5, 5, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {5, 5, 5, 5, 5, 5, 5, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0}

    };
    private int[][] room2 = new int [][]{
            {0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0, 0, 5, 5, 5, 6, 0},
            {0, 0, 0, 5, 0, 1, 1, 1, 5, 5, 5, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            {0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            {1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {5, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 5, 5, 0, 0},
            {5, 5, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 5, 5, 5, 5, 5, 0},
            {5, 5, 0, 0, 0, 0, 5, 5, 5, 1, 0, 0, 0, 0, 1, 5, 5, 5, 5, 0, 0},
            {0, 0, 0, 5, 0, 0, 5, 5, 5, 1, 1, 1, 1, 1, 1, 0, 5, 0, 0, 0, 0},
            {5, 5, 5, 5, 5, 5, 5, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0}

    };

    private int [][] room3 = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 5, 5, 5, 5, 6, 0},
            {0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 5, 0},
            {1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 5, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1},
            {5, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0},
            {5, 5, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0},
            {5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0},
            {5, 5, 5, 5, 5, 5, 5, 2, 3, 4, 0, 0, 5, 5, 5, 5, 5, 5, 5, 7, 0}
    };


    public Room(Bitmap res){
        image = res;
    }
    // updates the background
    public void update(){

        // this is stuff if you want to hvae it scrollable or move on its on
        x+=dx;
        // this loop will do nothing
        if(x < 0){
            // if(x < -GamePanel.WIDTH){
            x = 0;
            // dx +=1;

        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
        // for scrolling the image, if part of the image is off screen
        // draw image ontop of it
        if(x<0){
            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
            //canvas.drawBitmap()
        }

    }

    public int [][] getroom(int roomnum){
        switch(roomnum){
            case 1:
                return room1;
            case 2:
                return room2;
            case 3:
                return room3;
        }
        return room1;
    }

    public void setVector(int dx){
        this.dx = dx;
    }
}

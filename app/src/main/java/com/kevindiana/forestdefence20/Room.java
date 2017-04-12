package com.kevindiana.forestdefence20;

/**
 * Created by kevin on 12/28/2016.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 class  Room

 NAME

 Room

 SYNOPSIS
    class Room
        Bitmap m_image -> holds the image being used for the map
        int [][] room1 -> holds the numbers that create the grid for map 1
        int [][] room2 -> holds the numbers that create the grid for map 2
        int [][] room3 -> hold the numbers that create the grid for map 3

 DESCRIPTION

    Sets up the map image and the map 2D array for gameplay

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 8:00Pm 12/28/2016

 */
public class Room{
    private Bitmap m_image;

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
            {5, 5, 5, 5, 5, 5, 5, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0}

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
            {5, 5, 5, 5, 5, 5, 5, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0}

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
            {5, 5, 5, 5, 5, 5, 5, 2, 3, 4, 0, 0, 5, 5, 5, 5, 5, 5, 7, 0, 0}
    };

    // constructor setting the image for the map selected
    public Room(Bitmap a_res){
        m_image = a_res;
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

    /**
     public void update(int a_monster_x, int a_monster_y)

     NAME

     update

     SYNOPSIS

     public void update(int a_monster_x, int a_monster_y)
     int a_monster_x -> monsters x coord
     int a_monster_y -> monsters y coord


     DESCRIPTION

     updates the position of the attack to move closer and closer to the monster
     it was assigned to attack

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     6:20pm 3/30/2017

     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(m_image, x, y, null);
        // for scrolling the image, if part of the image is off screen
        // draw image ontop of it
        if(x<0){
            canvas.drawBitmap(m_image, x+GamePanel.WIDTH, y, null);
            //canvas.drawBitmap()
        }

    }

    // returns the room that was chosen from select screen
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

}

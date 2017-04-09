package com.kevindiana.forestdefence20;

import android.graphics.Rect;

/**
 * Created by kevin on 12/29/2016.
 */

/**
 class GameObject

 NAME

 GameObject

 SYNOPSIS
    int m_x -> the x coordinates of the object
    int m_y -> the y coordinates of the object
    int m_width -> the width of the object
    int m_height -> the height of the object
    m_level -> the level of the object
    m_power -> the strength of the object

 DESCRIPTION

    Every Game Object shares these traits

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 11:24Am 12/29/2017

 */
// all objects in the game will eccept this class
public class GameObject {
    protected int m_x;
    protected int m_y;
    protected int m_width;
    protected int m_height;
    protected int m_level;
    protected int m_power;

    // sets the X coord
    public void setX(int a_x){
        m_x = a_x;
    }
    // sets the Y coord
    public void setY(int a_y){
        m_y = a_y;
    }
    // gets the x coord of the object
    public int getX(){
        return m_x;
    }
    // gets the y coord of the object
    public int getY(){
        return m_y;
    }
    // gets the power/damage of the object
    public int getPower(){return m_power;}
    // gets the height of the object
    public int getHeight(){
        return m_height;
    }
    // gets the width of the object
    public int getWidth(){
        return m_width;
    }
    // gets the hitbox of the object
    public Rect getRectangle(){
        return new Rect(m_x, m_y, m_x+m_width -65, m_y+m_height - 65);
    }



}

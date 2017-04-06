package com.kevindiana.forestdefence20;

import android.graphics.Rect;

/**
 * Created by kevin on 12/29/2016.
 */

// all objects in the game will eccept this class
public class GameObject {
    protected int m_x;
    protected int m_y;
    protected int m_width;
    protected int m_height;
    protected int [][]m_room;
    protected int m_gold;
    protected int m_level;
    //protected int range;
    //protected double attack_speed;
    protected int m_power;
    protected int upgrade;
    protected int sell;
    protected int health;

    public void setX(int a_x){
        m_x = a_x;
    }
    public void setY(int a_y){
        m_y = a_y;
    }
    public void setRoom(int [][]a_room){m_room = a_room;}
    public int [][] setRoom(){return m_room;}
    public int getX(){
        return m_x;
    }
    public int getY(){
        return m_y;
    }
    public int getPower(){return m_power;}
    public int getGold(int towerType){return m_gold;}
    public void setGold(int a_towerGold) {m_gold = a_towerGold;}
    public int getHeight(){
        return m_height;
    }
    public int getWidth(){
        return m_width;
    }
    public Rect getRectangle(){
        return new Rect(m_x, m_y, m_x+m_width -65, m_y+m_height - 65);
    }



}

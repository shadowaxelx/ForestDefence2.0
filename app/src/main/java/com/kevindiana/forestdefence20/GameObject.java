package com.kevindiana.forestdefence20;

import android.graphics.Rect;

/**
 * Created by kevin on 12/29/2016.
 */

// all objects in the game will eccept this class
public class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int [][]room;
    protected int gold;
    protected int level;
    //protected int range;
    //protected double attack_speed;
    protected int power;
    protected int upgrade;
    protected int sell;
    protected int health;

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setRoom(int [][]room){this.room = room;}
    public int [][] setRoom(){return room;}
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getPower(){return power;}
    //public int getRange() {return range;}
   // public double getAttackSpeed() {return attack_speed;}
    public int getGold(int towerType){return gold;}
    public void setGold(int towerGold) {gold = towerGold;}
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
   // public void setHealth(int damage){health -= damage;}
    //public int getHealth(){return health;}
    // checks if rectangle space is intersecting
    public Rect getRectangle(){
        return new Rect(x, y, x+width -65, y+height - 65);
    }



}

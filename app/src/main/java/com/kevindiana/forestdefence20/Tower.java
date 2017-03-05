package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static android.R.attr.bitmap;

/**
 * Created by kevin on 2/7/2017.
 */

public class Tower extends GameObject{
    private Bitmap image;
    // 1 is sniper, 2 double shot, 3 slow
    private int tower_type;
    private int tower_lvl;
    private int sell_cost;
    private int upgrade_cost;
    private float range;
    // it is in milla seconds
    private double attack_speed;

    // tower type 1 sniper, 2 shoots 2, 3 slows
    public Tower(Bitmap res, int x, int y, int towertype){
        super.x = x;
        super.y = y;
        super.width = 130;
        super.height = 130;
        image = res;
        // sets the tower lvl automatically to 1
        super.level = 1;

        switch(towertype){
            // sniper tower
            case 1:
                this.tower_lvl = 1;
                // setting the game objects states for this particular tower object
                super.power = 5;
                // 3.5 * 130
                range = 455;
                // calculated in miliseconds so 1.5 seconds
                attack_speed = 1500;
                //super.attack_speed = 1.25;
                upgrade_cost = 8;
                sell_cost = 5;
                tower_type = towertype;

                break;

            // double shot tower
            case 2:
                this.tower_lvl = 1;
                super.power = 6;
                // 3.0 * 130
                range = 390;
                // calculated in miliseconds so 1.25 seconds
                attack_speed = 1250;
                //super.attack_speed = 1.25;
                upgrade_cost = 8;
                sell_cost = 5;
                tower_type = towertype;
                break;
            // slow tower
            case 3:
                this.tower_lvl = 1;
                super.power = 1;
                //4 * 130
                range = 520;
                // has rapid fire attack speed; .10 second attack speed
                attack_speed = 100;
                upgrade_cost = 8;
                sell_cost = 5;
                tower_type = towertype;
                break;
        }


    }

    public void update(){

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public int getTowerType(){
        return tower_type;
    }
    public int getSell_cost() {return sell_cost; }
    public int getUpgrade_cost() {return upgrade_cost; }
    public float getRange() {return range;}
    public double getAttackSpeed() {return attack_speed;}
    public void upgrade_tower(){

        // sniper tower
        if(tower_type == 1){
            switch (tower_lvl){
                case 1:
                    tower_lvl = 2;
                    power = 10;
                    //3.5 + .5(is the box the tower is actually in)
                    range = 520;
                    //1.25 attack speed per second
                    attack_speed = 1250;
                    upgrade_cost = 16;
                    sell_cost = 10;
                    break;
                case 2:
                    break;
                case 3:
                    break;

            }
        }
        // double shot tower
        else if(tower_type == 2){

        }
        // slow/ ice tower
        else if(tower_type == 3){

        }


    }
}

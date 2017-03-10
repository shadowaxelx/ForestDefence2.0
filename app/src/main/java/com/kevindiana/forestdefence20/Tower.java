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
            // sniper tower cost 5
            case 1:
                this.tower_lvl = 1;
                // setting the game objects states for this particular tower object
                super.power = 5;
                // 3.5 * 130  which is 3 + .5
                range = 455;
                // calculated in miliseconds so 1.5 seconds
                attack_speed = 1500;
                //super.attack_speed = 1.25;
                upgrade_cost = 10;
                sell_cost = 3;
                tower_type = towertype;

                break;

            // double shot tower cost 15
            case 2:
                this.tower_lvl = 1;
                super.power = 7;
                // 3.0 * 130
                range = 390;
                // calculated in miliseconds so 1.25 seconds
                attack_speed = 1000;
                //super.attack_speed = 1.25;
                upgrade_cost = 15;
                sell_cost = 5;
                tower_type = towertype;
                break;
            // slow tower cost 25
            case 3:
                this.tower_lvl = 1;
                super.power = 1;
                //4 * 130
                range = 520;
                // has rapid fire attack speed; .10 second attack speed
                attack_speed = 400;
                upgrade_cost = 30;
                sell_cost = 10;
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
    public int getTowerLvl() {return tower_lvl; }
    public void upgrade_tower(){

        // sniper tower
        if(tower_type == 1){
            switch (tower_lvl){
                // lvl 2 tower
                case 1:
                    tower_lvl = 2;
                    power = 12;
                    //3 + .5(is the box the tower is actually in) so range is 3.5
                    range = 520;
                    //1.25 attack speed per second
                    attack_speed = 1250;
                    upgrade_cost = 20;
                    sell_cost = 6;
                    break;
                // lvl 3
                case 2:
                    tower_lvl = 3;
                    power = 26;
                    // 4 + .5(is the box the tower is actually in) so range is 2.5
                    range = 650;
                    attack_speed = 1000;
                    upgrade_cost = 40;
                    sell_cost = 12;
                    break;
                case 3:
                    tower_lvl = 4;
                    power = 55;
                    // 5 + .5(is the box the tower is actually in)
                    range = 780;
                    attack_speed = 750;
                    // no upgrade cost max lvl
                    sell_cost = 24;
                    break;

            }
        }
        // double shot tower
        else if(tower_type == 2){
            switch (tower_lvl){
                // lvl 2 tower
                case 1:
                    tower_lvl = 2;
                    power = 18;
                    //3.5 + .5(is the box the tower is actually in)
                    range = 390;
                    //1.25 attack speed per second
                    attack_speed = 1000;
                    upgrade_cost = 30;
                    sell_cost = 10;
                    break;
                // lvl 3
                case 2:
                    tower_lvl = 3;
                    power = 40;
                    // 4 + .5(is the box the tower is actually in)
                    range = 455;
                    attack_speed = 1000;
                    upgrade_cost = 60;
                    sell_cost = 20;
                    break;
                case 3:
                    tower_lvl = 4;
                    power = 80;
                    // 5 + .5(is the box the tower is actually in)
                    range = 455;
                    attack_speed = 1000;
                    // no upgrade cost max lvl
                    sell_cost = 40;
                    break;

            }

        }
        // slow/ ice tower
        else if(tower_type == 3){

            switch (tower_lvl){
                // lvl 2 tower
                case 1:
                    tower_lvl = 2;
                    power = 2;
                    //4 * 130
                    range = 520;
                    //1.25 attack speed per second
                    attack_speed = 300;
                    upgrade_cost = 60;
                    sell_cost = 20;
                    break;
                // lvl 3
                case 2:
                    tower_lvl = 3;
                    power = 3;
                    //4 * 130
                    range = 520;
                    attack_speed = 200;
                    upgrade_cost = 120;
                    sell_cost = 40;
                    break;
                case 3:
                    tower_lvl = 4;
                    power = 4;
                    //4 * 130
                    range = 520;
                    attack_speed = 100;
                    // no upgrade cost max lvl
                    sell_cost = 80;
                    break;

            }

        }


    }
}

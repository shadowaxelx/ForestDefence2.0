package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static android.R.attr.bitmap;

/**
 * Created by kevin on 2/7/2017.
 */

public class Tower extends GameObject{
    private Bitmap m_image;
    // 1 is sniper, 2 double shot, 3 slow
    private int m_tower_type;
    private int m_tower_lvl;
    private int m_sell_cost;
    private int m_upgrade_cost;
    private float m_range;
    // it is in milla seconds
    private double m_attack_speed;

    // tower type 1 sniper, 2 shoots 2, 3 slows
    public Tower(Bitmap a_res, int a_x, int a_y, int a_towertype){
        m_x = a_x;
        m_y = a_y;
        m_width = 130;
        m_height = 130;
        m_image = a_res;
        // sets the tower lvl automatically to 1
        m_level = 1;
        m_tower_type = a_towertype;

        switch(a_towertype){
            // sniper tower cost 5
            case 1:
                m_tower_lvl = 1;
                // setting the game objects states for this particular tower object
                m_power = 5;
                // 3.5 * 130  which is 3 + .5
                m_range = 455;
                // calculated in miliseconds so 1.5 seconds
                m_attack_speed = 1500;
                //super.attack_speed = 1.25;
                m_upgrade_cost = 10;
                m_sell_cost = 3;
                

                break;

            // double shot tower cost 15
            case 2:
                m_tower_lvl = 1;
                m_power = 7;
                // 3.0 * 130
                m_range = 390;
                // calculated in miliseconds so 1.25 seconds
                m_attack_speed = 1000;
                //super.attack_speed = 1.25;
                m_upgrade_cost = 15;
                m_sell_cost = 5;
                
                break;
            // slow tower cost 25
            case 3:
                m_tower_lvl = 1;
                m_power = 1;
                //4 * 130
                m_range = 520;
                // has rapid fire attack speed; .10 second attack speed
                m_attack_speed = 400;
                m_upgrade_cost = 30;
                m_sell_cost = 10;
                
                break;
        }


    }

    public void update(){

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(m_image, m_x, m_y, null);
    }

    public int getTowerType(){
        return m_tower_type;
    }
    public int getSell_cost() {return m_sell_cost; }
    public int getUpgrade_cost() {return m_upgrade_cost; }
    public float getRange() {return m_range;}
    public double getAttackSpeed() {return m_attack_speed;}
    public int getTowerLvl() {return m_tower_lvl; }

    public void upgrade_tower(){

        // sniper tower
        if(m_tower_type == 1){
            switch (m_tower_lvl){
                // lvl 2 tower
                case 1:
                    m_tower_lvl = 2;
                    m_power = 12;
                    //3 + .5(is the box the tower is actually in) so m_range is 3.5
                    m_range = 520;
                    //1.25 attack speed per second
                    m_attack_speed = 1250;
                    m_upgrade_cost = 20;
                    m_sell_cost = 6;
                    break;
                // lvl 3
                case 2:
                    m_tower_lvl = 3;
                    m_power = 26;
                    // 4 + .5(is the box the tower is actually in) so m_range is 2.5
                    m_range = 650;
                    m_attack_speed = 1000;
                    m_upgrade_cost = 40;
                    m_sell_cost = 12;
                    break;
                case 3:
                    m_tower_lvl = 4;
                    m_power = 55;
                    // 5 + .5(is the box the tower is actually in)
                    m_range = 780;
                    m_attack_speed = 750;
                    // no upgrade cost max lvl
                    m_sell_cost = 24;
                    break;

            }
        }
        // double shot tower
        else if(m_tower_type == 2){
            switch (m_tower_lvl){
                // lvl 2 tower
                case 1:
                    m_tower_lvl = 2;
                    m_power = 18;
                    //3.5 + .5(is the box the tower is actually in)
                    m_range = 390;
                    //1.25 attack speed per second
                    m_attack_speed = 1000;
                    m_upgrade_cost = 30;
                    m_sell_cost = 10;
                    break;
                // lvl 3
                case 2:
                    m_tower_lvl = 3;
                    m_power = 40;
                    // 4 + .5(is the box the tower is actually in)
                    m_range = 455;
                    m_attack_speed = 1000;
                    m_upgrade_cost = 60;
                    m_sell_cost = 20;
                    break;
                case 3:
                    m_tower_lvl = 4;
                    m_power = 80;
                    // 5 + .5(is the box the tower is actually in)
                    m_range = 455;
                    m_attack_speed = 1000;
                    // no upgrade cost max lvl
                    m_sell_cost = 40;
                    break;

            }

        }
        // slow/ ice tower
        else if(m_tower_type == 3){

            switch (m_tower_lvl){
                // lvl 2 tower
                case 1:
                    m_tower_lvl = 2;
                    m_power = 2;
                    //4 * 130
                    m_range = 520;
                    //1.25 attack speed per second
                    m_attack_speed = 300;
                    m_upgrade_cost = 60;
                    m_sell_cost = 20;
                    break;
                // lvl 3
                case 2:
                    m_tower_lvl = 3;
                    m_power = 3;
                    //4 * 130
                    m_range = 520;
                    m_attack_speed = 200;
                    m_upgrade_cost = 120;
                    m_sell_cost = 40;
                    break;
                case 3:
                    m_tower_lvl = 4;
                    m_power = 4;
                    //4 * 130
                    m_range = 520;
                    m_attack_speed = 100;
                    // no upgrade cost max lvl
                    m_sell_cost = 80;
                    break;

            }

        }


    }
}

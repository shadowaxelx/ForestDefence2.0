package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 2/12/2017.
 */

public class TowerShot extends GameObject {

    private Bitmap m_spritesheet;
    private Animation m_animation = new Animation();
    private int m_shot_Speed;
    private int m_numFrames;
    private int m_monsterID;
    // 1 is fire tower, 2 is double shot, 3 is ice tower
    private int m_type;

    public TowerShot(Bitmap a_res, int a_x, int a_y, int a_numFrames, int a_damage, int a_monsterID, int a_type){
        m_x = a_x;
        m_y = a_y;
        m_width = 125;
        m_height = 128;
        m_power = a_damage;
        //super.attack_speed = attackSpeed;
        m_shot_Speed = 35;
        m_type = a_type;
        m_monsterID = a_monsterID;
        m_numFrames = a_numFrames;

        Bitmap[] image = new Bitmap[m_numFrames];
        m_spritesheet = a_res;

        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(m_spritesheet, 0, i*m_height, m_width, m_height);
        }

        // sets the animation
        m_animation.setFrames(image);
        // sets how fast the objects will move, lower number faster it moves
        // make this divisible by 130 becuase of the squares
        m_animation.setDelay(360 - m_shot_Speed);

    }

    public void update(int monster_x, int monster_y){

        // too keep bullet moving toward its target at all cost

        if(monster_x > m_x ){
            m_x += m_shot_Speed;
        }
        else{
            m_x -= m_shot_Speed;
        }

        if(monster_y > m_y){
            m_y += m_shot_Speed;
        }
        else{
            m_y -= m_shot_Speed;
        }

        m_animation.updateShot();

    }

    public void draw(Canvas canvas) {
        try{
            canvas.drawBitmap(m_animation.getImage(),m_x,m_y,null);
        }catch(Exception e){}
    }

    public int getMonsterID(){return m_monsterID;}
    public int getShotType(){return m_type;}
}

package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 3/30/2017.
 */

/**
 class HeroAttack

 NAME

    HeroAttack

 SYNOPSIS

    class HeroAttack
        Bitmap m_spritesheet -> holds the image's being used
        Animation m_animation -> creates a new animation object
        int m_shot_speed -> holds how fast the shot will travel on screen
        int m_monsterID -> holds the monster ID that the shot is targeting
        int m_numFrames -> holds the number of frames the image has to cycle through
        int m_type -> number which tells what type of shot it should be depending on the hero type


 DESCRIPTION

    Creates the shots that the heros will shoot depending on what type of hero has been selected

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 4:55pm 3/30/2017

 */
public class HeroAttack extends GameObject {

    private Bitmap m_spritesheet;
    private Animation m_animation = new Animation();
    private int m_shot_Speed;
    private int m_monsterID;
    private int m_numFrames;
    // 1 is \knight, 2 archer, 3 wizard
    private int m_type;

    /**
     HeroAttack(Bitmap a_res, int a_x, int a_y, int a_numFrames, int a_damage, int a_monsterID, int a_type)

     NAME

     HeroAttack

     SYNOPSIS

     HeroAttack(Bitmap a_res, int a_x, int a_y, int a_numFrames, int a_damage, int a_monsterID, int a_type)
        Bitmap a_res -> image being passed in that will be used
        int a_x -> x coord for the image to be drawn to
        int a_y -> y coord for the image to be drawn to
        int a_numFrames -> number of frames the image has to cycle through
        int a_damage -> the amount of damage the shot/attack will do
        int a_monsterID -> the monster ID to which the attack is following and will hit
        int a_type -> the type of attack(which image) will be used


     DESCRIPTION

        Constructor for the HeroAttack which will create the attack on screen

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     4:55pm 3/30/2017

     */
    HeroAttack(Bitmap a_res, int a_x, int a_y, int a_numFrames, int a_damage, int a_monsterID, int a_type){
        m_x = a_x;
        m_y = a_y;
        m_width = 128;
        m_height = 128;
        m_power = a_damage;

        m_shot_Speed = 35;
        m_type = a_type;
        m_monsterID = a_monsterID;
        m_numFrames = a_numFrames;

        Bitmap[] image = new Bitmap[a_numFrames];
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
    public void update(int a_monster_x, int a_monster_y){

        // too keep bullet moving toward its target at all cost

        if(a_monster_x > m_x ){
            m_x += m_shot_Speed;
        }
        else{
            m_x -= m_shot_Speed;
        }

        if(a_monster_y > m_y){
            m_y += m_shot_Speed;
        }
        else{
            m_y -= m_shot_Speed;
        }

        m_animation.updateShot();

    }

    /**
     public void draw(Canvas canvas)

     NAME

     draw

     SYNOPSIS

     public void draw(Canvas canvas)
     Canvas canvas -> the canvas the game is using(the screen) to draw things on

     DESCRIPTION

     is called in order to draw the images to the canvas

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     2:30pm 3/31/2017

     */
    public void draw(Canvas canvas) {
        try{
            canvas.drawBitmap(m_animation.getImage(),m_x,m_y,null);
        }catch(Exception e){}
    }

    // return monster ID number
    public int getMonsterID(){return m_monsterID;}

}

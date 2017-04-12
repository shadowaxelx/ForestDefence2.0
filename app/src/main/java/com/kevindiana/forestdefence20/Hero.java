package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static java.lang.StrictMath.abs;

/**
 * Created by kevin on 3/24/2017.
 */

/**
 class Hero

 NAME

    Hero

 SYNOPSIS

    Hero
        Bitmap m_spritesheet -> will hold the bitmap image
        Animation m_animation -> declaring animation class object
        boolean m_moving -> variable to know if the hero is being moved or not
        float m_range -> attack range of the hero which is divided by 130 then sub 65 to get actual range number
        int m_damage -> amount of damage hero does
        double m_attack_speed -> how fast hero attacks in milliseconds
        int m_move_speed -> the speed at which hero will move
        int m_newxLocation -> new requested x cord location of the hero
        m_newyLocation -> new requested y cord location of hero
        int m_direction -> used to tell what direction hero is moving in
        static final int m_still = 0, m_left = 1, m_right = 2, m_up = 3, m_down = 4;
        int m_type -> determins which hero you picked, knight, archer, wizard
        int m_exp -> the amount of exp the hero has
        int m_lvl -> the lvl the hero is at
        int m_neededExp -> how much exp is needed for the next lvl

 DESCRIPTION

    This class is used to implement the hero into the game for the infinite game mode.  It controlls the
    movement of the hero and the lvl up system of the hero.

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 1:00pm 3/24/2017

 */
public class Hero extends GameObject {

    private Bitmap m_spritesheet;
    private Animation m_animation = new Animation();
    private boolean m_moving;
    private float m_range;
    private int m_damage;
    private double m_attack_speed;
    private int m_move_speed;
    private int m_newxLocation;
    private int m_newyLocation;
    // used to tell if the direction the hero was moving in is done so they can change direction to finish their movement
    // 0 is stay still, 1 is left, 2 is right, 3 is up, 4 is down;
    private int m_direction = 0;
    private static final int m_still = 0, m_left = 1, m_right = 2, m_up = 3, m_down = 4;
    private int m_type;
    private int m_exp;
    private int m_lvl;
    private int m_neededExp;

    /**
     Hero(Bitmap a_res, int a_x, int a_y, int a_hero_type)

     NAME

        Hero

     SYNOPSIS

        Hero(Bitmap a_res, int a_x, int a_y, int a_hero_type)
            Bitmap a_res -> image that is being sent int to use
            int a_x -> the x coord where the image will be drawn
            int a_y -> the y coord where the image will be drawn
            int a_hero_type -> tells the game which hero should be created

     DESCRIPTION

        Is the Hero constructor which creates the hero

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:00pm 3/24/2017

     */
    Hero(Bitmap a_res, int a_x, int a_y, int a_hero_type){
        m_x = a_x;
        m_y = a_y;
        m_newxLocation = a_x;
        m_newyLocation = a_y;
        m_width = 128;
        m_height = 128;
        m_type = a_hero_type;

        // hero starts out standing still so not moving
        m_moving = false;

        // giongto cycle through 2 images
        Bitmap image[] = new Bitmap[2];
        m_spritesheet = a_res;

        // image starts out facing forwards
        image[0] = Bitmap.createBitmap(m_spritesheet, 0, 9*m_height, m_width, m_height);
        image[1] = Bitmap.createBitmap(m_spritesheet, 0, 9*m_height, m_width, m_height);

        //for(int i = 0; i < image.length; i++){
         //   image[i] = Bitmap.createBitmap(spritesheet, 0, i*height, width, height);
        //}

        m_animation.setFrames(image);
        m_animation.setDelay(360);

        switch (a_hero_type){
            // knight
            case 1:
                //(1 + .5) * 130
                m_range = 195;
                m_damage = 10;
                // miliseconds
                m_attack_speed = 500;
                m_move_speed = 30;
                m_lvl = 1;
                m_neededExp = 10 + (5 * (m_lvl - 1));
                m_exp = 0;
                m_animation.setDelay(390 - m_move_speed);
                break;
            // archer
            case 2:
                //(4.5 + .5)* 130
                m_range = 650;
                m_damage = 4;
                m_attack_speed = 1250;
                m_move_speed = 17;
                m_lvl = 1;
                m_neededExp = 10 + (5 * (m_lvl - 1));
                m_exp = 0;
                m_animation.setDelay(390 - m_move_speed);
                break;
            // wizard
            case 3:
                //(4 + .5) * 130
                m_range = 585;
                m_damage = 6;
                // miliseconds
                m_attack_speed = 1250;
                m_move_speed = 17;
                m_lvl = 1;
                m_neededExp = 10 + (5 * (m_lvl - 1));
                m_exp = 0;
                m_animation.setDelay(390 - m_move_speed);
                break;
        }

    }

    /**
     public void move_to(int a_newx, int a_newy)

     NAME

        move_to

     SYNOPSIS

     public void move_to(int a_newx, int a_newy)
        int a_newx -> new x cord of the hero to move to
        int a_newy -> new y cord of the hero to move to

     DESCRIPTION

        Sets the coords for the hero to move to

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:00pm 3/24/2017

     */
    public void move_to(int a_newx, int a_newy){
        m_newxLocation = (a_newx * 130);
        m_newyLocation = (a_newy * 130);
        m_direction = m_still;
        m_moving = true;
    }

    /**
     public void update()

     NAME

        update

     SYNOPSIS

     public void update()

     DESCRIPTION

        Updates the location of the hero as well as the animation of the hero

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:00pm 3/24/2017

     */
    public void update(){

        // done if the hero has finished a direction or no move command already set
        if(m_direction == m_still){
            // if x is furthest directino away move that way first
            if((abs(m_newxLocation - m_x) > abs(m_newyLocation - m_y))){
                if(m_newxLocation - m_x < 0){
                    m_direction = m_left;
                    // function for switching direction
                    switchdirection(m_direction);
                }
                else{
                    m_direction = m_right;
                    // function for switching direction
                    switchdirection(m_direction);
                }
                m_moving = true;
            }
            // move in they y direction first
            else if((abs(m_newxLocation - m_x) < abs(m_newyLocation - m_y))){
                if(m_newyLocation - m_y < 0){
                    m_direction = m_up;
                    // function for switching direction
                    switchdirection(m_direction);
                }
                else{
                    m_direction = m_down;
                    // function for switching direction
                    switchdirection(m_direction);
                }
                m_moving = true;
            }
            // no comand/ finsihed moving
            else{
                m_direction = m_still;
                m_moving = true;
                // function for switching direction
                switchdirection(m_direction);
            }
        }
        // stay in the same direction
        else{
            m_moving = true;
            samedirection(m_direction);

        }




        m_animation.update();


    }

    /**
     private void samedirection(int a_direction)

     NAME

     samedircetion

     SYNOPSIS

     private void samedirection(int a_direction)
        int a_direction -> holds a number which tells the game which direction the hero is moving in

     DESCRIPTION

        Function used for when the hero is not turning so the animation doesnt need to be changed

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     2:20pm 3/24/2017

     */
    private void samedirection(int a_direction){
        switch(a_direction){
            case m_left:

                // make sure hero doesnt over run anything
                if(m_x - m_move_speed < m_newxLocation){
                    m_x -= (m_x - m_newxLocation);
                    m_direction = 0;
                }
                else{
                    m_x -= m_move_speed;
                }

                break;
            case m_right:

                if(m_x + m_move_speed > m_newxLocation){
                    m_x += (m_newxLocation - m_x);
                    m_direction = 0;
                }
                else{
                    m_x += m_move_speed;
                }
                break;
            case m_up:

                if(m_y - m_move_speed < m_newyLocation){
                    m_y -= (m_y - m_newyLocation);
                    m_direction = 0;
                }
                else{
                    m_y -= m_move_speed;
                }
                break;
            case m_down:

                if(m_y + m_move_speed > m_newyLocation){
                    m_y += (m_newyLocation - m_y);
                    m_direction = 0;
                }
                else{
                    m_y += m_move_speed;
                }
                break;
            case m_still:
                m_moving = false;
                break;


        }
    }

    /**
     private void switchdirection(int a_direction)

     NAME

     switchdircetion

     SYNOPSIS

     private void switchdirection(int a_direction)
     int a_direction -> holds a number which tells the game which direction the hero is moving in

     DESCRIPTION

     Function used for when the hero is going to change direction and the animation images need
        to be changed

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     2:30pm 3/24/2017

     */
    private void switchdirection(int a_direction){

        Bitmap[] image = new Bitmap[2];
        switch(a_direction){
            case m_left:
                // change the bitmap images
                image[0] = Bitmap.createBitmap(m_spritesheet, 0, 3*m_height, m_width, m_height);
                image[1] = Bitmap.createBitmap(m_spritesheet, 0, 4*m_height, m_width, m_height);
                m_animation.setFrames(image);
                // make sure hero doesnt over run anything
                if(m_x - m_move_speed < m_newxLocation){
                    m_x -= (m_x - m_newxLocation);
                    m_direction = 0;
                }
                else{
                    m_x -= m_move_speed;
                }

                break;
            case m_right:

                // change the bitmap images
                image[0] = Bitmap.createBitmap(m_spritesheet, 0, 0*m_height, m_width, m_height);
                image[1] = Bitmap.createBitmap(m_spritesheet, 0, 1*m_height, m_width, m_height);
                m_animation.setFrames(image);

                if(m_x + m_move_speed > m_newxLocation){
                    m_x += (m_newxLocation - m_x);
                    m_direction = 0;
                }
                else{
                    m_x += m_move_speed;
                }
                break;
            case m_up:

                // change the bitmap images
                image[0] = Bitmap.createBitmap(m_spritesheet, 0, 6*m_height, m_width, m_height);
                image[1] = Bitmap.createBitmap(m_spritesheet, 0, 7*m_height, m_width, m_height);
                m_animation.setFrames(image);

                if(m_y - m_move_speed < m_newyLocation){
                    m_y -= (m_y - m_newyLocation);
                    m_direction = 0;
                }
                else{
                    m_y -= m_move_speed;
                }
                break;
            case m_down:

                // image starts out facing forwards
                image[0] = Bitmap.createBitmap(m_spritesheet, 0, 9*m_height, m_width, m_height);
                image[1] = Bitmap.createBitmap(m_spritesheet, 0, 10*m_height, m_width, m_height);
                m_animation.setFrames(image);

                if(m_y + m_move_speed > m_newyLocation){
                    m_y += (m_newyLocation - m_y);
                    m_direction = 0;
                }
                else{
                    m_y += m_move_speed;
                }
                break;
            case m_still:
                m_moving = false;
                break;


        }

    }

    // return the heros needed exp
    public int getNeededExp(){return m_neededExp;}
    // returns the heros current exp
    public int getEXP(){return m_exp;}

    // adds exp to hero and lvl's up hero
    public void gainExp(int a_gainEXP){
        m_exp += a_gainEXP;
        if(m_exp >= m_neededExp){
            lvlUP();
        }
    }

    /**
     private void lvlUP()

     NAME

      lvlUP

     SYNOPSIS

     private void lvlUP()

     DESCRIPTION

        When hero gains enough exp this function resets the heros current exp to 0,
            increases the exp needed and strengthens the hero.

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     2:30pm 3/24/2017

     */
    private void lvlUP(){
        switch(m_type){
            // knight lvl up
            case 1:
                m_damage += 10;
                m_lvl += 1;
                m_neededExp += 10;
                m_exp = 0;
                break;
            // archer lvl up
            case 2:
                m_damage += 4;
                m_lvl += 1;
                m_exp = 0;
                m_neededExp += 10;
                switch(m_lvl){
                    case 3:
                        m_attack_speed -= 150;
                        break;
                    case 6:
                        m_attack_speed -= 150;
                        break;
                }

                break;
            // mage lvl up
            case 3:
                m_damage += 6;
                m_lvl += 1;
                m_exp = 0;
                m_neededExp += 10;
                break;
        }
    }

    // return hero damage
    public int getDamage(){return m_damage;}

    // return hero range
    public float getRange(){return m_range;}

    // return hero attack speed
    public double getAttack_speed(){return m_attack_speed;}

    // return hero type
    public int getType(){return m_type;}

    // returns true if hero is moving
    public boolean isMoving(){return m_moving;}

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

     2:30pm 3/24/2017

     */
    public void draw(Canvas canvas){

        try{
            canvas.drawBitmap(m_animation.getImage(),m_x,m_y,null);
        }catch(Exception e){}

    }

    // returns the lvl of the hero
    public int getLevel(){return m_lvl;}

}

package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.abs;


/**
 * Created by kevin on 12/29/2016.
 */

/**
 class MainThread

 NAME

 MainThread

 SYNOPSIS

 class MainThread
    Bitmap m_spritesheet -> hold the image being used
    double m_health -> monster current health
     double m_starthealth -> monster max/starting health
     int m_money -> money monster will give
     int m_walk_speed -> how fast monster will move on screen
    int m_gridx, m_gridy -> the x and y grid numbers for the 2D array of the room
     // these are changed depending on the direction
    int m_gridx_search , m_gridy_serach -> Is the direction that is being looked at 1 space ahead

     int [][] m_currentroom -> the current 2D array room that is being used to path the monsters

     Animation m_animation -> animation Object to animate the sprites
     int m_numFrames -> number of frames for the animation / image to cycle through
     // 0 is to right, 1 is to left, 2 is up, 3 is down
     int m_moveDirection -> the direction the monster is moving in

     // added to make bullet tracking easier
     int m_monsterID -> the id given to the monster when it is created

     // For the slow tower effect
     double m_slow_effect -> the amount the monster is being slowed by
     // slow effect should last for 1.5 seconds
     long m_slow_timer_count -> timer for the slow of the monster
     //gets monster back on a correct corse so its walking doesnt get messed up
     int m_back_on_course -> helps the monster stay on the path and not wonder off after/ durring slow



 DESCRIPTION

    Creates the monsters, gives them all of their stats, keeps them on the correct path, and animates them

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 12:00Am 12/29/2016

 */
public class Monster extends GameObject{
    private Bitmap m_spritesheet;
    private double m_health;
    private double m_starthealth;
    private int m_money;
    private int m_walk_speed;
    private int m_gridx, m_gridy;
    // these are changed depending on the direction
    private int m_gridx_search = 0, m_gridy_serach = 0;
    private int x_next = 0, y_next = 0;

    private int [][] m_currentroom;
    //private boolean playing;
    private Animation m_animation = new Animation();
    private int m_numFrames;
    // 0 is to right, 1 is to left, 2 is up, 3 is down
    private int m_moveDirection = -1;

    // added to make bullet tracking easier
    private int m_monsterID;

    // For the slow tower effect
    private double m_slow_effect = 0;
    // slow effect should last for 1.5 seconds
    private long m_slow_timer_count;
    //gets monster back on a correct corse so its walking doesnt get messed up
    private int m_back_on_course;


    /**
     public Monster(Bitmap a_res, int a_x, int a_y, int a_w, int a_h, int a_numFrames, int [][] a_currentroom, int a_monstertype, int a_monsterID, int a_hp_mult)

     NAME

     Monster

     SYNOPSIS

     public Monster(Bitmap a_res, int a_x, int a_y, int a_w, int a_h, int a_numFrames, int [][] a_currentroom, int a_monstertype, int a_monsterID, int a_hp_mult)

        Bitmap a_res -> where the image is stored for the monster
        int a_x -> x coord of the monster when it is created
        int a_y -> y coord of the monster when it is created
        int a_w -> width of the image
        int a_h -> height of the image
        int a_numFrames -> number of frames that will cycle through
        int [][] a_currentroom -> holds the room/map information to help track the monster to keep them on path
        int a_monstertype -> helps decide which type of monster will be made with corresponding image
        int a_monsterID -> number given to monster on creation
        int a_hp_mult -> is used when infinite mode is being played on to increase monster hp

     DESCRIPTION

        Constructor for the class which creates the specific type of monster chosen

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     12:00Am 12/29/2016

     */
    public Monster(Bitmap a_res, int a_x, int a_y, int a_w, int a_h, int a_numFrames,
                   int [][] a_currentroom, int a_monstertype, int a_monsterID, double a_hp_mult){

        m_x = a_x;
        m_y = a_y;
        m_currentroom = a_currentroom;

        m_width = a_w;
        m_height = a_h;
        m_numFrames = a_numFrames;
        m_monsterID = a_monsterID;



        Bitmap[] image = new Bitmap[m_numFrames];

        m_spritesheet = a_res;

        // starts by moving to the right
        m_moveDirection = 0;

        image[0] = Bitmap.createBitmap(m_spritesheet, 0, 0*m_height, m_width, m_height);
        image[1] = Bitmap.createBitmap(m_spritesheet, 0, 1*m_height, m_width, m_height);
        image[2] = Bitmap.createBitmap(m_spritesheet, 0, 0*m_height, m_width, m_height);
        image[3] = Bitmap.createBitmap(m_spritesheet, 0, 1*m_height, m_width, m_height);
        image[4] = Bitmap.createBitmap(m_spritesheet, 0, 0*m_height, m_width, m_height);
        image[5] = Bitmap.createBitmap(m_spritesheet, 0, 1*m_height, m_width, m_height);
        image[6] = Bitmap.createBitmap(m_spritesheet, 0, 0*m_height, m_width, m_height);
        image[7] = Bitmap.createBitmap(m_spritesheet, 0, 1*m_height, m_width, m_height);

        m_animation.setFrames(image);

        // each top of monster has different movespeed and hp
        // need to keep movement speed divisble by # that can divide 130 equally
        switch (a_monstertype){
            // red_dot monster
            case 1:
               //walk_speed = 13;
                m_walk_speed = 26;
                m_health = 10 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 1;
                m_money = 2;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            // green blob momster
            case 2:
                m_walk_speed = 10;
                //health = 15;
                m_health = 25 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 1;
                m_money = 2;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            // mouse monster
            case 3:
                m_walk_speed = 26;
                m_power = 1;
                //health = 7;
                m_health = 15 * a_hp_mult;
                m_starthealth = m_health;
                m_money = 2;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            // bannana monster
            case 4:
                m_walk_speed = 10;
                //health = 50;
                m_health = 100 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 1;
                m_money = 4;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            // lion monster
            case 5:
                m_walk_speed = 5;
                //health = 125;
                m_health = 275 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 2;
                m_money = 6;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            //theif monster // rare monster
            case 6:
                m_walk_speed = 26;
                m_health = 100 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 1;
                m_money = 12;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            // white knight
            case 7:
                m_walk_speed = 10;
               // health = 175;
                m_health =325 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 2;
                m_money = 6;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            // Blue knight
            case 8:
                m_walk_speed = 5;
                //health = 300;
                m_health = 550 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 3;
                m_money = 8;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            // bomb man
            case 9:
                m_walk_speed = 13;
                //health = 200;
                m_health = 375 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 2;
                m_money = 6;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            // fire spirite
            case 10:
                m_walk_speed = 13;
                //health = 250;
                m_health = 475 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 2;
                m_money = 8;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            // baby dragon
            case 11:
                m_walk_speed = 10;
                //health = 350;
                m_health = 900 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 2;
                m_money = 8;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            // silver dragon
            case 12:
                m_walk_speed = 5;
               // health = 600;
                m_health = 1200 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 4;
                m_money = 10;
                m_animation.setDelay(390 - m_walk_speed);
                break;
            // King of Beast
            case 13:
                m_walk_speed = 5;
                m_health = 2000 * a_hp_mult;
                m_starthealth = m_health;
                m_power = 5;
                m_money = 12;
                m_animation.setDelay(390 - m_walk_speed);
                break;



        }

        //startTime = System.nanoTime();
    }


    /**
     public void update()

     NAME

     update

     SYNOPSIS

     public void update()

     DESCRIPTION

        updates the monsters x and y location if they have to change direction and calls animation class in order to
            make the monster actually look like they are moving.  Also changes the image for different direction changes.

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     6:20pm 3/30/2017

     */
    public void update(){
        // gets the coordinate of the image in grid terms
        System.out.println("x: " + m_x);
        System.out.println("y " + m_y);
        m_gridx = getXcoord(m_x);
        m_gridy = getYcoord(m_y);

        //System.out.println("x:" + gridx);
        //System.out.println("y" + gridy);

        //System.out.println(gridx + " : " + gridy);

        switch(m_moveDirection){
            // move right
            case 0:
                m_gridx_search = 1;
                m_gridy_serach = 0;
                x_next = 0;
                break;
            // move left
            case 1:
                m_gridx_search = -1;
                m_gridy_serach = 0;
                break;
            // move up
            case 2:
                m_gridx_search = 0;
                m_gridy_serach = -1;
                // need to do this becuase it determins what square its in by the upper part
                // of its boddy, so as soon as uper hits the next square it thinks its in the next
                // square when it isnt yet
                //gridy += 1;
                break;
            // move down
            case 3:
                m_gridx_search = 0;
                m_gridy_serach = +1;
                break;


        }

        //System.out.println(currentroom[gridy + gridy_serach][gridx + gridx_search]);
        // means change direction
        if(m_currentroom[m_gridy + m_gridy_serach][m_gridx + m_gridx_search] != 1){

            int checkgridx = m_gridx;
            int checkgridy = m_gridy;

            // check upwards
            if(m_currentroom[checkgridy - 1][checkgridx] == 1 && m_moveDirection != 3){

                if(m_moveDirection != 2){
                    System.gc();
                    m_moveDirection = 2;

                    Bitmap[] image = new Bitmap[m_numFrames];

                    image[0] = Bitmap.createBitmap(m_spritesheet, 0, 4*m_height, m_width, m_height);
                    image[1] = Bitmap.createBitmap(m_spritesheet, 0, 5*m_height, m_width, m_height);
                    image[2] = Bitmap.createBitmap(m_spritesheet, 0, 4*m_height, m_width, m_height);
                    image[3] = Bitmap.createBitmap(m_spritesheet, 0, 5*m_height, m_width, m_height);
                    image[4] = Bitmap.createBitmap(m_spritesheet, 0, 4*m_height, m_width, m_height);
                    image[5] = Bitmap.createBitmap(m_spritesheet, 0, 5*m_height, m_width, m_height);
                    image[6] = Bitmap.createBitmap(m_spritesheet, 0, 4*m_height, m_width, m_height);
                    image[7] = Bitmap.createBitmap(m_spritesheet, 0, 5*m_height, m_width, m_height);

                    m_animation.setFrames(image);

                }
               // y-=m_walk_speed;
                if (m_slow_effect == 0) {
                    m_back_on_course = (m_y % m_walk_speed);

                    m_y -= m_walk_speed + m_back_on_course;
                }
                else{
                   // back_on_course = (y % (int)slow_effect);
                    m_y-= m_slow_effect;// + back_on_course;

                }

            }
            // check down
            else if(m_currentroom[checkgridy + 1][checkgridx] == 1 && m_moveDirection != 2){

                if(m_moveDirection != 3){
                    System.gc();
                    m_moveDirection = 3;

                    Bitmap[] image = new Bitmap[m_numFrames];

                    image[0] = Bitmap.createBitmap(m_spritesheet, 0, 6*m_height, m_width, m_height);
                    image[1] = Bitmap.createBitmap(m_spritesheet, 0, 7*m_height, m_width, m_height);
                    image[2] = Bitmap.createBitmap(m_spritesheet, 0, 6*m_height, m_width, m_height);
                    image[3] = Bitmap.createBitmap(m_spritesheet, 0, 7*m_height, m_width, m_height);
                    image[4] = Bitmap.createBitmap(m_spritesheet, 0, 6*m_height, m_width, m_height);
                    image[5] = Bitmap.createBitmap(m_spritesheet, 0, 7*m_height, m_width, m_height);
                    image[6] = Bitmap.createBitmap(m_spritesheet, 0, 6*m_height, m_width, m_height);
                    image[7] = Bitmap.createBitmap(m_spritesheet, 0, 7*m_height, m_width, m_height);

                    m_animation.setFrames(image);

                }
                //y+=m_walk_speed;
                if (m_slow_effect == 0) {
                    m_back_on_course = (m_y % m_walk_speed);

                    m_y += m_walk_speed + m_back_on_course;
                }
                else{
                   // m_back_on_course = (y % (int)m_slow_effect);
                    m_y+= m_slow_effect;// + m_back_on_course;
                }

            }
            // check right
            else if(m_currentroom[checkgridy][checkgridx + 1]  == 1 && m_moveDirection != 1){

                if(m_moveDirection != 0){
                    System.gc();
                    m_moveDirection = 0;

                    Bitmap[] image = new Bitmap[m_numFrames];

                    image[0] = Bitmap.createBitmap(m_spritesheet, 0, 0*m_height, m_width, m_height);
                    image[1] = Bitmap.createBitmap(m_spritesheet, 0, 1*m_height, m_width, m_height);
                    image[2] = Bitmap.createBitmap(m_spritesheet, 0, 0*m_height, m_width, m_height);
                    image[3] = Bitmap.createBitmap(m_spritesheet, 0, 1*m_height, m_width, m_height);
                    image[4] = Bitmap.createBitmap(m_spritesheet, 0, 0*m_height, m_width, m_height);
                    image[5] = Bitmap.createBitmap(m_spritesheet, 0, 1*m_height, m_width, m_height);
                    image[6] = Bitmap.createBitmap(m_spritesheet, 0, 0*m_height, m_width, m_height);
                    image[7] = Bitmap.createBitmap(m_spritesheet, 0, 1*m_height, m_width, m_height);

                    m_animation.setFrames(image);

                }
                //x+=m_walk_speed;
                // means slow effect is off
                if (m_slow_effect == 0) {
                    m_back_on_course = (m_x % m_walk_speed);

                    m_x += m_walk_speed + m_back_on_course;
                }
                else{
                    //m_back_on_course = (x % (int)m_slow_effect);
                    m_x+= m_slow_effect;// + m_back_on_course;
                }

            }
            // check left
            else if(m_currentroom[checkgridy][checkgridx - 1]  == 1 && m_moveDirection != 0){

                if(m_moveDirection != 1){
                    System.gc();
                    m_moveDirection = 1;

                    Bitmap[] image = new Bitmap[m_numFrames];

                    image[0] = Bitmap.createBitmap(m_spritesheet, 0, 2*m_height, m_width, m_height);
                    image[1] = Bitmap.createBitmap(m_spritesheet, 0, 3*m_height, m_width, m_height);
                    image[2] = Bitmap.createBitmap(m_spritesheet, 0, 2*m_height, m_width, m_height);
                    image[3] = Bitmap.createBitmap(m_spritesheet, 0, 3*m_height, m_width, m_height);
                    image[4] = Bitmap.createBitmap(m_spritesheet, 0, 2*m_height, m_width, m_height);
                    image[5] = Bitmap.createBitmap(m_spritesheet, 0, 3*m_height, m_width, m_height);
                    image[6] = Bitmap.createBitmap(m_spritesheet, 0, 2*m_height, m_width, m_height);
                    image[7] = Bitmap.createBitmap(m_spritesheet, 0, 3*m_height, m_width, m_height);

                    m_animation.setFrames(image);

                }
               // x-=m_walk_speed;
                if (m_slow_effect == 0) {
                    m_back_on_course = (m_x % m_walk_speed);

                    m_x -= m_walk_speed + m_back_on_course;
                }
                else{
                   // m_back_on_course = (x % (int)m_slow_effect);
                    m_x-= m_slow_effect; //+ m_back_on_course;

                }

            }

        }
        // didnt hit a wall
        // reason for m_back_on_course is so that 130 is always divisible by monster walk
        // this way they will never get too far off track and not follow the tracking system
        // on course gets the remainder from the location x or y, then adds it with the walkspeed
        // to keep
        // them on course

        else{
            no_wall_hit();
        }



            // resets slow effect back to 0 if slow effect wares off
        if(TimeUnit.MILLISECONDS.convert(System.nanoTime() - m_slow_timer_count,
                TimeUnit.NANOSECONDS) >= 2000 ){
            m_slow_effect = 0;
        }

        m_animation.update();



    }

    /**
     private void no_wall_hit()

     NAME

     no_wall_hit

     SYNOPSIS

     private void no_wall_hit()

     DESCRIPTION

        updates the monsters x and y coord if they monster does not have to change direction

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     6:20pm 3/30/2017

     */
    private void no_wall_hit(){

            switch(m_moveDirection){
                // move right
                case 0:
                    // means slow effect is off
                    if (m_slow_effect == 0) {

                        m_back_on_course = ((m_gridx + 1) * 130) - m_x;
                        if(m_back_on_course < m_walk_speed){
                            m_x += m_walk_speed - (m_walk_speed - m_back_on_course);
                        }
                        else{
                            //m_back_on_course = (130 % (int)m_slow_effect);
                            m_x+= m_walk_speed; //+ m_back_on_course;
                        }
                    }

                    // slow effect is on, need to track check to stop monster from turning around or going of their trail
                    else{
                        m_back_on_course = ((m_gridx + 1) * 130) - m_x;
                        if(m_back_on_course < m_slow_effect){
                            m_x += m_slow_effect - (m_slow_effect - m_back_on_course);
                        }
                        else{
                            //m_back_on_course = (130 % (int)m_slow_effect);
                            m_x+= m_slow_effect; //+ m_back_on_course;
                        }
                    }

                    break;
                // move left
                case 1:
                    // means slow effect is off
                    if (m_slow_effect == 0) {

                        m_back_on_course = m_x - ((m_gridx - 1) * 130);
                        if(m_back_on_course < m_walk_speed){
                            m_x-= m_walk_speed -(m_walk_speed - m_back_on_course);
                        }
                        else{
                            //m_back_on_course = (130 % (int)m_slow_effect);
                            m_x-=  m_walk_speed; //+ m_back_on_course;

                        }

                    }
                    // this is to make sure that right before it gets to the edge of a block it will be exactly divisible by 130
                    // so things it wont turn the wrong way
                    else{
                        m_back_on_course = m_x - ((m_gridx - 1) * 130);
                        if(m_back_on_course < m_slow_effect){
                            m_x-= m_slow_effect -(m_slow_effect - m_back_on_course);
                        }
                        else{
                            //m_back_on_course = (130 % (int)m_slow_effect);
                            m_x-=  m_slow_effect; //+ m_back_on_course;

                        }

                    }
                    //x-=m_walk_speed - m_slow_effect;
                    break;
                // move up
                case 2:
                    if (m_slow_effect == 0) {

                        m_back_on_course = m_y - ((m_gridy - 1) * 130);
                        if(m_back_on_course < m_walk_speed){
                            m_y-= m_walk_speed -(m_walk_speed - m_back_on_course);
                        }
                        else{
                            //m_back_on_course = (130 % (int)m_slow_effect);
                            m_y-=  m_walk_speed; //+ m_back_on_course;

                        }
                    }
                    // this is to make sure that right before it gets to the edge of a block it will be exactly divisible by 130
                    // so things it wont turn the wrong way
                    else{
                        m_back_on_course = m_y - ((m_gridy - 1) * 130);
                        if(m_back_on_course < m_slow_effect){
                            m_y-= m_slow_effect -(m_slow_effect - m_back_on_course);
                        }
                        else{
                            //m_back_on_course = (130 % (int)m_slow_effect);
                            m_y-=  m_slow_effect; //+ m_back_on_course;

                        }

                    }
                    //y-=m_walk_speed - m_slow_effect;
                    break;
                // move down
                case 3:
                    if (m_slow_effect == 0) {

                        m_back_on_course = ((m_gridy + 1) * 130) - m_y;
                        if(m_back_on_course < m_walk_speed){
                            m_y += m_walk_speed - (m_walk_speed - m_back_on_course);
                        }
                        else{
                            //m_back_on_course = (130 % (int)m_slow_effect);
                            m_y+= m_walk_speed; //+ m_back_on_course;
                        }
                    }
                    // this is to make sure that right before it gets to the edge of a block it will be exactly divisible by 130
                    // so things it wont turn the wrong way
                    else{
                        m_back_on_course = ((m_gridy + 1) * 130) - m_y;
                        if(m_back_on_course < m_slow_effect){
                            m_y += m_slow_effect - (m_slow_effect - m_back_on_course);
                        }
                        else{
                            //m_back_on_course = (130 % (int)m_slow_effect);
                            m_y+= m_slow_effect; //+ m_back_on_course;
                        }


                    }
                    //y+=m_walk_speed - m_slow_effect;
                    break;
            }

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
    public void draw(Canvas canvas){

        try{
            canvas.drawBitmap(m_animation.getImage(),m_x,m_y,null);
        }catch(Exception e){}

    }

    // divides x coord by 130 to fit the grid
    public int getXcoord(int x){
        //return Math.round(x / 120);
        // doing this bottom one because images are 130 by 130
        return Math.round(x / 130);
    }

    // divides y coord by 130 to fit the grid
    public int getYcoord(int y){
        //double newy = Math.round(y/119.5);
        // doing this bottom one because images are 130 by 130
        //Want to round number up when moving left or upwards
        double newy;
        if(m_moveDirection == 2){
            newy = Math.ceil(y/130.0);
        }
        else{
            newy = Math.round(y/130);
        }


        int thisy = (int) newy;



        return thisy;
    }

    // returns money monster gives
    public int GetMoney(){
        return m_money;
    }
    // return monster id number
    public int getID(){
        return m_monsterID;
    }
    // return monster health
    public double getHealth(){return m_health;}
    // returns monsters starting hp
    public double getStartHealth(){return m_starthealth; }
    // damages the monster when hit
    public void setHealth(int a_damage){m_health -= a_damage;}
    // returns monster id number
    public int getMonsterID(){return m_monsterID;}
    // slow efefects cuts the walkspeed in half
    public void setSlow_effect(){

        m_slow_effect = Math.floor(m_walk_speed / 2.0);
        m_slow_timer_count = System.nanoTime();
    }

}

package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.abs;


/**
 * Created by kevin on 12/29/2016.
 */

public class Monster extends GameObject{
    private Bitmap spritesheet;
    private int health;
    private int starthealth;
    private int money;
    private int walk_speed;
    private int gridx, gridy;
    // these are changed depending on the direction
    private int gridx_search = 0, gridy_serach = 0;
    private int x_next = 0, y_next = 0;

    private int [][] currentroom;
    //private boolean playing;
    private Animation animation = new Animation();
    private long startTime;
    private int numFrames;
    // 0 is to right, 1 is to left, 2 is up, 3 is down
    private int moveDirection = -1;

    // added to make bullet tracking easier
    private int monsterID;

    // For the slow tower effect
    private double slow_effect = 0;
    // slow effect should last for 1.5 seconds
    private long slow_timer_count;
    //gets monster back on a correct corse so its walking doesnt get messed up
    private int back_on_course;

    // x is x coord it will spawn, y is the y coord, w is width of sprit h is height
    public Monster(Bitmap res, int x, int y, int w, int h, int numFrames, int [][] currentroom, int monstertype, int monsterID, int hp_mult){

        super.x = x;
        super.y = y;
        this.currentroom = currentroom;

        width = w;
        height = h;
        this.numFrames = numFrames;
        this.monsterID = monsterID;



        Bitmap[] image = new Bitmap[numFrames];

        spritesheet = res;

        // starts by moving to the right
        moveDirection = 0;

        image[0] = Bitmap.createBitmap(spritesheet, 0, 0*height, width, height);
        image[1] = Bitmap.createBitmap(spritesheet, 0, 1*height, width, height);
        image[2] = Bitmap.createBitmap(spritesheet, 0, 0*height, width, height);
        image[3] = Bitmap.createBitmap(spritesheet, 0, 1*height, width, height);
        image[4] = Bitmap.createBitmap(spritesheet, 0, 0*height, width, height);
        image[5] = Bitmap.createBitmap(spritesheet, 0, 1*height, width, height);
        image[6] = Bitmap.createBitmap(spritesheet, 0, 0*height, width, height);
        image[7] = Bitmap.createBitmap(spritesheet, 0, 1*height, width, height);

        animation.setFrames(image);

        // each top of monster has different movespeed and hp
        // need to keep movement speed divisble by # that can divide 130 equally
        switch (monstertype){
            // red_dot monster
            case 1:
               //walk_speed = 13;
                walk_speed = 26;
                health = 10 * hp_mult;
                starthealth = health;
                super.power = 1;
                money = 2;
                animation.setDelay(390 - walk_speed);
                break;
            // green blob momster
            case 2:
                walk_speed = 10;
                //health = 15;
                health = 25 * hp_mult;
                starthealth = health;
                super.power = 1;
                money = 2;
                animation.setDelay(390 - walk_speed);
                break;
            // mouse monster
            case 3:
                walk_speed = 26;
                //health = 7;
                health = 15 * hp_mult;
                starthealth = health;
                money = 2;
                animation.setDelay(390 - walk_speed);
                break;
            // bannana monster
            case 4:
                walk_speed = 10;
                //health = 50;
                health = 100 * hp_mult;
                starthealth = health;
                super.power = 1;
                money = 4;
                animation.setDelay(390 - walk_speed);
                break;
            // lion monster
            case 5:
                walk_speed = 5;
                //health = 125;
                health = 275 * hp_mult;
                starthealth = health;
                super.power = 2;
                money = 6;
                animation.setDelay(390 - walk_speed);
                break;
            //theif monster // rare monster
            case 6:
                walk_speed = 26;
                health = 100 * hp_mult;
                starthealth = health;
                super.power = 1;
                money = 12;
                animation.setDelay(390 - walk_speed);
                break;
            // white knight
            case 7:
                walk_speed = 10;
               // health = 175;
                health =325 * hp_mult;
                starthealth = health;
                super.power = 2;
                money = 6;
                animation.setDelay(390 - walk_speed);
                break;
            // Blue knight
            case 8:
                walk_speed = 5;
                //health = 300;
                health = 550 * hp_mult;
                starthealth = health;
                super.power = 3;
                money = 8;
                animation.setDelay(390 - walk_speed);
                break;
            // bomb man
            case 9:
                walk_speed = 13;
                //health = 200;
                health = 375 * hp_mult;
                starthealth = health;
                super.power = 2;
                money = 6;
                animation.setDelay(390 - walk_speed);
                break;
            // fire spirite
            case 10:
                walk_speed = 13;
                //health = 250;
                health = 475 * hp_mult;
                starthealth = health;
                super.power = 2;
                money = 8;
                animation.setDelay(390 - walk_speed);
                break;
            // baby dragon
            case 11:
                walk_speed = 10;
                //health = 350;
                health = 900 * hp_mult;
                starthealth = health;
                super.power = 2;
                money = 8;
                animation.setDelay(390 - walk_speed);
                break;
            // silver dragon
            case 12:
                walk_speed = 5;
               // health = 600;
                health = 1200 * hp_mult;
                starthealth = health;
                super.power = 4;
                money = 10;
                animation.setDelay(390 - walk_speed);
                break;
            // King of Beast
            case 13:
                walk_speed = 5;
                //health = 850;
                health = 2000 * hp_mult;
                starthealth = health;
                super.power = 5;
                money = 12;
                animation.setDelay(390 - walk_speed);
                break;



        }

        //startTime = System.nanoTime();
    }


    // this would be where you would do different animation for
    // the different way they are walking as well as
    // change the direction they are walking
    public void update(){
        // gets the coordinate of the image in grid terms
        System.out.println("x: " + x);
        System.out.println("y " + y);
        gridx = getXcoord(x);
        gridy = getYcoord(y);

        //System.out.println("x:" + gridx);
        //System.out.println("y" + gridy);

        //System.out.println(gridx + " : " + gridy);

        switch(moveDirection){
            // move right
            case 0:
                gridx_search = 1;
                gridy_serach = 0;
                x_next = 0;
                break;
            // move left
            case 1:
                gridx_search = -1;
                gridy_serach = 0;
                break;
            // move up
            case 2:
                gridx_search = 0;
                gridy_serach = -1;
                // need to do this becuase it determins what square its in by the upper part
                // of its boddy, so as soon as uper hits the next square it thinks its in the next
                // square when it isnt yet
                //gridy += 1;
                break;
            // move down
            case 3:
                gridx_search = 0;
                gridy_serach = +1;
                break;


        }

        //System.out.println(currentroom[gridy + gridy_serach][gridx + gridx_search]);
        // means change direction
        if(currentroom[gridy + gridy_serach][gridx + gridx_search] != 1){

            int checkgridx = gridx;
            int checkgridy = gridy;

            // check upwards
            if(currentroom[checkgridy - 1][checkgridx] == 1 && moveDirection != 3){

                if(moveDirection != 2){
                    System.gc();
                    moveDirection = 2;

                    Bitmap[] image = new Bitmap[numFrames];

                    image[0] = Bitmap.createBitmap(spritesheet, 0, 4*height, width, height);
                    image[1] = Bitmap.createBitmap(spritesheet, 0, 5*height, width, height);
                    image[2] = Bitmap.createBitmap(spritesheet, 0, 4*height, width, height);
                    image[3] = Bitmap.createBitmap(spritesheet, 0, 5*height, width, height);
                    image[4] = Bitmap.createBitmap(spritesheet, 0, 4*height, width, height);
                    image[5] = Bitmap.createBitmap(spritesheet, 0, 5*height, width, height);
                    image[6] = Bitmap.createBitmap(spritesheet, 0, 4*height, width, height);
                    image[7] = Bitmap.createBitmap(spritesheet, 0, 5*height, width, height);

                    animation.setFrames(image);

                }
               // y-=walk_speed;
                if (slow_effect == 0) {
                    back_on_course = (y % walk_speed);

                    y -= walk_speed + back_on_course;
                }
                else{
                   // back_on_course = (y % (int)slow_effect);
                    y-= slow_effect;// + back_on_course;

                }

            }
            // check down
            else if(currentroom[checkgridy + 1][checkgridx] == 1 && moveDirection != 2){

                if(moveDirection != 3){
                    System.gc();
                    moveDirection = 3;

                    Bitmap[] image = new Bitmap[numFrames];

                    image[0] = Bitmap.createBitmap(spritesheet, 0, 6*height, width, height);
                    image[1] = Bitmap.createBitmap(spritesheet, 0, 7*height, width, height);
                    image[2] = Bitmap.createBitmap(spritesheet, 0, 6*height, width, height);
                    image[3] = Bitmap.createBitmap(spritesheet, 0, 7*height, width, height);
                    image[4] = Bitmap.createBitmap(spritesheet, 0, 6*height, width, height);
                    image[5] = Bitmap.createBitmap(spritesheet, 0, 7*height, width, height);
                    image[6] = Bitmap.createBitmap(spritesheet, 0, 6*height, width, height);
                    image[7] = Bitmap.createBitmap(spritesheet, 0, 7*height, width, height);

                    animation.setFrames(image);

                }
                //y+=walk_speed;
                if (slow_effect == 0) {
                    back_on_course = (y % walk_speed);

                    y += walk_speed + back_on_course;
                }
                else{
                   // back_on_course = (y % (int)slow_effect);
                    y+= slow_effect;// + back_on_course;
                }

            }
            // check right
            else if(currentroom[checkgridy][checkgridx + 1]  == 1 && moveDirection != 1){

                if(moveDirection != 0){
                    System.gc();
                    moveDirection = 0;

                    Bitmap[] image = new Bitmap[numFrames];

                    image[0] = Bitmap.createBitmap(spritesheet, 0, 0*height, width, height);
                    image[1] = Bitmap.createBitmap(spritesheet, 0, 1*height, width, height);
                    image[2] = Bitmap.createBitmap(spritesheet, 0, 0*height, width, height);
                    image[3] = Bitmap.createBitmap(spritesheet, 0, 1*height, width, height);
                    image[4] = Bitmap.createBitmap(spritesheet, 0, 0*height, width, height);
                    image[5] = Bitmap.createBitmap(spritesheet, 0, 1*height, width, height);
                    image[6] = Bitmap.createBitmap(spritesheet, 0, 0*height, width, height);
                    image[7] = Bitmap.createBitmap(spritesheet, 0, 1*height, width, height);

                    animation.setFrames(image);

                }
                //x+=walk_speed;
                // means slow effect is off
                if (slow_effect == 0) {
                    back_on_course = (x % walk_speed);

                    x += walk_speed + back_on_course;
                }
                else{
                    //back_on_course = (x % (int)slow_effect);
                    x+= slow_effect;// + back_on_course;
                }

            }
            // check left
            else if(currentroom[checkgridy][checkgridx - 1]  == 1 && moveDirection != 0){

                if(moveDirection != 1){
                    System.gc();
                    moveDirection = 1;

                    Bitmap[] image = new Bitmap[numFrames];

                    image[0] = Bitmap.createBitmap(spritesheet, 0, 2*height, width, height);
                    image[1] = Bitmap.createBitmap(spritesheet, 0, 3*height, width, height);
                    image[2] = Bitmap.createBitmap(spritesheet, 0, 2*height, width, height);
                    image[3] = Bitmap.createBitmap(spritesheet, 0, 3*height, width, height);
                    image[4] = Bitmap.createBitmap(spritesheet, 0, 2*height, width, height);
                    image[5] = Bitmap.createBitmap(spritesheet, 0, 3*height, width, height);
                    image[6] = Bitmap.createBitmap(spritesheet, 0, 2*height, width, height);
                    image[7] = Bitmap.createBitmap(spritesheet, 0, 3*height, width, height);

                    animation.setFrames(image);

                }
               // x-=walk_speed;
                if (slow_effect == 0) {
                    back_on_course = (x % walk_speed);

                    x -= walk_speed + back_on_course;
                }
                else{
                   // back_on_course = (x % (int)slow_effect);
                    x-= slow_effect; //+ back_on_course;

                }

            }

        }
        // didnt hit a wall
        // reason for back_on_course is so that 130 is always divisible by monster walk
        // this way they will never get too far off track and not follow the tracking system
        // on course gets the remainder from the location x or y, then adds it with the walkspeed to keep
        // them on course
        else{
            switch(moveDirection){
                // move right
                case 0:
                    // means slow effect is off
                    if (slow_effect == 0) {

                        back_on_course = ((gridx + 1) * 130) - x;
                        if(back_on_course < walk_speed){
                            x += walk_speed - (walk_speed - back_on_course);
                        }
                        else{
                            //back_on_course = (130 % (int)slow_effect);
                            x+= walk_speed; //+ back_on_course;
                        }
                    }

                    // slow effect is on, need to track check to stop monster from turning around or going of their trail
                    else{
                        back_on_course = ((gridx + 1) * 130) - x;
                        if(back_on_course < slow_effect){
                            x += slow_effect - (slow_effect - back_on_course);
                        }
                        else{
                            //back_on_course = (130 % (int)slow_effect);
                            x+= slow_effect; //+ back_on_course;
                        }
                    }

                    break;
                // move left
                case 1:
                    // means slow effect is off
                    if (slow_effect == 0) {

                        back_on_course = x - ((gridx - 1) * 130);
                        if(back_on_course < walk_speed){
                            x-= walk_speed -(walk_speed - back_on_course);
                        }
                        else{
                            //back_on_course = (130 % (int)slow_effect);
                            x-=  walk_speed; //+ back_on_course;

                        }

                    }
                    // this is to make sure that right before it gets to the edge of a block it will be exactly divisible by 130
                    // so things it wont turn the wrong way
                    else{
                        back_on_course = x - ((gridx - 1) * 130);
                        if(back_on_course < slow_effect){
                            x-= slow_effect -(slow_effect - back_on_course);
                        }
                        else{
                            //back_on_course = (130 % (int)slow_effect);
                            x-=  slow_effect; //+ back_on_course;

                        }

                    }
                    //x-=walk_speed - slow_effect;
                    break;
                // move up
                case 2:
                    if (slow_effect == 0) {

                        back_on_course = y - ((gridy - 1) * 130);
                        if(back_on_course < walk_speed){
                            y-= walk_speed -(walk_speed - back_on_course);
                        }
                        else{
                            //back_on_course = (130 % (int)slow_effect);
                            y-=  walk_speed; //+ back_on_course;

                        }
                    }
                    // this is to make sure that right before it gets to the edge of a block it will be exactly divisible by 130
                    // so things it wont turn the wrong way
                    else{
                        back_on_course = y - ((gridy - 1) * 130);
                        if(back_on_course < slow_effect){
                            y-= slow_effect -(slow_effect - back_on_course);
                        }
                        else{
                            //back_on_course = (130 % (int)slow_effect);
                            y-=  slow_effect; //+ back_on_course;

                        }

                    }
                    //y-=walk_speed - slow_effect;
                    break;
                // move down
                case 3:
                    if (slow_effect == 0) {

                        back_on_course = ((gridy + 1) * 130) - y;
                        if(back_on_course < walk_speed){
                            y += walk_speed - (walk_speed - back_on_course);
                        }
                        else{
                            //back_on_course = (130 % (int)slow_effect);
                            y+= walk_speed; //+ back_on_course;
                        }
                    }
                    // this is to make sure that right before it gets to the edge of a block it will be exactly divisible by 130
                    // so things it wont turn the wrong way
                    else{
                        back_on_course = ((gridy + 1) * 130) - y;
                        if(back_on_course < slow_effect){
                            y += slow_effect - (slow_effect - back_on_course);
                        }
                        else{
                            //back_on_course = (130 % (int)slow_effect);
                            y+= slow_effect; //+ back_on_course;
                        }


                    }
                    //y+=walk_speed - slow_effect;
                    break;
            }
        }

            // resets slow effect back to 0 if slow effect wares off
        if(TimeUnit.MILLISECONDS.convert(System.nanoTime() - slow_timer_count, TimeUnit.NANOSECONDS) >= 2000 ){
            slow_effect = 0;
        }

        animation.update();



    }

    public void draw(Canvas canvas){

        try{
            canvas.drawBitmap(animation.getImage(),x,y,null);
        }catch(Exception e){}

    }

    public int getXcoord(int x){
        //return Math.round(x / 120);
        // doing this bottom one because images are 130 by 130
        return Math.round(x / 130);
    }

    public int getYcoord(int y){
        //double newy = Math.round(y/119.5);
        // doing this bottom one because images are 130 by 130
        //Want to round number up when moving left or upwards
        double newy;
        if(moveDirection == 2){
            newy = Math.ceil(y/130.0);
        }
        else{
            newy = Math.round(y/130);
        }


        int thisy = (int) newy;



        return thisy;
    }

    public int GetMoney(){
        return money;
    }
    public int getID(){
        return monsterID;
    }
    public int getHealth(){return health;}
    public int getStartHealth(){return starthealth; }
    public void setHealth(int damage){health -= damage;}
    public int getMoveSpeed(){return  walk_speed;}

    // slow efefects cuts the walkspeed in half
    public void setSlow_effect(){

        slow_effect = Math.floor(walk_speed / 2.0);
        slow_timer_count = System.nanoTime();
    }

}

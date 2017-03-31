package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static java.lang.StrictMath.abs;

/**
 * Created by kevin on 3/24/2017.
 */

public class Hero extends GameObject {

    private Bitmap spritesheet;
    private Animation animation = new Animation();
    private boolean moving;
    private float range;
    private int damage;
    private double attack_speed;
    private int move_speed;
    private int newxLocation;
    private int newyLocation;
    // used to tell if the direction the hero was moving in is done so they can change direction to finish their movement
    // 0 is stay still, 1 is left, 2 is right, 3 is up, 4 is down;
    private int direction = 0;
    private static final int still = 0, left = 1, right = 2, up = 3, down = 4;
    private int type;
    private int exp;
    private int lvl;
    private int neededExp;


    Hero(Bitmap res, int x, int y, int hero_type){
        super.x = x;
        super.y = y;
        newxLocation = x;
        newyLocation = y;
        super.width = 128;
        super.height = 128;
        type = hero_type;

        // hero starts out standing still so not moving
        moving = false;

        // giongto cycle through 2 images
        Bitmap image[] = new Bitmap[2];
        spritesheet = res;

        // image starts out facing forwards
        image[0] = Bitmap.createBitmap(spritesheet, 0, 9*height, width, height);
        image[1] = Bitmap.createBitmap(spritesheet, 0, 9*height, width, height);

        //for(int i = 0; i < image.length; i++){
         //   image[i] = Bitmap.createBitmap(spritesheet, 0, i*height, width, height);
        //}

        animation.setFrames(image);
        animation.setDelay(360);

        switch (hero_type){
            // knight
            case 1:
                //(1 + .5) * 130
                range = 195;
                damage = 10;
                // miliseconds
                attack_speed = 500;
                move_speed = 30;
                lvl = 1;
                neededExp = 10 + (5 * (lvl - 1));
                exp = 0;
                animation.setDelay(390 - move_speed);
                break;
            // archer
            case 2:
                //(4.5 + .5)* 130
                range = 650;
                damage = 4;
                attack_speed = 1250;
                move_speed = 17;
                lvl = 1;
                neededExp = 10 + (5 * (lvl - 1));
                exp = 0;
                animation.setDelay(390 - move_speed);
                break;
            // wizard
            case 3:
                //(4 + .5) * 130
                range = 585;
                damage = 6;
                // miliseconds
                attack_speed = 1250;
                move_speed = 17;
                lvl = 1;
                neededExp = 10 + (5 * (lvl - 1));
                exp = 0;
                animation.setDelay(390 - move_speed);
                break;
        }

    }

    // saving the new coordinates toupdate to from onclick event
    public void move_to(int newx, int newy){
        newxLocation = (newx * 130);
        newyLocation = (newy * 130);
        direction = still;
        moving = true;
    }

    public void update(){

        // done if the hero has finished a direction or no move command already set
        if(direction == still){
            // if x is furthest directino away move that way first
            if((abs(newxLocation - x) > abs(newyLocation - y))){
                if(newxLocation - x < 0){
                    direction = left;
                    // function for switching direction
                    switchdirection(direction);
                }
                else{
                    direction = right;
                    // function for switching direction
                    switchdirection(direction);
                }
                moving = true;
            }
            // move in they y direction first
            else if((abs(newxLocation - x) < abs(newyLocation - y))){
                if(newyLocation - y < 0){
                    direction = up;
                    // function for switching direction
                    switchdirection(direction);
                }
                else{
                    direction = down;
                    // function for switching direction
                    switchdirection(direction);
                }
                moving = true;
            }
            // no comand/ finsihed moving
            else{
                direction = still;
                moving = true;
                // function for switching direction
                switchdirection(direction);
            }
        }
        // stay in the same direction
        else{
            moving = true;
            samedirection(direction);

        }




        animation.update();


    }

    private void samedirection(int direction){
        switch(direction){
            case left:

                // make sure hero doesnt over run anything
                if(x - move_speed < newxLocation){
                    x -= (x - newxLocation);
                    this.direction = 0;
                }
                else{
                    x -= move_speed;
                }

                break;
            case right:

                if(x + move_speed > newxLocation){
                    x += (newxLocation - x);
                    this.direction = 0;
                }
                else{
                    x += move_speed;
                }
                break;
            case up:

                if(y - move_speed < newyLocation){
                    y -= (y - newyLocation);
                    this.direction = 0;
                }
                else{
                    y -= move_speed;
                }
                break;
            case down:

                if(y + move_speed > newyLocation){
                    y += (newyLocation - y);
                    this.direction = 0;
                }
                else{
                    y += move_speed;
                }
                break;
            case still:
                moving = false;
                break;


        }
    }

    private void switchdirection(int direction){

        Bitmap[] image = new Bitmap[2];
        switch(direction){
            case left:
                // change the bitmap images
                image[0] = Bitmap.createBitmap(spritesheet, 0, 3*height, width, height);
                image[1] = Bitmap.createBitmap(spritesheet, 0, 4*height, width, height);
                animation.setFrames(image);
                // make sure hero doesnt over run anything
                if(x - move_speed < newxLocation){
                    x -= (x - newxLocation);
                    this.direction = 0;
                }
                else{
                    x -= move_speed;
                }

                break;
            case right:

                // change the bitmap images
                image[0] = Bitmap.createBitmap(spritesheet, 0, 0*height, width, height);
                image[1] = Bitmap.createBitmap(spritesheet, 0, 1*height, width, height);
                animation.setFrames(image);

                if(x + move_speed > newxLocation){
                    x += (newxLocation - x);
                    this.direction = 0;
                }
                else{
                    x += move_speed;
                }
                break;
            case up:

                // change the bitmap images
                image[0] = Bitmap.createBitmap(spritesheet, 0, 6*height, width, height);
                image[1] = Bitmap.createBitmap(spritesheet, 0, 7*height, width, height);
                animation.setFrames(image);

                if(y - move_speed < newyLocation){
                    y -= (y - newyLocation);
                    this.direction = 0;
                }
                else{
                    y -= move_speed;
                }
                break;
            case down:

                // image starts out facing forwards
                image[0] = Bitmap.createBitmap(spritesheet, 0, 9*height, width, height);
                image[1] = Bitmap.createBitmap(spritesheet, 0, 10*height, width, height);
                animation.setFrames(image);

                if(y + move_speed > newyLocation){
                    y += (newyLocation - y);
                    this.direction = 0;
                }
                else{
                    y += move_speed;
                }
                break;
            case still:
                moving = false;
                break;


        }

    }

    public int getNeededExp(){return neededExp;}
    public int getEXP(){return exp;}

    public void gainExp(int gainEXP){
        exp += gainEXP;
        if(exp >= neededExp){
            lvlUP();
        }
    }

    private void lvlUP(){
        switch(type){
            // knight lvl up
            case 1:
                damage += 10;
                lvl += 1;
                neededExp += 10;
                exp = 0;
                break;
            // archer lvl up
            case 2:
                damage += 4;
                lvl += 1;
                exp = 0;
                neededExp += 10;
                switch(lvl){
                    case 3:
                        attack_speed -= 150;
                        break;
                    case 6:
                        attack_speed -= 150;
                        break;
                }

                break;
            // mage lvl up
            case 3:
                damage += 6;
                lvl += 1;
                exp = 0;
                neededExp += 10;
                break;
        }
    }

    public int getDamage(){return damage;}

    public float getRange(){return range;}

    public double getAttack_speed(){return attack_speed;}

    public int getType(){return type;}

    public boolean isMoving(){return moving;}

    public void draw(Canvas canvas){

        try{
            canvas.drawBitmap(animation.getImage(),x,y,null);
        }catch(Exception e){}

    }

}

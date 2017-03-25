package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

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


    Hero(Bitmap res, int x, int y, int hero_type){
        super.x = x;
        super.y = y;
        super.width = 128;
        super.height = 128;

        // hero starts out standing still so not moving
        moving = false;

        // giongto cycle through 2 images
        Bitmap image[] = new Bitmap[2];
        spritesheet = res;

        // image starts out facing forwards
        image[0] = Bitmap.createBitmap(spritesheet, 0, 9*height, width, height);
        image[0] = Bitmap.createBitmap(spritesheet, 0, 9*height, width, height);

        //for(int i = 0; i < image.length; i++){
         //   image[i] = Bitmap.createBitmap(spritesheet, 0, i*height, width, height);
        //}

        animation.setFrames(image);
        animation.setDelay(360);

        switch (hero_type){
            // knight
            case 1:
                //1 * 130
                range = 130;
                damage = 10;
                // miliseconds
                attack_speed = 500;
                move_speed = 30;
                animation.setDelay(390 - move_speed);
                break;
            // archer
            case 2:
                //4.5 * 130
                range = 585;
                damage = 4;
                attack_speed = 1250;
                move_speed = 17;
                animation.setDelay(390 - move_speed);
                break;
            // wizard
            case 3:
                //1 * 130
                range = 1520;
                damage = 6;
                // miliseconds
                attack_speed = 1250;
                move_speed = 17;
                animation.setDelay(390 - move_speed);
                break;
        }

    }

    public void update(int x, int y){

    }

    public void draw(Canvas canvas){

        try{
            canvas.drawBitmap(animation.getImage(),x,y,null);
        }catch(Exception e){}

    }

    public float getRange(){
        return range;
    }
}

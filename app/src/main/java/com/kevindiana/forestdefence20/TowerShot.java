package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 2/12/2017.
 */

public class TowerShot extends GameObject {

    private Bitmap spritesheet;
    private Animation animation = new Animation();
    private int shot_Speed;
    private int numFrames;
    private int monsterID;
    // 1 is fire tower, 2 is double shot, 3 is ice tower
    private int type;

    public TowerShot(Bitmap res, int x, int y, int numFrames, int damage, int monsterID, int type){
        super.x = x;
        super.y = y;
        super.width = 125;
        super.height = 130;
        super.power = damage;
        //super.attack_speed = attackSpeed;
        shot_Speed = 35;
        this.type = type;
        this.monsterID = monsterID;
        this.numFrames = numFrames;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, 0, i*width, width, height);
        }

        // sets the animation
        animation.setFrames(image);
        // sets how fast the objects will move, lower number faster it moves
        // make this divisible by 130 becuase of the squares
        animation.setDelay(360 - shot_Speed);

    }

    public void update(int monster_x, int monster_y){

        // too keep bullet moving toward its target at all cost

        if(monster_x > x ){
            x += shot_Speed;
        }
        else{
            x -= shot_Speed;
        }

        if(monster_y > y){
            y += shot_Speed;
        }
        else{
            y -= shot_Speed;
        }

        animation.updateShot();

    }

    public void draw(Canvas canvas) {
        try{
            canvas.drawBitmap(animation.getImage(),x,y,null);
        }catch(Exception e){}
    }

    public int getMonsterID(){return monsterID;}
    public int getShotType(){return type;}
}

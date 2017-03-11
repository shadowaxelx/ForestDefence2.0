package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 3/10/2017.
 */

public class Start_Monster_Wave_Icon extends GameObject {
    private Bitmap spritesheet;
    private Animation animation = new Animation();

    Start_Monster_Wave_Icon(Bitmap res, int x, int y, int numframes ){
        super.x = x;
        super.y = y;
        super.width = 125;
        super.height = 128;

        Bitmap image[] = new Bitmap[numframes];
        spritesheet = res;

        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, 0, i*height, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(360);

    }

    public void update(){
        animation.update();
    }

    public void draw(Canvas canvas) {
        try{
            canvas.drawBitmap(animation.getImage(),x,y,null);
        }catch(Exception e){}
    }

}

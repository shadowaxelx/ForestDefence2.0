package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;

/**
 * Created by kevin on 12/29/2016.
 */

public class Animation {
    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    // some animations only want to play once
    private boolean playedOnce;

    public void setFrames(Bitmap[] frames){
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void setDelay(long d){delay = d;}

    // if you want to manually set frame
    public void setFrame(int i){currentFrame= i;}

    public void update(){
        long elapsed = (System.nanoTime()-startTime) / 1000000;
        //long elapsed = (System.nanoTime() - startTime) / 100000;

        // determins which image in array will be displaed
        if(elapsed>delay){
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }
    }

    public void updateShot(){
        //long elapsed = (System.nanoTime()-startTime) / 1000000;
        long elapsed = (System.nanoTime() - startTime) / 10000;

        // determins which image in array will be displaed
        if(elapsed>delay){
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }
    }

    public Bitmap getImage(){
        return frames[currentFrame];
    }
    public int getFrame(){return currentFrame;}
    public boolean playedOnce(){return playedOnce;}
}

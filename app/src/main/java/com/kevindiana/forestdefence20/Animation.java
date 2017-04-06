package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;

/**
 * Created by kevin on 12/29/2016.
 */

public class Animation {
    private Bitmap[] m_frames;
    private int m_currentFrame;
    private long m_startTime;
    private long m_delay;
    // some animations only want to play once
    private boolean m_playedOnce;

    public void setFrames(Bitmap[] a_frames){
        m_frames = a_frames;
        m_currentFrame = 0;
        m_startTime = System.nanoTime();
    }

    public void setDelay(long a_d){m_delay = a_d;}

    // if you want to manually set frame
    public void setFrame(int a_i){m_currentFrame= a_i;}

    public void update(){
        long elapsed = (System.nanoTime()-m_startTime) / 1000000;
        //long elapsed = (System.nanoTime() - startTime) / 100000;

        // determins which image in array will be displaed
        if(elapsed>m_delay){
            m_currentFrame++;
            m_startTime = System.nanoTime();
        }
        if(m_currentFrame == m_frames.length){
            m_currentFrame = 0;
            m_playedOnce = true;
        }
    }

    public void slowedupdate(){
        long elapsed = (System.nanoTime()-m_startTime) / 1000000;
        //long elapsed = (System.nanoTime() - startTime) / 100000;

        // determins which image in array will be displaed
        if(elapsed>m_delay + 15){
            m_currentFrame++;
            m_startTime = System.nanoTime();
        }
        if(m_currentFrame == m_frames.length){
            m_currentFrame = 0;
            m_playedOnce = true;
        }
    }

    public void updateShot(){
        long elapsed = (System.nanoTime() - m_startTime) / 1;

        // determins which image in array will be displaed
        if(elapsed>m_delay){
            m_currentFrame++;
            m_startTime = System.nanoTime();
        }
        if(m_currentFrame == m_frames.length){
            m_currentFrame = 0;
            m_playedOnce = true;
        }
    }

    public Bitmap getImage(){
        return m_frames[m_currentFrame];
    }
    public int getFrame(){return m_currentFrame;}
    public boolean playedOnce(){return m_playedOnce;}
}

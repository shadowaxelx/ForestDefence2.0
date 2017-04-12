package com.kevindiana.forestdefence20;

import android.graphics.Bitmap;

/**
 * Created by kevin on 12/29/2016.
 */

/**
 class Animation

 NAME

 class Animation

 SYNOPSIS

 Animation
 Bitmap[] m_frames -> array to hold bitmap images
 int m_currentFrame -> hold the current image number from the animation
 long m_startTime -> The time in which the animation start using System.nanoTime
 long m_delay -> the delay between the image switching
 boolean m_playedOnce -> some animations should only play once

 a_savedInstanceState -> The saved state of the application being passed in as a bundle


 DESCRIPTION

 This class is to animate images

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 3:20pm 2/3/2017

 */
public class Animation {
    private Bitmap[] m_frames;
    private int m_currentFrame;
    private long m_startTime;
    private long m_delay;
    // some animations only want to play once
    private boolean m_playedOnce;


    /**
     public void setFrames(Bitmap[] a_frames)

     NAME

     setFrames(Bitmap[] a_frames)

     SYNOPSIS

     public void setFrames(Bitmap[] a_frames)

     Bitmap[] a_frames -> the array of images wanting to be animated


     DESCRIPTION

     Sets the frames for the image, sets the current frame for the image,
     and sets the start time for the image

     RETURNS
     NA



     AUTHOR

     Kevin Diana

     DATE

     1:17pm 12/29/2017

     */
    public void setFrames(Bitmap[] a_frames){
        m_frames = a_frames;
        m_currentFrame = 0;
        m_startTime = System.nanoTime();
    }

    /**
     public void setDelay(long a_d)

     NAME

     setDelay(long a_d)

     SYNOPSIS

     public void setDelay(long a_d)

        long a_d -> the time for the delay on the switch between images


     DESCRIPTION

        Sets the Delay time for images

     RETURNS
     NA

     AUTHOR

     Kevin Diana

     DATE

     1:20pm 12/29/2017

     */
    public void setDelay(long a_d){m_delay = a_d;}

    // if you want to manually set frame
    public void setFrame(int a_i){m_currentFrame= a_i;}

    /**
     public void update()

     NAME

        update()

     SYNOPSIS

        public void update()

     DESCRIPTION

     Updates the frame to the next frame if elapsed time is greater than delay time

     RETURNS
     NA

     AUTHOR

     Kevin Diana

     DATE

     1:25pm 12/29/2017

     */
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

    /**
     public void updateShot()

     NAME

        updateShot()

     SYNOPSIS

        public void updateShot()

     DESCRIPTION

        Updates the images for tower shots since they move faster they animate faster

     RETURNS
     NA

     AUTHOR

     Kevin Diana

     DATE

     1:25pm 12/29/2017

     */
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

    // returns the current frame image
    public Bitmap getImage(){
        return m_frames[m_currentFrame];
    }

    // returns the current frame number
    public int getFrame(){return m_currentFrame;}

    // if you want to know if the image has already played once
    public boolean playedOnce(){return m_playedOnce;}
}

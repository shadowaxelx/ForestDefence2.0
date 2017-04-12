package com.kevindiana.forestdefence20;


import android.content.Intent;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 class MainThread

 NAME

    MainThread

 SYNOPSIS

    class MainThread
        int m_FPS- > the set Frames Per Second of the game
        double m_averageFPS -> average Frames Per Second of the game
        SurfaceHolder m_surfaceHolder -> content of the surface being passed in
        GamePanel m_gamePanel -> GamePanel object being created
        Canvas m_canvas -> the canvas being drawn to




 DESCRIPTION

    Continously goes through a loop to update canvas, draw canvas, and catching errors

 RETURNS

    NA

 AUTHOR

 Kevin Diana

 DATE

 12:00Am 12/26/2016

 */
public class MainThread extends Thread
{
    private int m_FPS = 30;
    private double m_averageFPS;
    private SurfaceHolder m_surfaceHolder;
    private GamePanel m_gamePanel;
    private boolean m_running;
    public static Canvas m_canvas;


    /**
     public MainThread(SurfaceHolder a_surfaceHolder, GamePanel a_gamePanel)

     NAME

     MainThread

     SYNOPSIS

     public MainThread(SurfaceHolder a_surfaceHolder, GamePanel a_gamePanel)
            SurfaceHolder a_surfaceHolder -> contents of the surface being passed in from another activity
            GamePanel a_gamePanel -> sending the gamepanel object being used in




     DESCRIPTION

        Constructor which sets the surfaceHolder and gamepanel being used to play the game on

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     12:00Am 12/26/2016

     */
    public MainThread(SurfaceHolder a_surfaceHolder, GamePanel a_gamePanel)
    {
        super();
        m_surfaceHolder = a_surfaceHolder;
        m_gamePanel = a_gamePanel;
    }

    /**
     public void run()

     NAME

     run

     SYNOPSIS

     public void run()

     DESCRIPTION

        Loop that continuously updates the surface, draws to the canvas, and catches errors while the
            game is playing

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     12:00Am 12/26/2016

     */
    @Override
    public void run()
    {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount =0;
        long targetTime = 1000/m_FPS;

        while(m_running) {
            startTime = System.nanoTime();
            m_canvas = null;

                //try locking the canvas for pixel editing
                try {
                    m_canvas = m_surfaceHolder.lockCanvas();
                    synchronized (m_surfaceHolder) {
                        m_gamePanel.update();
                        m_gamePanel.draw(m_canvas);
                    }
                } catch (Exception e) {
                    Log.e("MYAPP", "exception", e);
                }
                finally{
                    if(m_canvas!=null)
                    {
                        try {
                            m_surfaceHolder.unlockCanvasAndPost(m_canvas);
                        }
                        catch(Exception e){e.printStackTrace();}
                    }
                }

                timeMillis = (System.nanoTime() - startTime) / 1000000;
                waitTime = targetTime-timeMillis;

                try{
                    this.sleep(waitTime);
                }catch(Exception e){}

                totalTime += System.nanoTime()-startTime;
                frameCount++;
                if(frameCount == m_FPS)
                {
                    m_averageFPS = 1000/((totalTime/frameCount)/1000000);
                    frameCount =0;
                    totalTime = 0;
                    System.out.println(m_averageFPS);
                }

        }
    }

    // sets running to either true or false depending on what was passed in
    public void setRunning(boolean a_b)
    {
        m_running= a_b;
    }

}
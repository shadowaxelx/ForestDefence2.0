package com.kevindiana.forestdefence20;

import android.content.Context;
import android.content.Intent;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import static java.lang.Math.pow;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.abs;

/**
 Class GamePanel

 NAME

    GamePanel

 SYNOPSIS

     Room m_room -> Room object that holds image for the map
     int [][]m_currentroom -> 2D array that holds the monsterpath for the map

    ArrayList<Monster> m_monster -> ArrayList to hold the monster objects that will spawn
    long m_monsterStartTime -> variable to hold the start of the monster spawntime

    int m_wavenumber = 1 -> variable to keep track of what wave is being spawned
    MonsterWave m_monster_waves -> MonsterWave object to retreive which set of waves for what difficulty
    int [][]m_monster_wavesArray -> 2D array which holds every monsterwave for the map
    m_wavesalldone -> If every wave is complete the game is over so it will become false

    boolean m_game_done -> holds boolean for if the game is finished and should move to EndGame activity
    long m_fiveSeconds -> is the start timer for the 5 second period of time
    boolean m_end_timer -> when the game is over there is a 5 second delay before next activity is started
    m_Context -> holds this games content in order to move to the next activity

    ArrayList<Shop> m_shopitems -> ArrayList holding shoplist objects

    ArrayList<Tower> m_tower -> ArrayList holding tower objects


    ArrayList<TowerShot> m_towershot -> ArrayList holding towershot objects
    long m_shotTimer [] -> array which holds the timers before towers can shoot again


    ArrayList<MyPopUpMenu> m_mypopupmenus -> ArrayList holding MyPopUpMenu Objects
    boolean PopsUpIsUp -> boolean which is set to true if a popup is up/ false if one isnt

    boolean m_Error -> Set to true if an illegal move was made
    long m_errorStartTime -> timer for how long the error statement is shown

    boolean m_paused -> boolean to tell if the player has the game paused or not

    int m_spot_number -> Holds the spot number of the first click the player makes
    int m_second_press_Spot_num -> holds the number of the second press made by the player
    boolean m_is_it_first_click -> true if its the player first click, flase if it isnt
    int first_pressX = 0 ->  X coord of the players first click
    int first_pressY = 0 -> Y coord of the players first click


    Player player -> initializing a player object


    ArrayList<Start_Monster_Wave_Icon> mIcons -> Creating array list for start wave icon

 // # in ones place means difficulty, 1 easy, 2 normal, 3 hard
    String m_map_difficulty -> will contain the difficult for the map that will be parsed

    boolean m_infiity -> true if player has choosen infinite mode, false if they didnt
    ArrayList<Hero> m_hero -> ArrayList for Hero object
    boolean heroselected -> true if hero selected button was clicked, false if it is off
 // wait time for hero's next attack after the last one
    long attack_timer -> the time inbetween hero attacks
    ArrayList<HeroAttack> m_hero_attack ->ArrayList holding HeroAttack images
    boolean knight_attack_animation_start -> true if knight is attack
    ArrayList<Hero_Icon> m_h_icon -> Array list containing hero attack icon

    MainThread m_thread -> main thread for the entire game


 DESCRIPTION

    Activity that holds all of the information for the player to play the game

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 12:24pm 12/29/2017

 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    // the demenstion of the game ***********
    public static final int WIDTH = 2500;
    public static final int HEIGHT = 1500;

    //getting the room******************
    private Room m_room;
    private int [][]m_currentroom;

    // monster stuff***********************
    // vector that holds monsters
    private ArrayList<Monster> m_monster;
    // number for monster start time
    private long m_monsterStartTime;

    // monster wave stuff*************************
    // the number of waves that have passes, starts at 1
    private int m_wavenumber = 1;
    // creates monsterwave object
    private MonsterWave m_monster_waves;
    // the array that holds every monster wave for the map
    private int [][]m_monster_wavesArray;
    // if every way is done then the game is over
    private boolean m_wavesalldone = false;

    // end of game stuff ************
    // true when last monster has spawned and no more monsters are in the array
    private boolean m_game_done = false;
    private long m_fiveSeconds;
    private boolean m_end_timer = false;
    Context m_Context;

    // shop stuff********************
    private ArrayList<Shop> m_shopitems;

    // Tower stuff*****************
    //private Tower tower;
    private ArrayList<Tower> m_tower;

    // TowerShot stuff**************
    private ArrayList<TowerShot> m_towershot;
    // to have towers shoot at appropriate times
    private long m_shotTimer [];

    //PopupMenu Stuff*********
    private ArrayList<MyPopUpMenu> m_mypopupmenus;
    private boolean PopsUpIsUp = false;
    private boolean m_Error = false;
    private long m_errorStartTime;
    private boolean m_paused = false;

    // On click event stuff
    private int m_spot_number;
    private int m_second_press_Spot_num = -1;
    private boolean m_is_it_first_click = true;
    private int first_pressX = 0;
    private int first_pressY = 0;

    // Player class stuff
    Player player;

    //Start_Monster_wave_Icon stuff ****
    private ArrayList<Start_Monster_Wave_Icon> mIcons;

    // The player choosing the map and difficulty string 1 in tens place means map 1, 2 means map 2
    // # in ones place means difficulty, 1 easy, 2 normal, 3 hard
    private String m_map_difficulty;
    private boolean m_infiity = false;
    private ArrayList<Hero> m_hero;
    private boolean heroselected = false;
    // wait time for hero's next attack after the last one
    private long attack_timer;
    private ArrayList<HeroAttack> m_hero_attack;
    private boolean knight_attack_animation_start = false;
    private ArrayList<Hero_Icon> m_h_icon;

    // the main thread for the entire game
    private MainThread m_thread;



    /**
     public GamePanel(Context a_context, String a_map_difficulty)

NAME

     GamePanel(Context a_context, String a_map_difficulty)

SYNOPSIS

     public GamePanel(Context a_context, String a_map_difficulty)
        Context a_context -> the contexts of the activity
        String a_map_difficulty -> the string holding the map and dificulty to be later parsed

DESCRIPTION

        The Initializer for the GamePanel class

RETURNS

        NA

AUTHOR

        Kevin Diana

DATE

        1:07Am 12/29/2001

*/
    public GamePanel(Context a_context, String a_map_difficulty) {
        super(a_context);
        m_Context = a_context;
        m_map_difficulty = a_map_difficulty;

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        m_thread = new MainThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    // if i wanted to change the surface of the activity
    @Override
    public void surfaceChanged(SurfaceHolder a_holder, int a_format, int a_width, int a_height){}

    /**
     public void surfaceDestroyed(SurfaceHolder a_holder)

     NAME

     surfaceDestroyed

     SYNOPSIS

     public void surfaceDestroyed(SurfaceHolder a_holder)
        SurfaceHolder a_holder -> the content of the surface

     DESCRIPTION

     Deconstructor of the surface when the activity ends

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    @Override
    public void surfaceDestroyed(SurfaceHolder a_holder){
        boolean retry = true;
        while(retry)
        {
            try{m_thread.setRunning(false);
                m_thread.join();

            }catch(InterruptedException e){e.printStackTrace();}
            retry = false;
        }

    }

    /**
     public void surfaceCreated(SurfaceHolder a_holder)

     NAME

    surfaceCreated

     SYNOPSIS

     public void surfaceCreated(SurfaceHolder a_holder)
        SurfaceHolder a_holder -> content to create surface

     DESCRIPTION

    Creates a surface for the thread as soon as the activity is started up, it initializes
     all the starting objects like the map, difficulty, type of hero selected.

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    @Override
    public void surfaceCreated(SurfaceHolder a_holder){
        // parse string holding information with map number and difficulty into an int
        int map_difficulty = Integer.parseInt(m_map_difficulty);
        m_monster_waves = new MonsterWave();


        // Check to see if the game mode is infinity, map_difficulty > 100
        if(map_difficulty > 100){
            m_infiity = true;

            // create a new herro array list
            m_hero = new ArrayList<Hero>();
            m_hero_attack = new ArrayList<HeroAttack>();
            m_h_icon = new ArrayList<Hero_Icon>();
            m_h_icon.add(new Hero_Icon(BitmapFactory.decodeResource(getResources(), R.drawable.hero_icon), 2340, 1300));


            // seeing which map it will be played on
            switch((map_difficulty % 100)/10){

                // map 1
                case 1:
                    // setting the map the player will be using
                    m_room = new Room(BitmapFactory.decodeResource(getResources(), R.drawable.room1));
                    m_currentroom = m_room.getroom(1);
                    // 14 is infinite wave
                    m_monster_wavesArray = m_monster_waves.getwave(14);

                    // means hero is knight
                    if(map_difficulty < 200){
                        m_hero.add(new Hero(BitmapFactory.decodeResource(getResources(), R.drawable.hero_knight_sprite), 1170, 650, 1));
                    }
                    // means her is archer
                    else if(map_difficulty < 300){
                        m_hero.add(new Hero(BitmapFactory.decodeResource(getResources(), R.drawable.hero_archer_sprite), 1170, 650, 2));
                    }
                    // hero is wizard
                    else{
                        m_hero.add(new Hero(BitmapFactory.decodeResource(getResources(), R.drawable.hero_wizard_sprite), 1170, 650, 3));
                    }
                    break;
                // map 2
                case 2:
                    // setting the map the player will be using
                    m_room = new Room(BitmapFactory.decodeResource(getResources(), R.drawable.room2));
                    m_currentroom = m_room.getroom(2);
                    // 14 is infinite wave
                    m_monster_wavesArray = m_monster_waves.getwave(14);

                    // means hero is knight
                    if(map_difficulty < 200){
                        m_hero.add(new Hero(BitmapFactory.decodeResource(getResources(), R.drawable.hero_knight_sprite), 1170, 650, 1));
                    }
                    // means her is archer
                    else if(map_difficulty < 300){
                        m_hero.add(new Hero(BitmapFactory.decodeResource(getResources(), R.drawable.hero_archer_sprite), 1170, 650, 2));
                    }
                    // hero is wizard
                    else{
                        m_hero.add(new Hero(BitmapFactory.decodeResource(getResources(), R.drawable.hero_wizard_sprite), 1170, 650, 3));
                    }
                    break;
                // map 3
                case 3:
                    // setting the map the player will be using
                    m_room = new Room(BitmapFactory.decodeResource(getResources(), R.drawable.room3));
                    m_currentroom = m_room.getroom(3);
                    // 14 is infinite wave
                    m_monster_wavesArray = m_monster_waves.getwave(14);

                    // means hero is knight
                    if(map_difficulty < 200){
                        m_hero.add(new Hero(BitmapFactory.decodeResource(getResources(), R.drawable.hero_knight_sprite), 1170, 650, 1));
                    }
                    // means her is archer
                    else if(map_difficulty < 300){
                        m_hero.add(new Hero(BitmapFactory.decodeResource(getResources(), R.drawable.hero_archer_sprite), 1170, 650, 2));
                    }
                    // hero is wizard
                    else{
                        m_hero.add(new Hero(BitmapFactory.decodeResource(getResources(), R.drawable.hero_wizard_sprite), 1170, 650, 3));
                    }
                    break;
            }
        }

        else{
            // map 1
            if(map_difficulty < 20){

                // setting the map the player will be using
                m_room = new Room(BitmapFactory.decodeResource(getResources(), R.drawable.room1));
                m_currentroom = m_room.getroom(1);

                switch(map_difficulty % 10){
                    // easy difficulty wave
                    case 1:
                        m_monster_wavesArray = m_monster_waves.getwave(11);
                        break;
                    // normal diffuclty wave
                    case 2:
                        m_monster_wavesArray = m_monster_waves.getwave(12);
                        break;
                    // hard difficulty wave
                    case 3:
                        m_monster_wavesArray = m_monster_waves.getwave(13);
                        break;
                }
            }
            // map 2
            else if (map_difficulty < 30){
                // setting the map the player will be using
                m_room = new Room(BitmapFactory.decodeResource(getResources(), R.drawable.room2));
                m_currentroom = m_room.getroom(2);

                switch(map_difficulty % 10){
                    // easy difficulty wave
                    case 1:
                        m_monster_wavesArray = m_monster_waves.getwave(11);
                        break;
                    // normal diffuclty wave
                    case 2:
                        m_monster_wavesArray = m_monster_waves.getwave(12);
                        break;
                    // hard difficulty wave
                    case 3:
                        m_monster_wavesArray = m_monster_waves.getwave(13);
                        break;
                }
            }
            // map 3 and set infinity to true
            else{
                m_room = new Room(BitmapFactory.decodeResource(getResources(), R.drawable.room3));
                m_currentroom = m_room.getroom(3);

                switch(map_difficulty % 10){
                    // easy difficulty wave
                    case 1:
                        m_monster_wavesArray = m_monster_waves.getwave(11);
                        break;
                    // normal diffuclty wave
                    case 2:
                        m_monster_wavesArray = m_monster_waves.getwave(12);
                        break;
                    // hard difficulty wave
                    case 3:
                        m_monster_wavesArray = m_monster_waves.getwave(13);
                        break;

                }
            }
        }


        m_shopitems = new ArrayList<Shop>();
        m_shopitems.add(new Shop(BitmapFactory.decodeResource(getResources(), R.drawable.whiteflowertowershopbutton), 910, 1300, 1));
        m_shopitems.add(new Shop(BitmapFactory.decodeResource(getResources(), R.drawable.double_shot_tower_shop_buton), 1040, 1300, 2));
        m_shopitems.add(new Shop(BitmapFactory.decodeResource(getResources(), R.drawable.ice_tower_shop_button), 1170, 1300, 3));


        // creates array list for popup menue
        m_mypopupmenus = new ArrayList<MyPopUpMenu>();

        // creates array list for monsters
        m_monster = new ArrayList<Monster>();
        // starts calculating the time the first monster spawns
        m_monsterStartTime = System.nanoTime();

        mIcons = new ArrayList<Start_Monster_Wave_Icon>();
        mIcons.add(new Start_Monster_Wave_Icon(BitmapFactory.decodeResource(getResources(), R.drawable.start_wave), 0, 520, 5));

        m_tower = new ArrayList<Tower>();
        m_towershot = new ArrayList<TowerShot>();
        m_shotTimer = new long [250];

        player = new Player();

        //we can safely start the game loop
        m_thread.setRunning(true);
        m_thread.start();

    }


    //*********************************************************************
    /**
     public boolean onTouchEvent(MotionEvent a_event)

     NAME

     onTouchEvent

     SYNOPSIS

     public boolean onTouchEvent(MotionEvent a_event)
        MotionEvent a_event -> contains the data from the on touch event like x and y coords

     DESCRIPTION

        Reads the players touch actions on the screen to determain what should be done

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    @Override
    public boolean onTouchEvent(MotionEvent a_event) {
        int eventX = (int)a_event.getX();
        int eventY = (int)a_event.getY();


        eventX = getXcoord(eventX);
        eventY = getYcoord(eventY);

        System.out.println(eventX);
        System.out.println(eventY);

        //System.out.println("spot num X then Y" + currentroom[eventX][eventY]);
       // System.out.println("spot num Y then X" + currentroom[eventY][eventX]);
        //System.out.println("height of shop item");
       // System.out.println(shop.getHeight());
        //System.out.println("Width of shop item");
       // System.out.println(shop.getWidth());

        // this gets the spot number only for the second click and holds it
        if (!m_is_it_first_click){
            m_second_press_Spot_num = m_currentroom[eventY][eventX];

        }
        else{
            m_spot_number = m_currentroom[eventY][eventX];
            first_pressX = eventX;
            first_pressY = eventY;
        }

        // this is for being able to place towers down / upgrade them
        // this switch statement will check what number is in the grid the person clicked on
        // means its their second click
        // also move hero

        // if its true then it was second button press
        if(second_ButtonPress(eventX, eventY)){
            return super.onTouchEvent(a_event);
        }

        // this is for upgrade and selling towers i guess
        // also could be for clicking on monster/ tower and seeing their stats

        first_ButtonPress(eventX, eventY);

        return super.onTouchEvent(a_event);
    }

    /**
     public void first_ButtonPress(int a_eventX, int a_eventY)

     NAME

        first_ButtonPress

     SYNOPSIS

        public void first_ButtonPress(int a_eventX, int a_eventY)
            int a_eventX -> Holds the x coordinates of first button touch
            int a_eventY -> Holds the y coordinates of the first buttton touch

     DESCRIPTION

        Figures out what there first button press what and what should happen next

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    public void first_ButtonPress(int a_eventX, int a_eventY){
        if (m_is_it_first_click){

            // move hero until deslected
            if(heroselected && m_currentroom[a_eventY][a_eventX] != 7){
                if(m_infiity){
                    // if the person didnt reclick the button to deselect hero
                    if(m_currentroom[a_eventY][a_eventX] != 7){

                        // there is only 1 hero so
                        m_hero.get(0).move_to( a_eventX, a_eventY);
                    }
                }

            }
            else{
                switch(m_spot_number){

                    // means probably clicked on the start wave icon thing
                    case 1:
                        // if there are icons stored in the array, meaning that they are out
                        // and able to click on
                        if(mIcons.size() > 0){

                            for(Start_Monster_Wave_Icon i: mIcons){
                                if(getXcoord(i.getX()) == first_pressX &&
                                        getYcoord(i.getY()) == first_pressY){
                                    mIcons.remove(i);
                                }
                            }

                        }
                        break;
                    // tower type 1
                    case 2:
                        m_is_it_first_click = false;
                        break;
                    // tower type 2
                    case 3:
                        m_is_it_first_click = false;
                        break;
                    // tower type 3
                    case 4:
                        m_is_it_first_click = false;
                        break;
                    case 6:

                        // then add popup menu
                        m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                (getResources(), R.drawable.in_game_menu)));

                        m_paused = true;
                        // pause the thread
                        //thread.pause_unpause(true);



                        m_is_it_first_click = false;
                        break;
                    case 7:
                        // every time hero hits a monster gains experience
                        // show text showing the heros stats
                        if (m_infiity){
                            if(!heroselected){
                                heroselected = true;
                            }
                            else{
                                heroselected = false;
                            }

                            m_is_it_first_click = false;
                        }

                        break;
                    // for tower type 1
                    case 11:
                        // different pop ups for tower lvl 1-4;
                        switch(getTowerByCoord(first_pressX, first_pressY).getTowerLvl()){
                            case 1:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.pop_upmenue_sniper_lvl_1)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                            case 2:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.popup_menu_tower_type1_lvl2)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                            case 3:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.popup_menu_tower_type1_lvl3)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                            case 4:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.popup_menu_tower_type1_lvl4)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                        }

                        m_is_it_first_click = false;
                        break;
                    // for tower type 2
                    case 12:

                        // different pop ups for tower lvl 1-4;
                        switch(getTowerByCoord(first_pressX, first_pressY).getTowerLvl()){
                            case 1:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.popup_menu_tower_type2_lvl1)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                            case 2:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.popup_menu_tower_type2_lvl2)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                            case 3:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.popup_menu_tower_type2_lvl3)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                            case 4:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.popup_menu_tower_type2_lvl4)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                        }

                        m_is_it_first_click = false;
                        break;
                    // for tower type 3
                    case 13:

                        // different pop ups for tower lvl 1-4;
                        switch(getTowerByCoord(first_pressX, first_pressY).getTowerLvl()){
                            case 1:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.popup_menu_tower_type3_lvl1)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                            case 2:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.popup_menu_tower_type3_lvl2)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                            case 3:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.popup_menu_tower_type3_lvl3)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                            case 4:
                                m_mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.popup_menu_tower_type3_lvl4)));
                                // will only draw if popup is true dont think you need this
                                //PopsUpIsUp = true;

                                m_is_it_first_click = false;
                                break;
                        }

                        m_is_it_first_click = false;
                        break;
                }
            }

        }
    }

    /**
     public boolean second_ButtonPress(int a_eventX, int a_eventY)

     NAME

        second_ButtonPress

     SYNOPSIS

        public boolean second_ButtonPress(int a_eventX, int a_eventY)
            int a_eventX -> x coord for second button press
            int a_eventY -> y coord for second button press

     DESCRIPTION

        Figures out what to do on the players second button press if their first button press
        was sucessful

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    public boolean second_ButtonPress(int a_eventX, int a_eventY){
        if (!m_is_it_first_click){

            // if the popup menue has something in it
            if(m_mypopupmenus.size() > 0){
                // if the next click is the same location as the sell button
                if(m_paused){
                    // means they pressed the exit button
                    if((a_eventX == 9 || a_eventX == 10 || a_eventX == 11) && a_eventY == 4){
                        // go back to homescreen
                        //thread.pause_unpause(true);
                        Intent intent = new Intent(m_Context, HomeScreen.class);
                        m_Context.startActivity(intent);
                    }
                    // means they pressed the play button so continue on
                    else if((a_eventX == 9 || a_eventX == 10 || a_eventX == 11) && a_eventY == 5){
                        m_paused = false;
                        //thread.pause_unpause(false);
                        m_spot_number = -1;
                    }
                    else{

                    }

                }
                else if(a_eventX == 9 && a_eventY == 6){

                    for(Tower t: m_tower){
                        if(getXcoord(t.getX()) == first_pressX && getYcoord(t.getY()) ==
                                first_pressY){
                            player.AddMoney(t.getSell_cost());
                            m_tower.remove(t);
                            // then remove from the map itself
                            m_currentroom[first_pressY][first_pressX] = 0;

                            m_spot_number = -1;
                            break;
                        }
                        else{
                            m_spot_number = -1;
                        }
                    }
                }
                // upgrade tower
                else if(a_eventX == 12 && a_eventY == 6){
                    for(Tower t: m_tower){
                        if(getXcoord(t.getX()) == first_pressX && getYcoord(t.getY()) ==
                                first_pressY){
                            // check to see if player has the money to upgrade
                            if(player.GetMoney() >= t.getUpgrade_cost()){
                                player.SubMoney(t.getUpgrade_cost());
                                t.upgrade_tower();
                                m_spot_number = -1;
                            }
                            // you dont have the money give an error
                            else{
                                // make error true to start the popup timmer
                                m_Error = true;
                                m_errorStartTime = System.nanoTime();
                                m_spot_number = -1;
                            }

                        }
                        // didnt click on the upgrade button
                        else{
                            m_spot_number = -1;
                        }
                    }
                }
                else{
                    m_spot_number = -1;

                }
            }
            if(heroselected){
                if(m_infiity){
                    // if the person didnt reclick the button to deselect hero
                    if(m_currentroom[a_eventY][a_eventX] != 7){

                        // there is only 1 hero so
                        m_hero.get(0).move_to( a_eventX, a_eventY);
                    }
                    else{
                        heroselected = false;
                    }
                }
            }
            else{
                switch(m_spot_number){

                    // blank space
                    case 0:
                        //PopsUpIsUp = false;
                        break;
                    // monster path
                    case 1:
                        break;
                    // shop item 1
                    case 2:
                        // only add tower if the spot is a 0 meaning blank space
                        if(m_second_press_Spot_num == 0){

                            // check to see if player has enough money
                            if(player.GetMoney() >= 5 ){

                                m_tower.add(new Tower(BitmapFactory.decodeResource(getResources(),
                                        R.drawable.whiteflowertower), (a_eventX * 130),
                                        (a_eventY * 130), 1));

                                // this makes the empty spot a tower 1 spot now by putting
                                // replacing 0 with 11
                                m_currentroom[a_eventY][a_eventX] = 11;

                                // reset first button click
                                m_spot_number = -1;

                                player.SubMoney(5);

                            }
                            // else create an error message for player for insuficent gold or
                            // cant put on thatspot
                            else{

                                // make error true to start the popup timmer
                                m_Error = true;
                                m_errorStartTime = System.nanoTime();
                            }

                        }


                        m_is_it_first_click = true;
                        break;
                    // shop item 2
                    case 3:

                        // only add tower if the spot is a 0 meaning blank space
                        if(m_second_press_Spot_num == 0){

                            // check to see if player has enough money
                            if(player.GetMoney() >= 15 ){

                                m_tower.add(new Tower(BitmapFactory.decodeResource(getResources(),
                                        R.drawable.double_shot_tower), (a_eventX * 130),
                                        (a_eventY * 130), 2));

                                // this makes the empty spot a tower 2 spot now by putting
                                // replacing 0 with 11
                                m_currentroom[a_eventY][a_eventX] = 12;

                                // reset first button click
                                m_spot_number = -1;

                                player.SubMoney(15);

                            }
                            // else create an error message for player for insuficent
                            // gold or cant put on thatspot
                            else{

                                // make error true to start the popup timmer
                                m_Error = true;
                                m_errorStartTime = System.nanoTime();
                            }

                        }


                        m_is_it_first_click = true;
                        break;
                    // shop item 3
                    case 4:

                        // only add tower if the spot is a 0 meaning blank space
                        if(m_second_press_Spot_num == 0){

                            // check to see if player has enough money
                            if(player.GetMoney() >= 25 ){

                                m_tower.add(new Tower(BitmapFactory.decodeResource(getResources(),
                                        R.drawable.ice_tower), (a_eventX * 130),
                                        (a_eventY * 130), 3));

                                // this makes the empty spot a tower 2 spot now by
                                // putting replacing 0 with 11
                                m_currentroom[a_eventY][a_eventX] = 13;

                                // reset first button click
                                m_spot_number = -1;

                                player.SubMoney(25);

                            }
                            // else create an error message for player for insuficent gold
                            // or cant put on thatspot
                            else{

                                // make error true to start the popup timmer
                                m_Error = true;
                                m_errorStartTime = System.nanoTime();
                            }

                        }

                        m_is_it_first_click = true;

                        break;
                    // shop item 4
                    case 5:
                        break;
                    // hero movement
                    case 7:
                        heroselected = false;
                        m_is_it_first_click = true;

                        break;
                    // tower 1
                    case 11:
                        break;
                    // tower 2
                    case 12:
                        break;
                    // tower 3
                    case 13:
                        break;


                }
            }



            // adds everything that was in the temp into the actual array
            //tower.addAll(ttemp);

            for(int i = 0; i <m_mypopupmenus.size(); i++){

                // remove monster when it gets off screen, should also deplete hp
                m_mypopupmenus.remove(i);
                //System.out.println("Removing monster");

            }

            m_is_it_first_click = true;

            return true;
            //return super.onTouchEvent(event);
        }
        else{
            return false;
        }
    }
    //**********************************************************************

    //**********************************************************************
    /**
     public void update()

     NAME

     update

     SYNOPSIS

     public void update()

     DESCRIPTION

        Updates the images and other objects of the game like if a monster dies, to make the monster
     move, to make the hero move, towers attack, towershots moving

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    public void update() {
        if(!m_paused){
            m_room.update();

            // if player has less then 0 health he is dead
            if (player.GetHealth() <= 0){
                Intent intent = new Intent("com.kevindiana.forestdefence20.End_Of_Game");
                intent.putExtra("Health", "-1");
                m_Context.startActivity(intent);
            }

            // if game_done is true and there is nothing in monster array wait
            // 5 seconds then go to end screen
            // set timer and start the 5 second wait
            if(m_game_done && m_monster.size() == 0 && !m_end_timer){
                m_fiveSeconds = System.nanoTime();
                m_end_timer = true;
            }
            // if all the requirements are ment go to the end screen
            if(m_game_done && m_monster.size() == 0 && m_end_timer){
                if(TimeUnit.SECONDS.convert(System.nanoTime() - m_fiveSeconds,
                        TimeUnit.NANOSECONDS) >= 5 ){

                    //Intent intent = new Intent("com.kevindiana.forestdefence20.End_Of_Game");
                    Intent intent = new Intent("com.kevindiana.forestdefence20.End_Of_Game");
                    intent.putExtra("Health", Integer.toBinaryString(player.GetHealth()));
                    m_Context.startActivity(intent);

                }
            }

            // adding the monster on timer
            long monsterElapsed = (System.nanoTime()-m_monsterStartTime) / 1000000;

            //if(!wavesalldone){
            // if (!nextWaveCountdown){
            if(mIcons.size() == 0){
                if(monsterElapsed >(875)){

                    //if(!nextWaveCountdown){
                    if (!m_wavesalldone){


                        //System.out.println("making monster!!");
                        if(!addmonster(m_wavenumber - 1)){

                            m_wavenumber++;
                            knight_attack_animation_start = false;
                            // next wave will start so start countdown from 8 seconds
                            //nextWaveCountdown = true;
                            //countdown_8sec = System.nanoTime();

                            if (m_infiity){
                                if(m_wavenumber >= 13){
                                    m_monster_wavesArray = m_monster_waves.infinite();
                                }

                            }
                                mIcons.add(new Start_Monster_Wave_Icon(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.start_wave), 0, 520, 5));

                        }
                    }

                    // reset timer/ wave
                    m_monsterStartTime = System.nanoTime();
                }
            }
            // }
        /*
        else{
                //countdown_8sec -= 1;
                long timeElapsed = (System.nanoTime() - countdown_8sec);
                if(timeElapsed / 1000000 >= 8000){
                    nextWaveCountdown = false;
                }
           // System.out.println("Time Elapsed");
            //System.out.println(timeElapsed);
            //System.out.println(timeElapsed / 1000000);
        }
        */

            // tower attack animation and update stuff
            tower_Attack();

            if(m_infiity){
                // This is for hero attack aimation stuff
                    hero_Attack();

            }


            //update the monster start wave icon
            for(int i = 0; i <mIcons.size(); i++){
                mIcons.get(i).update();
            }

            // update hero
            if(m_infiity){
                for(int i = 0; i <m_hero.size(); i++){
                    m_hero.get(i).update();
                }
            }


            // update the monsters
            for(int i = 0; i < m_monster.size(); i++){

                // update monster
                m_monster.get(i).update();

                // remove monster when it gets off screen, should also deplete hp
                //if(monster.get(i).getX()<-100){
                if(m_monster.get(i).getX() > (WIDTH)){

                    // removes other tower shots
                    for(int y = 0; y < m_towershot.size(); y++){


                        if(i == m_towershot.get(y).getMonsterID()){

                            m_towershot.remove(y);
                        }

                    }

                    player.SetHealth(m_monster.get(i).getPower());

                    m_monster.remove(i);
                    System.out.println("Removing monster");
                    break;
                }
            }
        }




    }

    /**
     public void hero_Attack()

     NAME

     hero_Attack

     SYNOPSIS

     public void hero_Attack()

     DESCRIPTION

     updates the Heros Attack and determins when they can attack next

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    // checks to see if the hero needs to attack or not
    // basically same as tower shot function
    public void hero_Attack(){
        // gets hero x and y cords

            int hero_x = m_hero.get(0).getX();
            int hero_y = m_hero.get(0).getY();
        if (!m_hero.get(0).isMoving()){

            for(int i = 0; i < m_monster.size(); i++){

                // gets monster x and y coords
                int monster_x = m_monster.get(i).getX();
                int monster_y = m_monster.get(i).getY();

                if(attack_timer == 0){
                    if(abs(monster_x - hero_x) + abs(monster_y - hero_y) <=
                            m_hero.get(0).getRange() ){

                        switch(m_hero.get(0).getType()){
                            // knight
                            case 1:
                                // will jsut draw his attack on canvas using circles
                                knight_attack_animation_start = true;
                                attack_timer = System.nanoTime();

                                //knights attack is different so gatta do this
                                getKnightsdamage(hero_x, hero_y);
                                break;
                            // archer
                            case 2:
                                m_hero_attack.add(new HeroAttack(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.attack_archer), hero_x, hero_y,
                                        2, m_hero.get(0).getDamage(), m_monster.get(i).getID(), 2));
                                System.out.println("Creating Heroattack: " + i);
                                attack_timer = System.nanoTime();
                                break;
                            // mage
                            case 3:
                                m_hero_attack.add(new HeroAttack(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.attack_wizard), hero_x, hero_y,
                                        2,  m_hero.get(0).getDamage(), m_monster.get(i).getID(),3));
                                System.out.println("Creating Heroattack: " + i);
                                attack_timer = System.nanoTime();
                                break;
                        }

                    }

                }
                else{

                    if(TimeUnit.MILLISECONDS.convert(System.nanoTime() - attack_timer,
                            TimeUnit.NANOSECONDS) >= m_hero.get(0).getAttack_speed()){
                        attack_timer = 0;
                    }
                }


            }
            if(TimeUnit.MILLISECONDS.convert(System.nanoTime() - attack_timer, TimeUnit.NANOSECONDS)
                    >= m_hero.get(0).getAttack_speed()){
                attack_timer = 0;
            }

        }
        else{
            attack_timer = 0;
            knight_attack_animation_start = false;
        }



        // now update the attack and store monster and attack to be deleted later
        ArrayList<Monster> mtemplist = new ArrayList<Monster>();
        ArrayList<HeroAttack> hatemplist = new ArrayList<HeroAttack>();
        ArrayList<TowerShot> tstemplist = new ArrayList<TowerShot>();


        for(int i = 0; i < m_hero_attack.size(); i++){

            for (int j = 0; j < m_monster.size(); j++){

                if(m_hero_attack.get(i).getMonsterID() == m_monster.get(j).getMonsterID()){
                    int monster_x = m_monster.get(j).getX();
                    int monster_y = m_monster.get(j).getY();

                    m_hero_attack.get(i).update(monster_x, monster_y);

                    // checking to see if attack hit
                    if(collision(m_hero_attack.get(i), m_monster.get(j))){

                        // sets subtracts bullter damage to monster health
                        m_monster.get(j).setHealth(m_hero_attack.get(i).getPower());

                        // checks the monster hp if it is less then or = 0 get rid of it
                        // along with all shots moving to that target
                        if(m_monster.get(j).getHealth() <= 0){

                            // gain 2 exp for monster kill
                            m_hero.get(0).gainExp(2);

                            player.AddMoney(m_monster.get(j).GetMoney());

                            // removes other tower shots
                            for(int y = 0; y < m_hero_attack.size(); y++){

                                // this is so the original shot isnt removed before checking
                                // all shots
                                if(y != i){
                                    if(m_hero_attack.get(y).getMonsterID() ==
                                            m_hero_attack.get(i).getMonsterID()){

                                        //towershot.remove(y);
                                        hatemplist.add(m_hero_attack.get(y));
                                        System.out.println("Removing tower shot: " + y);

                                    }
                                }

                            }

                            // removes other tower shots
                            for(int y = 0; y < m_towershot.size(); y++){

                                // this is so the original shot isnt removed before
                                // checking all shots
                                    if(m_towershot.get(y).getMonsterID() ==
                                            m_hero_attack.get(i).getMonsterID()){

                                        //towershot.remove(y);
                                        tstemplist.add(m_towershot.get(y));
                                        System.out.println("Removing tower shot: " + y);

                                    }


                            }

                            mtemplist.add(m_monster.get(j));
                            //monster.remove(k);

                            // remove this last becuase you still need its components before hand
                            hatemplist.add(m_hero_attack.get(i));
                            //towershot.remove(i);
                            //System.out.println("Removing tower shot: " + i);




                        }
                        else{

                            // gain 1 exp for monster hit;
                            m_hero.get(0).gainExp(1);
                            // remove this last becuase you still need its components before hand
                            hatemplist.add(m_hero_attack.get(i));
                            //towershot.remove(i);
                            System.out.println("Removing tower shot: " + i);
                        }
                    }
                }

            }
        }

        // lastly remove everthing that was in the temp lists from the real lists
        // remove all tower shots now
        m_hero_attack.removeAll(hatemplist);

        // remove all shots in monster temp list
        m_monster.removeAll(mtemplist);

        m_towershot.removeAll(tstemplist);


    }

    /**
     public void  getKnightsdamage(int a_hero_x, int a_hero_y)

     NAME

     getKnightsdamage

     SYNOPSIS

        public void  getKnightsdamage(int a_hero_x, int a_hero_y)
            int a_hero_x -> x coord if the knight hero was chosen
            int a_her_y -> y coord if the knight hero was chosen

     DESCRIPTION

        Updates the night attack animation and determins when he can attack next

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    public void  getKnightsdamage(int a_hero_x, int a_hero_y){

        // now update the attack and store monster and attack to be deleted later
        ArrayList<Monster> mtemplist = new ArrayList<Monster>();
        ArrayList<TowerShot> tstemplist = new ArrayList<TowerShot>();

        for(int i = 0; i < m_monster.size(); i++){
            int m_monster_x = m_monster.get(i).getX();
            int m_monster_y = m_monster.get(i).getY();

            // m_monster is in range
            if(abs(m_monster_x - a_hero_x) + abs(m_monster_y - a_hero_y) <=
                    m_hero.get(0).getRange() ){

                // sets subtracts bullter damage to m_monster health
                m_monster.get(i).setHealth(m_hero.get(0).getDamage());

                if(m_monster.get(i).getHealth() <= 0){

                    // gain 2 exp for m_monster kill
                    m_hero.get(0).gainExp(2);

                    player.AddMoney(m_monster.get(i).GetMoney());

                    // removes other tower shots
                    for(int y = 0; y < m_towershot.size(); y++){

                        // this is so the original shot isnt removed before checking all shots

                            if(m_towershot.get(y).getMonsterID() == m_monster.get(i).
                                    getMonsterID()){

                                //towershot.remove(y);
                                tstemplist.add(m_towershot.get(y));
                                System.out.println("Removing tower shot: " + y);

                            }

                    }

                    mtemplist.add(m_monster.get(i));
                    //monster.remove(k);

                }
                else{
                    // gain 1 exp for monster hit;
                    m_hero.get(0).gainExp(1);
                }
            }

        }

        // remove all shots in monster temp list
        m_monster.removeAll(mtemplist);

        // remove tower shots
        m_towershot.removeAll(tstemplist);
    }

    /**
     public void tower_Attack()

     NAME

     tower_Attack

     SYNOPSIS

     public void tower_Attack()

     DESCRIPTION

     updates the towershots that the towers shoot and determins when the towers can attack again

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    public void tower_Attack(){
        // This is where we will have to check to see if the tower will shoot..
        // probably make this into a seperate function with everything else
        // need to check every monster and see if it is within any tower range
        for(int i = 0; i < m_tower.size(); i++){
            int tower_x = m_tower.get(i).getX();
            int tower_y = m_tower.get(i).getY();
            double tower_range = m_tower.get(i).getRange();
            int tower_type = m_tower.get(i).getTowerType();


            // searching for monster in range
            for(int j = 0; j < m_monster.size(); j++){
                int monster_x = m_monster.get(j).getX();
                int monster_y = m_monster.get(j).getY();


                //long timeElapsed = (System.nanoTime() - countdown_8sec);
                // checks to see if the tower has already shot something 0 means it has not
                if (m_shotTimer[i] == 0){

                    switch(tower_type){
                        // sniper tower
                        case 1:
                            // means the shot is in range
                            if(abs(monster_x - tower_x) + abs(monster_y - tower_y) <= tower_range ){
                                m_towershot.add(new TowerShot(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.fire_flower_shot),
                                        m_tower.get(i).getX(), m_tower.get(i).getY(), 2,
                                        m_tower.get(i).getPower(), m_monster.get(j).getID(), 1));
                                System.out.println("Creating tower shot: " + i);
                                // break afer wards for first monster


                                m_shotTimer[i] = System.nanoTime();
                                break;
                            }
                            break;
                        // double shot tower
                        case 2:

                            if(abs(monster_x - tower_x) + abs(monster_y - tower_y) <= tower_range){
                                m_towershot.add(new TowerShot(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.double_shot_tower_projectile),
                                        m_tower.get(i).getX(), m_tower.get(i).getY(), 2,
                                        m_tower.get(i).getPower(), m_monster.get(j).getID(), 2));

                                // THen check to see if a second monsterr can be shot at as well

                                for(int k = 0; k < m_monster.size(); k++){

                                    // This makes sure we arent going to shoot at the same
                                    // monster the tower already has targeted
                                    if(m_monster.get(k).getID() != m_monster.get(j).getID()) {
                                        int monster2_x = m_monster.get(k).getX();
                                        int monster2_y = m_monster.get(k).getY();

                                        if(abs(monster2_x - tower_x) + abs(monster2_y - tower_y)
                                                <= tower_range){
                                            m_towershot.add(new TowerShot(BitmapFactory.
                                                    decodeResource(getResources(),
                                                            R.drawable.double_shot_tower_projectile)
                                                    , m_tower.get(i).getX(), m_tower.get(i).getY(),
                                                    2, m_tower.get(i).getPower(),
                                                    m_monster.get(k).getID(), 2));

                                            m_shotTimer[i] = System.nanoTime();
                                            break;
                                        }
                                    }
                                }
                                m_shotTimer[i] = System.nanoTime();
                                break;
                            }
                            break;
                        // slow tower
                        case 3:

                            // means the shot is in range
                            if(abs(monster_x - tower_x) + abs(monster_y - tower_y) <= tower_range ){
                                m_towershot.add(new TowerShot(BitmapFactory.decodeResource
                                        (getResources(), R.drawable.ice_towr_projectile),
                                        m_tower.get(i).getX(), m_tower.get(i).getY(), 3,
                                        m_tower.get(i).getPower(), m_monster.get(j).getID(), 3));
                                System.out.println("Creating tower shot: " + i);
                                // break afer wards for first monster


                                m_shotTimer[i] = System.nanoTime();
                                break;
                            }

                            break;

                    }

                }
                // check to see if the tower has already waited for its attack speed to shoot again
                else{

                    if(TimeUnit.MILLISECONDS.convert(System.nanoTime() - m_shotTimer[i],
                            TimeUnit.NANOSECONDS) >= m_tower.get(i).getAttackSpeed() ){
                        m_shotTimer[i] = 0;
                    }
                }

            }
        }

        // temporary array to hold shots that will be deleated later
        ArrayList<Monster> mtemplist = new ArrayList<Monster>();
        ArrayList<TowerShot> tstemplist = new ArrayList<TowerShot>();
        ArrayList<HeroAttack>hatemplist = new ArrayList<HeroAttack>();


        // update towershot/ Removing monster and adding money to player
        for(int i = 0; i < m_towershot.size(); i++){




            //if(towershot.get(i) != null){

            // This case is incase the monster dies, then get rid of the tower shot
            // following the monster

            // gets monster X and Y that the towershot is locked on
            for(int k = 0; k < m_monster.size(); k++){
                if(m_monster.get(k).getID() == m_towershot.get(i).getMonsterID()){
                    int m_monster_x = m_monster.get(k).getX();
                    int m_monster_y = m_monster.get(k).getY();




                    m_towershot.get(i).update(m_monster_x, m_monster_y);

                    // means shot connected and its not an ice tower shot which is 3
                    if(collision(m_towershot.get(i), m_monster.get(k))){

                        // sets subtracts bullter damage to m_monster health
                        m_monster.get(k).setHealth(m_towershot.get(i).getPower());

                        // if m_monster got hit by a slow shot slow its movement speed
                        if(m_towershot.get(i).getShotType() == 3){
                            m_monster.get(k).setSlow_effect();
                        }

                        // checks the m_monster hp if it is less then or = 0 get rid of it
                        // along with all shots moving to that target
                        if(m_monster.get(k).getHealth() <= 0){

                            player.AddMoney(m_monster.get(k).GetMoney());

                            // removes other tower shots
                            for(int y = 0; y < m_towershot.size(); y++){

                                // this is so the original shot isnt removed before
                                // checking all shots
                                if(y != i){
                                    if(m_towershot.get(y).getMonsterID() ==
                                            m_towershot.get(i).getMonsterID()){

                                        //towershot.remove(y);
                                        tstemplist.add(m_towershot.get(y));
                                        System.out.println("Removing tower shot: " + y);

                                    }
                                }

                            }

                            if(m_infiity){
                                // removes other tower shots
                                for(int y = 0; y < m_hero_attack.size(); y++){

                                    // this is so the original shot isnt removed
                                    // before checking all shots
                                        if(m_hero_attack.get(y).getMonsterID() ==
                                                m_towershot.get(i).getMonsterID()){

                                            //towershot.remove(y);
                                            hatemplist.add(m_hero_attack.get(y));
                                            System.out.println("Removing tower shot: " + y);

                                        }

                                }
                            }

                            mtemplist.add(m_monster.get(k));
                            //monster.remove(k);

                            // remove this last becuase you still need its components before hand
                            tstemplist.add(m_towershot.get(i));
                            //towershot.remove(i);
                            //System.out.println("Removing tower shot: " + i);




                        }
                        else{
                            // remove this last becuase you still need its components before hand
                            tstemplist.add(m_towershot.get(i));
                            //towershot.remove(i);
                            System.out.println("Removing tower shot: " + i);
                        }
                    }


                }
            }
        }

        // remove all tower shots now
        m_towershot.removeAll(tstemplist);

        if(m_infiity){
            // remove all hero attacks
            m_hero_attack.removeAll(hatemplist);
        }


        // remove all shots in monster temp list
        m_monster.removeAll(mtemplist);

    }
    //**********************************************************************

    /**
     public void draw(Canvas a_canvas)

     NAME

     draw

     SYNOPSIS

        public void draw(Canvas a_canvas)
            Canvas a_canvas -> is the canvas(the screen) which things will be drawn to

     DESCRIPTION

        Draws things to the screen so people can see whats happening

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    @Override
    public void draw(Canvas a_canvas){

        String errormessage = "Insuficient Gold or";
        String errormessage2 = "Cant Place Tower There";


        // to color and make the grid
        Paint red_paintbrush_stroke;
        red_paintbrush_stroke = new Paint();
        red_paintbrush_stroke.setColor(Color.RED);
        red_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        red_paintbrush_stroke.setStrokeWidth(10);

        Paint Herostats = new Paint();
        Herostats.setTextSize(86);
        Herostats.setColor(Color.BLACK);

        Paint gray_paintbrush_stroke;
        gray_paintbrush_stroke = new Paint();
        gray_paintbrush_stroke.setColor(Color.GRAY);
        gray_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        gray_paintbrush_stroke.setStrokeWidth(10);

        Paint yellow_paintbrush_stroke;
        yellow_paintbrush_stroke = new Paint();
        yellow_paintbrush_stroke.setColor(Color.YELLOW);
        yellow_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        yellow_paintbrush_stroke.setStrokeWidth(10);

        Paint money_text = new Paint();
        money_text.setTextSize(86);
        money_text.setColor(Color.YELLOW);

        Paint health_text = new Paint();
        health_text.setTextSize(86);
        health_text.setColor(Color.RED);

        Paint error_text = new Paint();
        error_text.setTextSize(125);
        error_text.setColor(Color.RED);


        if(a_canvas != null){

            // before scale create a saved state
            final int savedState = a_canvas.save();

            // draws the level or room player is playing on
            m_room.draw(a_canvas);

            // draws the towers
            for(Tower t: m_tower){
                t.draw(a_canvas);
                // draw tower radius for player to see when tower is clicked on
                if(getXcoord(t.getX()) == first_pressX && getYcoord(t.getY()) == first_pressY){
                    a_canvas.drawCircle(t.getX() + 65, t.getY() + 65, t.getRange(), red_paintbrush_stroke);
                }

            }

            // draws shop items
            for(Shop s: m_shopitems){
                s.draw(a_canvas);

            }

            // draws hero and hero icon
            if(m_infiity){

                for(Hero_Icon h: m_h_icon){
                    h.draw(a_canvas);
                }

                for(Hero h: m_hero){
                    h.draw(a_canvas);

                    // show exp bar
                    double percentage = (double)125 / (double)h.getNeededExp();
                    double exp_difference = h.getEXP();
                    a_canvas.drawLine(h.getX(), h.getY(), h.getX() + (float)((exp_difference * percentage)), h.getY(), gray_paintbrush_stroke);

                    if(heroselected){
                        a_canvas.drawCircle(h.getX() + 65, h.getY() + 65, (h.getRange()), red_paintbrush_stroke);

                        // while selcted see stats
                        a_canvas.drawText("Level: " + h.getLevel(), 2000, 1290, Herostats);
                        a_canvas.drawText("Range: " + (((h.getRange() / 130) + .5)-1), 2000, 1210, Herostats);
                        a_canvas.drawText("Power: " + h.getDamage(), 2000, 1130, Herostats);
                        a_canvas.drawText("AttackSp: " + (h.getAttack_speed()/1000), 2000, 1050, Herostats);
                    }

                    // do knight animation attack
                    if(h.getType() == 1 && attack_timer != 0 && knight_attack_animation_start){
                            a_canvas.drawCircle(h.getX() + 65, h.getY() + 65, (h.getRange()), yellow_paintbrush_stroke);
                            a_canvas.drawCircle(h.getX() + 55, h.getY() + 55, (h.getRange()), yellow_paintbrush_stroke);
                            a_canvas.drawCircle(h.getX() + 50, h.getY() + 50, (h.getRange()), yellow_paintbrush_stroke);
                            a_canvas.drawCircle(h.getX() + 35, h.getY() + 35, (h.getRange()), yellow_paintbrush_stroke);
                    }
                    else{
                        if(TimeUnit.MILLISECONDS.convert(System.nanoTime() - attack_timer, TimeUnit.NANOSECONDS) >= 100){
                            knight_attack_animation_start = false;
                        }
                    }

                }
            }


            // draws the monsters
            for(Monster m: m_monster){
                m.draw(a_canvas);

                // the line is 125 pixels long
                double percentage = (double)125 / (double)m.getStartHealth();
                // calculates the the missing health
                double health_difference = m.getStartHealth() - m.getHealth();
                a_canvas.drawLine(m.getX(), m.getY(), m.getX() + (float)(125 - (health_difference * percentage)), m.getY(), red_paintbrush_stroke);
                //a_canvas.drawLine(m.getX(), m.getY() + 130, m.getX() + 125, m.getY() + 130, red_paintbrush_stroke);
                //a_canvas.drawLine();
            }

            // draws towershot
            for (TowerShot ts: m_towershot){
                ts.draw(a_canvas);
            }

            // draws hero attacks
            if(m_infiity){
                for(HeroAttack a: m_hero_attack){
                    a.draw(a_canvas);
                }
            }

           // if(PopsUpIsUp == true){
                for(MyPopUpMenu mp: m_mypopupmenus){
                    mp.draw(a_canvas);
                }
           // }

            for(Start_Monster_Wave_Icon i: mIcons){
                i.draw(a_canvas);
            }

            // Error Message For buying things from shop
            if(m_Error){
                a_canvas.drawText(errormessage, 750, 770, error_text);
                a_canvas.drawText(errormessage2, 700, 900, error_text);
            }
            // After seconds make the message dissapear
            if(TimeUnit.SECONDS.convert(System.nanoTime() - m_errorStartTime, TimeUnit.NANOSECONDS) >= 3){

                m_Error = false;
            }

            //Money Text/ Bottom Left of Screen *****************************
            a_canvas.drawText("" + player.GetHealth(), 135, 1420, health_text);
            a_canvas.drawText("" + player.GetMoney(), 395, 1420, money_text);

            // draw the wave number into the bottom right by the shop
            a_canvas.drawText("Wave Number: " + m_wavenumber, 1300,1420, money_text );


/*
            // debugging perpuses horizontal grid lines( up and down lines)
            //for(int i = 0; i < 23; i++){
            for(int i = 0; i < 21; i++){
                //a_canvas.drawLine((float)(i * 120), 0, (float)(i * 120), HEIGHT, red_paintbrush_stroke);
                // doing this bottom one because the images are about 130 by 130
                a_canvas.drawLine((float)(i * 130), 0, (float)(i * 130), HEIGHT, red_paintbrush_stroke);
            }

            // vertical grid lines
            for(int i = 0; i < 14; i++){
                //a_canvas.drawLine( 0 , (float)(i * 112.5), WIDTH, (float)(i * 112.5), red_paintbrush_stroke);
                // doing this bottom one because the images are about 130 by 130
                a_canvas.drawLine( 0 , (float)(i * 130), WIDTH, (float)(i * 130), red_paintbrush_stroke);
            }
*/


            a_canvas.restoreToCount(savedState);
        }
    }

    //**********************************************************************

    /**
     public int getXcoord(int a_x)

     NAME

     getXcoord

     SYNOPSIS

        public int getXcoord(int a_x)
            int a_x -> x coord

     DESCRIPTION

     turn the x coord into the 130 grid which the game uses

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    public int getXcoord(int a_x){
        //return Math.round(x / 120);
        // doing this bottom one because images are 130 by 130
        return Math.round(a_x / 130);
    }

    /**
     public int getYcoord(int a_y)

     NAME

     getYcoord

     SYNOPSIS

     public int getYcoord(int a_y)
     int a_y -> y coord

     DESCRIPTION

     turn the y coord into the 130 grid which the game uses

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    public int getYcoord(int a_y){
        //double newy = Math.round(y/119.5);
        // doing this bottom one because images are 130 by 130
        double newy = Math.round(a_y/130);
        int thisy = (int) newy;
        return thisy;
    }

    /**
     public boolean collision(GameObject a_a, GameObject a_b)

     NAME

     collision

     SYNOPSIS

        public boolean collision(GameObject a_a, GameObject a_b)
            GameObject a_a -> first game object
            GameObject a_b -> second game Object

     DESCRIPTION

     Checks for unit collesion for gameobject a and b

     RETURNS

     true if the objects collide

     AUTHOR

     Kevin Diana

     DATE

     1:07Am 12/29/2001

     */
    public boolean collision(GameObject a_a, GameObject a_b){

        if(Rect.intersects(a_a.getRectangle(), a_b.getRectangle())){

            return true;
        }
        else{
            return false;
        }
    }

    /**
     public boolean addmonster(int a_wavenumber)

     NAME

     addmonster

     SYNOPSIS

        public boolean addmonster(int a_wavenumber)
            int a_wavenumber -> the wave number that is giong to be spawned

     DESCRIPTION

     adds monster to the monster ArrayList depending on what monsters are in the wave

     RETURNS

     true of the monster is in the array otherwise return false if there are no more monster to spawn

     AUTHOR

     Kevin Diana

     DATE

     1:47Am 12/29/2001

     */
    public boolean addmonster(int a_wavenumber){

        // is used for infinity mode, when the wave goes over 13 waves, the limit is turned on
        int infinityFactor = 0;
        double hp_mult = 1;
       if(m_infiity){
           if( a_wavenumber > 13){
               hp_mult = 3;
               if(((a_wavenumber - 12) / 2) >= 2){
                   hp_mult = pow(2,((a_wavenumber -13) / 2));
               }

           }
           // >= 12 because it starts at 0 so wave 12 is wave 13
           if(a_wavenumber >= 12){
               infinityFactor = a_wavenumber;
           }
       }
        if(a_wavenumber + 1 > m_monster_wavesArray.length && !m_infiity){
            m_wavesalldone = true;
            return false;
        }
        else{
            for(int x = 0; x < m_monster_wavesArray[a_wavenumber - infinityFactor].length; x++){
                switch(m_monster_wavesArray[a_wavenumber - infinityFactor][x]){
                    // means already done it
                    case 0:
                        break;
                    //red dot
                    case 1:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_01_red_dot), -130 , 520, 125, 128, 8,
                                m_currentroom, 1, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // green blob
                    case 2:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_02_green_blob), -130 , 520, 125, 128, 8,
                                m_currentroom, 2, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // mouse
                    case 3:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_03_mouse), -130 , 520, 125, 128, 8,
                                m_currentroom, 3, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // bannana man
                    case 4:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_04_bannana_man), -130 , 520, 125, 128, 8,
                                m_currentroom, 4, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // lion
                    case 5:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_05_lion), -130 , 520, 125, 128, 8,
                                m_currentroom, 5, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // red theif
                    case 6:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_06_red_theif), -130 , 520, 125, 128, 8,
                                m_currentroom, 6, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // white knight
                    case 7:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_07_white_knight), -130 , 520, 125, 128, 8,
                                m_currentroom, 7, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // blue knight
                    case 8:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_08_blue_knight), -130 , 520, 125, 128, 8,
                                m_currentroom, 8, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // bomb man
                    case 9:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_09_bomb_man), -130 , 520, 125, 128, 8,
                                m_currentroom, 9, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // fire spirit
                    case 10:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_10_fire_spirit), -130 , 520, 125, 128, 8,
                                m_currentroom, 10, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // baby dragon
                    case 11:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_11_baby_dragon), -130 , 520, 125, 128, 8,
                                m_currentroom, 11, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // silver dragon
                    case 12:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_12_silver_dragon), -130 , 520, 125, 128, 8,
                                m_currentroom, 12, a_wavenumber + x, hp_mult));

                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // king of beasts
                    case 13:
                        m_monster.add(new Monster(BitmapFactory.decodeResource(getResources(),
                                R.drawable.monster_13_king_of_beast), -130 , 520, 125, 128, 8,
                                m_currentroom, 13, a_wavenumber + x, hp_mult));
                        m_monster_wavesArray[a_wavenumber - infinityFactor][x] = 0;
                        return true;
                    // means that the game is over after everything in the monster array is gone
                    case 14:
                        m_game_done = true;
                        break;

                }
            }


        }



        return false;
    }

    /**
     public Tower getTowerByCoord(int a_x, int a_y)

     NAME

     getTowerByCoord

     SYNOPSIS

        public Tower getTowerByCoord(int a_x, int a_y)
            int a_x -> x coord
            int a_y -> y coord

     DESCRIPTION

     searches tower in the towerArraylist for the exact a_x and a_y coord, if the tower is found return tower number
     else return -1

     RETURNS

     true of the monster is in the array otherwise return false if there are no more monster to spawn

     AUTHOR

     Kevin Diana

     DATE

     1:47Am 12/29/2001

     */
    public Tower getTowerByCoord(int a_x, int a_y){

        for(int i = 0; i < m_tower.size(); i++){
            if (m_tower.get(i).getX() == (a_x * 130) && m_tower.get(i).getY() == (a_y * 130)){
                return m_tower.get(i);
            }
        }

        return m_tower.get(-1);
    }


}



package com.kevindiana.forestdefence20;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.widget.PopupMenu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.abs;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    // the demenstion of the game ***********
    public static final int WIDTH = 2500;
    public static final int HEIGHT = 1500;

    //getting the room******************
    private Room room1;
    private int [][]currentroom;

    // monster stuff***********************
    // vector that holds monsters
    private ArrayList<Monster> monster;
    // number for monster start time
    private long monsterStartTime;

    // monster wave stuff*************************
    // the number of waves that have passes, starts at 1
    private int wavenumber = 1;
    // creates monsterwave object
    private MonsterWave mwaves;
    // the array that holds every monster wave for the map
    private int [][]monsterwaves;
    // if every way is done then the game is over
    boolean wavesalldone = false;
    // a countdown for the next wave to come 8 seconds
    boolean nextWaveCountdown = false;
    // int next wave countdown 8 seconds
    private long countdown_8sec;

    // shop stuff********************
    private Shop shop;
    private ArrayList<Shop> shopitems;

    // Tower stuff*****************
    //private Tower tower;
    private ArrayList<Tower> tower;

    // TowerShot stuff**************
    private ArrayList<TowerShot> towershot;
    // to have towers shoot at appropriate times
    private long shotTimer [];

    //PopupMenu Stuff*********
    private ArrayList<MyPopUpMenu> mypopupmenus;
    private boolean PopsUpIsUp = false;
    private boolean Error = false;
    private long errorStartTime;

    // On click event stuff
    int spot_number;
    private boolean is_it_first_click = true;
    private int first_pressX = 0;
    private int first_pressY = 0;

    // Player class stuff
    Player player;


    // the main thread for the entire game
    private MainThread thread;

    public GamePanel(Context context)
    {
        super(context);


        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry)
        {
            try{thread.setRunning(false);
                thread.join();

            }catch(InterruptedException e){e.printStackTrace();}
            retry = false;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        room1 = new Room(BitmapFactory.decodeResource(getResources(), R.drawable.room1));
        currentroom = room1.getroom(1);
        shopitems = new ArrayList<Shop>();
        shopitems.add(new Shop(BitmapFactory.decodeResource(getResources(), R.drawable.whiteflowertowershopbutton), 910, 1300, 1));
        shopitems.add(new Shop(BitmapFactory.decodeResource(getResources(), R.drawable.double_shot_tower_shop_buton), 1040, 1300, 2));
        shopitems.add(new Shop(BitmapFactory.decodeResource(getResources(), R.drawable.ice_tower_shop_button), 1170, 1300, 3));
        //shop = new Shop(BitmapFactory.decodeResource(getResources(), R.drawable.whiteflowertowershopbutton), 1500, 1250, 1);

        // creates array list for popup menue
        mypopupmenus = new ArrayList<MyPopUpMenu>();

        // creates array list for monsters
        monster = new ArrayList<Monster>();
        // starts calculating the time the first monster spawns
        monsterStartTime = System.nanoTime();

        mwaves = new MonsterWave();
        monsterwaves = mwaves.getwave(1);

        tower = new ArrayList<Tower>();
        towershot = new ArrayList<TowerShot>();
        shotTimer = new long [100];

        player = new Player();

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int eventX = (int)event.getX();
        int eventY = (int)event.getY();
        int second_press_Spot_num = -1;


        // This is to stop ConcurrentModificationExceptions
        //ArrayList<Tower> ttemp = new ArrayList<Tower>();


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

        /*
        for (Shop s: shopitems){
           int shopitemx = s.getX();
           int shopitemy = s.getY();

            System.out.println(getXcoord(shopitemx));
            System.out.println(getYcoord(shopitemy));
        }
        */

        // this gets the spot number only for the second click and holds it
        if (is_it_first_click == false){
            second_press_Spot_num = currentroom[eventY][eventX];
        }
        else{
            spot_number = currentroom[eventY][eventX];
            first_pressX = eventX;
            first_pressY = eventY;
        }

        // this is for being able to place towers down / upgrade them
        // this switch statement will check what number is in the grid the person clicked on
        // means its their second click
        if (is_it_first_click == false){

            // if the popup menue has something in it
            if(mypopupmenus.size() > 0){
                // if the next click is the same location as the sell button
                if(eventX == 9 && eventY == 6){

                    for(Tower t: tower){
                        if(getXcoord(t.getX()) == first_pressX && getYcoord(t.getY()) == first_pressY){
                            player.AddMoney(t.getSell_cost());
                            tower.remove(t);
                            // then remove from the map itself
                            currentroom[first_pressY][first_pressX] = 0;

                            spot_number = -1;
                        }
                        else{
                            spot_number = -1;
                        }
                    }
                }
                // upgrade tower
                else if(eventX == 12 && eventY == 6){
                    for(Tower t: tower){
                        if(getXcoord(t.getX()) == first_pressX && getYcoord(t.getY()) == first_pressY){
                            // check to see if player has the money to upgrade
                            if(player.GetMoney() >= t.getUpgrade_cost()){
                                t.upgrade_tower();
                                spot_number = -1;
                            }
                            // you dont have the money give an error
                            else{
                                spot_number = -1;
                            }

                        }
                        // didnt click on the upgrade button
                        else{
                            spot_number = -1;
                        }
                    }
                }
                else{
                    spot_number = -1;
                }
            }
            else{
                switch(spot_number){

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
                        if(second_press_Spot_num == 0){

                            // check to see if player has enough money
                            if(player.GetMoney() >= 5 ){

                                //ttemp.add((new Tower(BitmapFactory.decodeResource(getResources(), R.drawable.whiteflowertowershopbutton), (eventX * 130), (eventY * 130), 1)));
                                tower.add(new Tower(BitmapFactory.decodeResource(getResources(), R.drawable.whiteflowertower), (eventX * 130), (eventY * 130), 1));

                                // this makes the empty spot a tower 1 spot now by putting replacing 0 with 11
                                currentroom[eventY][eventX] = 11;

                                // reset first button click
                                spot_number = -1;

                                player.SubMoney(5);

                            }
                            // else create an error message for player for insuficent gold or cant put on thatspot
                            else{

                                // make error true to start the popup timmer
                                Error = true;
                                errorStartTime = System.nanoTime();
                            }

                        }


                        is_it_first_click = true;
                        break;
                    // shop item 2
                    case 3:

                        // only add tower if the spot is a 0 meaning blank space
                        if(second_press_Spot_num == 0){

                            // check to see if player has enough money
                            if(player.GetMoney() >= 5 ){

                                tower.add(new Tower(BitmapFactory.decodeResource(getResources(), R.drawable.double_shot_tower), (eventX * 130), (eventY * 130), 2));

                                // this makes the empty spot a tower 2 spot now by putting replacing 0 with 11
                                currentroom[eventY][eventX] = 12;

                                // reset first button click
                                spot_number = -1;

                                player.SubMoney(5);

                            }
                            // else create an error message for player for insuficent gold or cant put on thatspot
                            else{

                                // make error true to start the popup timmer
                                Error = true;
                                errorStartTime = System.nanoTime();
                            }

                        }

                        is_it_first_click = true;
                        break;
                    // shop item 3
                    case 4:

                        // only add tower if the spot is a 0 meaning blank space
                        if(second_press_Spot_num == 0){

                            // check to see if player has enough money
                            if(player.GetMoney() >= 5 ){

                                tower.add(new Tower(BitmapFactory.decodeResource(getResources(), R.drawable.ice_tower), (eventX * 130), (eventY * 130), 3));

                                // this makes the empty spot a tower 2 spot now by putting replacing 0 with 11
                                currentroom[eventY][eventX] = 13;

                                // reset first button click
                                spot_number = -1;

                                player.SubMoney(5);

                            }
                            // else create an error message for player for insuficent gold or cant put on thatspot
                            else{

                                // make error true to start the popup timmer
                                Error = true;
                                errorStartTime = System.nanoTime();
                            }

                        }

                        is_it_first_click = true;

                        break;
                    // shop item 4
                    case 5:
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

            for(int i = 0; i <mypopupmenus.size(); i++){

                // remove monster when it gets off screen, should also deplete hp
                mypopupmenus.remove(i);
                //System.out.println("Removing monster");

            }

            is_it_first_click = true;
            return super.onTouchEvent(event);
        }



/*
        switch (event.getAction() & MotionEvent.ACTION_MASK){

            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:

                break;
        }
        */

        // this is for upgrade and selling towers i guess
        // also could be for clicking on monster/ tower and seeing their stats
        if (is_it_first_click == true){

            switch(spot_number){

                // tower type 1
                case 2:
                    is_it_first_click = false;
                    break;
                // tower type 2
                case 3:
                    is_it_first_click = false;
                    break;
                // tower type 3
                case 4:
                    is_it_first_click = false;
                    break;
                // for tower type 1
                case 11:

                    mypopupmenus.add(new MyPopUpMenu(BitmapFactory.decodeResource(getResources(), R.drawable.pop_upmenue_tower_type1_lvl_1)));
                    // will only draw if popup is true dont think you need this
                    //PopsUpIsUp = true;

                    is_it_first_click = false;

                    break;
                // for tower type 2
                case 12:
                    is_it_first_click = false;
                    break;
                // for tower type 3
                case 13:
                    is_it_first_click = false;
                    break;
            }
        }

        //is_it_first_click = false;
        // this gets the spot number for the first click and holds it
        //spot_number = currentroom[eventY][eventX];

        return super.onTouchEvent(event);
    }

    public void update()
    {
        room1.update();

        // adding the monster on timer
        long monsterElapsed = (System.nanoTime()-monsterStartTime) / 1000000;

        //if(!wavesalldone){
        if (!nextWaveCountdown){

            if(monsterElapsed >(875)){

                //if(!nextWaveCountdown){
                if (!wavesalldone){
                    //System.out.println("making monster!!");
                    if(!addmonster(wavenumber - 1)){

                        wavenumber++;
                        // next wave will start so start countdown from 8 seconds
                        nextWaveCountdown = true;
                        countdown_8sec = System.nanoTime();
                    }
                }

                // reset timer/ wave
                monsterStartTime = System.nanoTime();
            }

        }
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

        // This is where we will have to check to see if the tower will shoot.. probably make this into a seperate function with everything else
        // need to check every monster and see if it is within any tower range
        for(int i = 0; i < tower.size(); i++){
            int tower_x = tower.get(i).getX();
            int tower_y = tower.get(i).getY();
            double tower_range = tower.get(i).getRange();
            int tower_type = tower.get(i).getTowerType();


            // searching for monster in range
            for(int j = 0; j < monster.size(); j++){
                int monster_x = monster.get(j).getX();
                int monster_y = monster.get(j).getY();


                //long timeElapsed = (System.nanoTime() - countdown_8sec);
                // checks to see if the tower has already shot something 0 means it has not
                if (shotTimer[i] == 0){

                    switch(tower_type){
                        // sniper tower
                        case 1:
                            // means the shot is in range
                            if(abs(monster_x - tower_x) + abs(monster_y - tower_y) <= tower_range ){
                                towershot.add(new TowerShot(BitmapFactory.decodeResource(getResources(), R.drawable.fire_flower_shot), tower.get(i).getX(), tower.get(i).getY(), 2, tower.get(i).getPower(), monster.get(j).getID(), 1));
                                System.out.println("Creating tower shot: " + i);
                                // break afer wards for first monster


                                shotTimer[i] = System.nanoTime();
                                break;
                            }
                            break;
                        // double shot tower
                        case 2:

                            if(abs(monster_x - tower_x) + abs(monster_y - tower_y) <= tower_range){
                                towershot.add(new TowerShot(BitmapFactory.decodeResource(getResources(), R.drawable.double_shot_tower_projectile), tower.get(i).getX(), tower.get(i).getY(), 2, tower.get(i).getPower(), monster.get(j).getID(), 2));

                                // THen check to see if a second monsterr can be shot at as well

                                for(int k = 0; k < monster.size(); k++){

                                    // This makes sure we arent going to shoot at the same monster the tower already has targeted
                                    if(monster.get(k).getID() != monster.get(j).getID()) {
                                        int monster2_x = monster.get(k).getX();
                                        int monster2_y = monster.get(k).getY();

                                        if(abs(monster2_x - tower_x) + abs(monster2_y - tower_y) <= tower_range){
                                            towershot.add(new TowerShot(BitmapFactory.decodeResource(getResources(), R.drawable.double_shot_tower_projectile), tower.get(i).getX(), tower.get(i).getY(), 2, tower.get(i).getPower(), monster.get(k).getID(), 2));

                                            shotTimer[i] = System.nanoTime();
                                            break;
                                        }
                                    }
                                }
                                shotTimer[i] = System.nanoTime();
                                break;
                            }
                            break;
                        // slow tower
                        case 3:

                            // means the shot is in range
                            if(abs(monster_x - tower_x) + abs(monster_y - tower_y) <= tower_range ){
                                towershot.add(new TowerShot(BitmapFactory.decodeResource(getResources(), R.drawable.ice_towr_projectile), tower.get(i).getX(), tower.get(i).getY(), 3, tower.get(i).getPower(), monster.get(j).getID(), 3));
                                System.out.println("Creating tower shot: " + i);
                                // break afer wards for first monster


                                shotTimer[i] = System.nanoTime();
                                break;
                            }

                            break;

                    }

                }
                // check to see if the tower has already waited for its attack speed to shoot again
                else{

                    if(TimeUnit.MILLISECONDS.convert(System.nanoTime() - shotTimer[i], TimeUnit.NANOSECONDS) >= tower.get(i).getAttackSpeed() ){
                        shotTimer[i] = 0;
                    }
                }

            }
        }

        // temporary array to hold shots that will be deleated later
        ArrayList<Monster> mtemplist = new ArrayList<Monster>();
        ArrayList<TowerShot> tstemplist = new ArrayList<TowerShot>();

        // update towershot/ Removing monster and adding money to player
        for(int i = 0; i < towershot.size(); i++){




            //if(towershot.get(i) != null){

                // This case is incase the monster dies, then get rid of the tower shot following the monster

                // gets monster X and Y that the towershot is locked on
                for(int k = 0; k < monster.size(); k++){
                    if(monster.get(k).getID() == towershot.get(i).getMonsterID()){
                        int monster_x = monster.get(k).getX();
                        int monster_y = monster.get(k).getY();




                        towershot.get(i).update(monster_x, monster_y);

                        // means shot connected and its not an ice tower shot which is 3
                        if(collision(towershot.get(i), monster.get(k))){

                            // sets subtracts bullter damage to monster health
                            monster.get(k).setHealth(towershot.get(i).getPower());

                            // if monster got hit by a slow shot slow its movement speed
                            if(towershot.get(i).getShotType() == 3){
                                monster.get(k).setSlow_effect();
                            }

                            // checks the monster hp if it is less then or = 0 get rid of it along with all shots moving to that target
                            if(monster.get(k).getHealth() <= 0){

                                player.AddMoney(monster.get(k).GetMoney());

                                // removes other tower shots
                                for(int y = 0; y < towershot.size(); y++){

                                    // this is so the original shot isnt removed before checking all shots
                                    if(y != i){
                                        if(towershot.get(y).getMonsterID() == towershot.get(i).getMonsterID()){

                                            //towershot.remove(y);
                                            tstemplist.add(towershot.get(y));
                                            System.out.println("Removing tower shot: " + y);

                                        }
                                    }

                                }

                                mtemplist.add(monster.get(k));
                                //monster.remove(k);

                                // remove this last becuase you still need its components before hand
                                tstemplist.add(towershot.get(i));
                                //towershot.remove(i);
                                //System.out.println("Removing tower shot: " + i);




                            }
                            else{
                                // remove this last becuase you still need its components before hand
                                tstemplist.add(towershot.get(i));
                                //towershot.remove(i);
                                System.out.println("Removing tower shot: " + i);
                            }
                        }


                    }
                }
        }

        // remove all tower shots now
        towershot.removeAll(tstemplist);

        // remove all shots in monster temp list
        monster.removeAll(mtemplist);

        // clean up the lose ends of the 2 temp array
        //System.gc();

        // update the monsters
        for(int i = 0; i <monster.size(); i++){

            // update monster
            monster.get(i).update();

            // remove monster when it gets off screen, should also deplete hp
            //if(monster.get(i).getX()<-100){
            if(monster.get(i).getX() > (WIDTH)){

                // removes other tower shots
                for(int y = 0; y < towershot.size(); y++){


                    if(i == towershot.get(y).getMonsterID()){

                            towershot.remove(y);
                    }

                }

                player.SetHealth(monster.get(i).getPower());

                monster.remove(i);
                System.out.println("Removing monster");
                break;
            }
        }



    }

    @Override
    public void draw(Canvas canvas){

        String errormessage = "Insuficient Gold or";
        String errormessage2 = "Cant Place Tower There";


        // to color and make the grid
        Paint red_paintbrush_stroke;
        red_paintbrush_stroke = new Paint();
        red_paintbrush_stroke.setColor(Color.RED);
        red_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        red_paintbrush_stroke.setStrokeWidth(10);

        Paint money_text = new Paint();
        money_text.setTextSize(86);
        money_text.setColor(Color.YELLOW);

        Paint health_text = new Paint();
        health_text.setTextSize(86);
        health_text.setColor(Color.RED);

        Paint error_text = new Paint();
        error_text.setTextSize(125);
        error_text.setColor(Color.RED);


        if(canvas != null){

            // before scale create a saved state
            final int savedState = canvas.save();

            // draws the level or room player is playing on
            room1.draw(canvas);

            // draws the towers
            for(Tower t: tower){
                t.draw(canvas);
                // draw tower radius for player to see;

                canvas.drawCircle(t.getX() + 65, t.getY() + 65, t.getRange(), red_paintbrush_stroke);
            }

            // draws shop items
            for(Shop s: shopitems){
                s.draw(canvas);
            }
            //shop.draw(canvas);

            // draws the monsters
            for(Monster m: monster){
                m.draw(canvas);
            }

            // draws towershot
            for (TowerShot ts: towershot){
                ts.draw(canvas);
            }

           // if(PopsUpIsUp == true){
                for(MyPopUpMenu mp: mypopupmenus){
                    mp.draw(canvas);
                }
           // }

            // Error Message For buying things from shop
            if(Error){
                canvas.drawText(errormessage, 750, 770, error_text);
                canvas.drawText(errormessage2, 700, 900, error_text);
            }
            // After seconds make the message dissapear
            if(TimeUnit.SECONDS.convert(System.nanoTime() - errorStartTime, TimeUnit.NANOSECONDS) >= 3){

                Error = false;
            }

            //Money Text/ Bottom Left of Screen *****************************
            canvas.drawText("" + player.GetHealth(), 135, 1420, health_text);
            canvas.drawText("" + player.GetMoney(), 395, 1420, money_text);





            // debugging perpuses horizontal grid lines( up and down lines)
            //for(int i = 0; i < 23; i++){
            for(int i = 0; i < 21; i++){
                //canvas.drawLine((float)(i * 120), 0, (float)(i * 120), HEIGHT, red_paintbrush_stroke);
                // doing this bottom one because the images are about 130 by 130
                canvas.drawLine((float)(i * 130), 0, (float)(i * 130), HEIGHT, red_paintbrush_stroke);
            }

            // vertical grid lines
            for(int i = 0; i < 14; i++){
                //canvas.drawLine( 0 , (float)(i * 112.5), WIDTH, (float)(i * 112.5), red_paintbrush_stroke);
                // doing this bottom one because the images are about 130 by 130
                canvas.drawLine( 0 , (float)(i * 130), WIDTH, (float)(i * 130), red_paintbrush_stroke);
            }



            canvas.restoreToCount(savedState);
        }
    }

    public int getXcoord(int x){
        //return Math.round(x / 120);
        // doing this bottom one because images are 130 by 130
        return Math.round(x / 130);
    }

    public int getYcoord(int y){
        //double newy = Math.round(y/119.5);
        // doing this bottom one because images are 130 by 130
        double newy = Math.round(y/130);
        int thisy = (int) newy;
        return thisy;
    }

    // the collision class, checks to see if tower shots hit monster
    public boolean collision(GameObject a, GameObject b){

        if(Rect.intersects(a.getRectangle(), b.getRectangle())){

            return true;
        }
        else{
            return false;
        }
    }

    // return true if monster is in the array, return false if its not
    public boolean addmonster(int wavenumber){

        // means that all the waves went
        if(wavenumber + 1 > monsterwaves.length){
            wavesalldone = true;
            return false;
        }
        else{
            for(int x = 0; x < monsterwaves[wavenumber].length; x++){
                switch(monsterwaves[wavenumber][x]){
                    // means already done it
                    case 0:
                        break;
                    case 1:
                        monster.add(new Monster(BitmapFactory.decodeResource(getResources(), R.drawable.red_dot_sprite),
                                -130 , 520, 125, 130, 8, currentroom, 1, wavenumber + x));

                        monsterwaves[wavenumber][x] = 0;
                        return true;
                    case 2:
                        return true;
                    case 3:
                        return true;
                    case 4:
                        return true;
                    case 5:
                        return true;
                    case 6:
                        return true;
                    case 7:
                        return true;
                    case 8:
                        return true;
                    case 9:
                        return true;
                    case 10:
                        return true;
                    case 11:
                        return true;
                    case 12:
                        return true;
                    case 13:
                        monster.add(new Monster(BitmapFactory.decodeResource(getResources(), R.drawable.horned_monster_prototype),
                                -130 , 520, 125, 130, 8, currentroom, 13, wavenumber + x));
                        monsterwaves[wavenumber][x] = 0;
                        return true;

                }
            }


        }

        return false;
    }
}

package com.kevindiana.forestdefence20;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

/**
 class Game

 NAME

 Game -

 SYNOPSIS

 DESCRIPTION

 creates a new GamePanel then loads up all the information to the GamePanel

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 6:27pm 2/1/2017

 */
public class Game extends Activity {

    /**
protected void onCreate(Bundle savedInstanceState)

NAME

        onCreate - when the activity is launched everything within the function is called first

SYNOPSIS

        protected void onCreate(Bundle a_savedInstanceState)

        a_savedInstanceState -> The saved state of the application being passed in as a bundle


DESCRIPTION

        This function is called to start up the activity and to pass in extra information from
        previouse activity.  Then calls upon GamePanel to start the game.

RETURNS

        Nothing

AUTHOR

        Kevin Diana

DATE

        6:27pm 2/1/2017

*/
    @Override
    protected void onCreate(Bundle a_savedInstanceState) {
        super.onCreate(a_savedInstanceState);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // this is getting the information to choose the map and diffuculty
        String map_difficulty = getIntent().getExtras().getString("Chooser");

        setContentView(new GamePanel(this, map_difficulty));
    }

    /**
     protected void onCreateOptionsMenu(Menu a_menu)

     NAME

        onCreateOptionsMenu(Menu menu) - when the activity is launched creates a menue item

     SYNOPSIS

        protected void onCreateOptionsMenu(Menu a_menu)

        a_menu -> adds items to the action bar if it is present


     DESCRIPTION

        Creates a menu item from the res folder

     RETURNS
        true



     AUTHOR

        Kevin Diana

     DATE

        6:27pm 2/1/2017

     */
    @Override
    public boolean onCreateOptionsMenu(Menu a_menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, a_menu);
        return true;
    }

    /**
     onOptionsItemSelected(MenuItem item)

     NAME

    onOptionsItemSelected(menuItem a_item)

     SYNOPSIS

     onOptionsItemSelected(menuItem a_item)

     a_item -> gets the saved instance of the menu item


     DESCRIPTION

     / Handle action bar item clicks here. The action bar will
     // automatically handle clicks on the Home/Up button, so long
     // as you specify a parent activity in AndroidManifest.xml.

     RETURNS

     true if id = a_item.action_settings

     else return super.onOptionsItemSelected

     AUTHOR

     Kevin Diana

     DATE

     6:27pm 2/1/2017

     */
    @Override
    public boolean onOptionsItemSelected(MenuItem a_item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = a_item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(a_item);
    }
}
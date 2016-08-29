package com.example.jonathan.heylisten;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Random;

public class Play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //this line will put the app into fullscreen mode (only hides the status bar)
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        gameSelector(); //this calls a random game to be played
    }

//for starting a random game
    Random random = new Random();
    int randGame = random.nextInt(2);
    //the number 2 means it will generate 2 numbers - 0 and 1. Dumb

    public void gameSelector() {
    //this makes a random number then using that selects a minigame
        gameCatch gameCatch = new gameCatch(this);
        gameColorTouch gameColorTouch = new gameColorTouch(this);

        switch(randGame) {
            case 0: setContentView(gameColorTouch); break;
            case 1: setContentView(gameCatch); break;
            default: break;
        }
    }

//I tried putting the mainmenu return dialog in the Controls class but it appears you can only make the dialogs
    //appear in activities, not views
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //overriding the back button
        //before pressing back would bring you to the previous game, now it quits
        //intend on making this load a pause screen
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "Back button pressed", Toast.LENGTH_SHORT).show();
            mainMenuReturn(); //pressing back will give the player the option to return to the main menu
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void mainMenuReturn() {
        //to display the quit alert dialog
        AlertDialog.Builder b = new AlertDialog.Builder(this);

        b.setTitle("Paused");
        b.setIcon(android.R.drawable.star_big_on);
        b.setMessage("Return to main menu?");

        b.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which){
                startActivity(new Intent(Play.this, HomeScreen.class));
            }
        });

        b.setNegativeButton("Nope!", null);
        AlertDialog alertDialog = b.create();
        alertDialog.show();
    }
}


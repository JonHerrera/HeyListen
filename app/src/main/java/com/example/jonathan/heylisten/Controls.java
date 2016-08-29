package com.example.jonathan.heylisten;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.graphics.Color;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public abstract class Controls extends View {
//the abstract class will have all the imports and variables defined for user input
    //as well as the paint variables
//this is so I dont have to remake the variables in every class
    public Controls(Context context) {
        super(context);
    }

    Paint paint = new Paint();
    //used to display the drawings onto the canvas

            //METRICS && TROUBLESHOOTING

//these get the screen dimensions so I can make the objects relative to the edges
    DisplayMetrics screenSize = getContext().getResources().getDisplayMetrics();
    float screenWidth = screenSize.widthPixels;
    float screenHeight = screenSize.heightPixels;

//for getting position of user inputs
    float userX;
    float userY;

    //checking if touch input is being recognized by displaying coordinates in the corner
    public void troubleshoot(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(25);
        canvas.drawText(userX + ", " + userY, 25, 25, paint);
    }

    public void troubleshootToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

    }
            //GAME TEXT

//this method will display what the objective of the game is at the top
//have to pass the string but it is already formatted here
    public void gameObjective(String objective, Canvas canvas) {
        paint.setColor(Color.rgb(149, 165, 166));
        paint.setTextSize(125);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(objective, screenWidth/2, screenHeight/7, paint);
    }

//I have the random word generator on the outside so that the word doesn't keep switching while on screen
//also random for the Lose message
    Random random = new Random();
    int randMsg = random.nextInt(9);

    public void gameWin(Canvas canvas) {

        String msg; //making the win message change for the sake of variety

        switch (randMsg) {
            case 0: msg = "nice!"; break;
            case 1: msg = "radical!"; break;
            case 2: msg = "tubular!"; break;
            case 3: msg = "gnarly!"; break;
            case 4: msg = "mondo!"; break;
            case 5: msg = "outrageous!"; break;
            case 6: msg = "awesome!"; break;
            case 7: msg = "funky!"; break;
            case 8: msg = "groovy!"; break;
            case 9: msg = "sweet!"; break;
            default: msg = "P"; break;
        }

        paint.setColor(Color.rgb(46, 204, 113));
        paint.setTextSize(200);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(msg, screenWidth/2, 7*screenHeight/8, paint);
    }

    //this will be the mistake message for when the user fails the minigame
    public void gameLose(Canvas canvas) {

        String msg;

        switch(randMsg) {
            case 0: msg = "ouch!"; break;
            case 1: msg = "nope!"; break;
            case 2: msg = "wrong!"; break;
            case 3: msg = "aww!"; break;
            case 4: msg = "shucks!"; break;
            case 5: msg = "too bad!"; break;
            case 6: msg = "you tried!"; break;
            case 7: msg = "d'oh!"; break;
            case 8: msg = "darn!"; break;
            case 9: msg = "shoot!"; break;
            default: msg = "whoops!"; break;
        }

        paint.setColor(Color.rgb(207, 0, 15));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(200);
        canvas.drawText(msg, screenWidth/2, 7*screenHeight/8, paint);
    }

            //GAME TIMER

    //this is the start and end of the timer which. The end constantly changes
    float timerBeginning = 25; //the start position of the timer
    float timerFull = screenWidth-25; //the end position of the timer

    long timeStart = 4000;
    long thymeLeft; //the time elapsed, going down

    CountDownTimer doomTimer; //the timer itself

    public void timer(boolean go) { //created a timer to be used anywhere

        if(go == true) {
            doomTimer = new CountDownTimer(timeStart, 1) {
                //(startTime(ms), countBy(ms))
                Canvas canvas;

                @Override
                public void onTick(long l) {
                    thymeLeft = l; //this is the countdown time (time left)
                }

                @Override
                public void onFinish() {
                    thymeLeft = 0; //so that once the timer finishes it will be absolute 0
                }
            }.start(); //starts the timer
        }
    }

    public void drawTimer(Canvas canvas) {
        float timePercent = (thymeLeft/(timeStart/100)); //Taking the time left and dividing it by the total time given/100 to get a percent
        String time = Float.toString(thymeLeft/10); //turns the number into a string so I can display it
        float timeChange = timerFull*(timePercent/100); // this is how full the timer will be
        String percent = Float.toString(timePercent); //for troubleshooting so I can see the percentage

        paint.setColor(Color.rgb(129, 207, 224));
        canvas.drawRect(timerBeginning, screenHeight-75, timeChange, screenHeight-25, paint); //draw the timebar
        //rectangle(left, top, right, bottom, paint)
        paint.setTextAlign((Paint.Align.LEFT)); //displaying the timer
        paint.setTextSize(50);
        canvas.drawText(time, 25, screenHeight-125, paint); //displays the timer
        invalidate();
    }

        //CHANGE GAME

    public void changeGame() {
    //this method will be used to change the game
        //I realized that my games are not activities but rather they are views
        //by recalling the Play activity that is used to display the views, it will run the random game selector and start a new game
        getContext().startActivity(new Intent(getContext(), Play.class));
    }

}

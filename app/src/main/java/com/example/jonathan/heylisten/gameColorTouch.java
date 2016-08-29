package com.example.jonathan.heylisten;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import java.util.Random;

public class gameColorTouch extends Controls {

    public gameColorTouch(Context context) {
        super(context);
        timer(true); //by calling the timer() here, it does not constantly restart the timer with every frame

    }

    public void onDraw(Canvas canvas) {

        //this calls a method in the Controls class that presets the objective font, color, and pos
        //simply sending in whatever objective needs to be sent to it
        declareObjective(); //this calls a method that will randomly make an objective for the player
        gameObjective(objective, canvas); //this then takes the objective and passes it so it displays on the screen
        //drawing the circles this game
        circleRed(canvas);
        circleGreen(canvas);
        circleBlue(canvas);
        circleYellow(canvas);


        drawTimer(canvas); //draws timer bar on the bottom

        checkWin(canvas); //constantly checking where/if the player touches

        troubleshoot(canvas); //display the user input coordinates
        postInvalidate(); //constantly calls new frames
    }

    Random rand = new Random();
    int obj = rand.nextInt(4);

    String objective;

    public String declareObjective() {
    //the String is instantiated outside the method so it can be reached by the gameObjective()
        //also so that it changes upon every load
        switch (obj) {
            case 0: objective = "Touch blue!"; return objective;
            case 1: objective = "Touch green!"; return objective;
            case 2: objective = "Touch red!"; return objective;
            case 3: objective = "Touch yellow!"; return objective;
            default: objective = "What??"; return objective;
        }

    }

//circle dimensions, all relative to the screen size
    float circleRadius = 100;
    float circlePosRight = 3*screenWidth/4;
    float circlePosLeft = screenWidth/4;
    float circlePosTop = 3*screenHeight/8;
    float circlePosBottom = 5*screenHeight/8;
    float circlePosMiddleY = screenHeight/2;
    float circlePosMiddleX = screenWidth/2;

//simply drawing the colored circles
    public void circleRed(Canvas canvas) {
        paint.setColor(Color.rgb(236,100,75));
        canvas.drawCircle(circlePosRight, circlePosMiddleY, circleRadius, paint);

    }

    public void circleGreen(Canvas canvas) {
        paint.setColor(Color.rgb(46, 204, 113));
        canvas.drawCircle(circlePosMiddleX, circlePosBottom, circleRadius, paint);
    }

    public void circleBlue(Canvas canvas) {
        paint.setColor(Color.rgb(25, 181, 254));
        canvas.drawCircle(circlePosLeft, circlePosMiddleY, circleRadius, paint);
    }

    public void circleYellow(Canvas canvas) {
        paint.setColor(Color.rgb(233,212,96));
        canvas.drawCircle(circlePosMiddleX, circlePosTop, circleRadius, paint);
    }

//hmmm.. I'm wondering if I can put the touch in the Controls class so it constantly returns user input
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX(); //getting users X touch coordinate
        float touchY = event.getY(); //getting users Y touch coordinate

        switch(event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //sending the coordinates to new variables to be manipulated
                userX = touchX;
                userY = touchY;
                break;
        }
        return true;
    }

    public void checkWin(Canvas canvas) {
    //this function is constantly called to see if the player touches the right color
    //unfortunately, the colors are hard coded into specific positions
    //I wish to make the circles appear in random places but how I would check if the user touched the right one?
        switch(objective) {
            //checking the x-coordinates
            case "Touch blue!":
                if (userX > circlePosLeft - circleRadius && userX < circlePosLeft + circleRadius) {
                    //checking the y-coordinates
                    if (userY > circlePosMiddleY - circleRadius && userY < circlePosMiddleY + circleRadius) {
                        //if the touch is contained in the circles position then:
                        gagnez(canvas);
                    }
                }
                else if (userX == 0.0 && userY == 0.0) {
                    }
                //this is for when the minigame first starts up, no touch is detected so no win or lose
                else {
                    //if the x-coordinates are not contained then they lost anyway
                    perdez(canvas);
                } break;
            case "Touch red!":
                if (userX > circlePosRight - circleRadius && userX < circlePosRight + circleRadius) {
                    //checking the y-coordinates
                    if (userY > circlePosMiddleY - circleRadius && userY < circlePosMiddleY + circleRadius) {
                        //if the touch is contained in the circles position then:
                        gagnez(canvas);
                    }
                }
                else if (userX == 0.0 && userY == 0.0) {
                }
                //this is for when the minigame first starts up, no touch is detected so no win or lose
                else {
                    //if the x-coordinates are not contained then they lost anyway
                    perdez(canvas);
                } break;
            case "Touch green!":
                if (userX > circlePosMiddleX- circleRadius && userX < circlePosMiddleX + circleRadius) {
                    //checking the y-coordinates
                    if (userY > circlePosBottom - circleRadius && userY < circlePosBottom + circleRadius) {
                        //if the touch is contained in the circles position then:
                        gagnez(canvas);
                    }
                }
                else if (userX == 0.0 && userY == 0.0) {
                }
                //this is for when the minigame first starts up, no touch is detected so no win or lose
                else {
                    //if the x-coordinates are not contained then they lost anyway
                    perdez(canvas);
                } break;
            case "Touch yellow!":
                if (userX > circlePosMiddleX - circleRadius && userX < circlePosMiddleX + circleRadius) {
                    //checking the y-coordinates
                    if (userY > circlePosTop - circleRadius && userY < circlePosTop + circleRadius) {
                        //if the touch is contained in the circles position then:
                        gagnez(canvas);
                    }
                }
                else if (userX == 0.0 && userY == 0.0) {
                }
                //this is for when the minigame first starts up, no touch is detected so no win or lose
                else {
                    //if the x-coordinates are not contained then they lost anyway
                    perdez(canvas);
                } break;
        }
    }

    public void gagnez(Canvas canvas) {
    //when the player fulfills the objective this method is called
    //easier than copying and pasting the following four times
        gameWin(canvas); //the win message will appear
        doomTimer.cancel();
        changeGame();
    }

    public void perdez(Canvas canvas) {
    //for when the player fails the objective
        gameLose(canvas);
        doomTimer.cancel();
        changeGame();
    }
}

package com.example.jonathan.heylisten;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class gameCatch extends Controls {
//need to extend view to call the canvas
    public gameCatch(Context context) {
        super(context);
        //getting context like in JavaScript
        timer(true);
    }

    protected void onDraw(Canvas canvas) {
        drawTimer(canvas);
        gameObjective("Catch it!", canvas);
        troubleshoot(canvas);

        drawPaddle(canvas);
        drawBall(canvas);

    //ball movement
        ballX += dBallX;
        ballY += dBallY;
        collisionDetection(canvas);
        postInvalidate();

    }
//the following grabs the dimensions of the device screen so that I can use relative positions when positioning


//Paddle dimensions
    float paddlePositionX = (screenWidth)/3; //the paddles initial position
    float paddleRight = paddlePositionX+200;
    float paddleTop = screenHeight-250;
    float paddleBottom = paddleTop+50;
//Ball dimensions
    float ballRadius = 50;
    float ballX = screenWidth/2;
    float ballY = screenHeight/2;
    //ball speeds
    float dBallX = -10;
    float dBallY = 15;

    private void drawPaddle(Canvas canvas) {
        paint.setColor(Color.rgb(162, 222, 208));
        paint.setStrokeWidth(3);
        canvas.drawRect(paddlePositionX, paddleTop, paddleRight, paddleBottom, paint);
        //rect(left edge, top edge, right edge, bottom edge)
        //circle(x, y, r, paint)
    }

    public void drawBall(Canvas canvas) {
//code to redraw the ball

        paint.setColor(Color.rgb(239, 72, 54));
        canvas.drawCircle(ballX, ballY, ballRadius, paint);
        //.arc(x, y, r, sAngle, eAngle, ccw)
        //the ball starts at a definite position with a previously declared radius

    }

//handling collision detection
    public void collisionDetection(Canvas canvas) {
        if(ballX+ballRadius == screenWidth || ballX-ballRadius == 0){
        //need to account for ball radius to improve 'bounce' look
            dBallX = -dBallX;
        }
        if (ballY+ballRadius == paddleTop) {
            if (ballX > paddlePositionX && ballX < paddleRight) {
                dBallX = paddlePositionX;
                dBallY = paddleTop;
                gameWin(canvas);
                doomTimer.cancel();
                //it will check the ball's x-position is between contained within the paddle's x-position then the ball's direction will reverse, but...
                changeGame();
            }
        }
        if (ballY >= screenHeight) {
            gameLose(canvas);
            doomTimer.cancel();
            changeGame();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
    //handles touch input
        float touchX = event.getX(); //getting users X touch coordinate
        float touchY = event.getY(); //getting users Y touch coordinate

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
        //to constantly get the X position of sliding gestures
            case MotionEvent.ACTION_MOVE:
                //_MOVE for slides and _DOWN for taps
                userX = touchX; //converted for troubleshooting
                userY = touchY; //converted for troubleshooting

                paddlePositionX = userX-100; //changes paddle position according to finger position
                    //the -100 is to place the person's finger in the middle of the paddle
                paddleRight = userX+100; //this moves the right side of the paddle
                break;
        }
        return true;
    }
}

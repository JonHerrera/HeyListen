package com.example.jonathan.heylisten;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

public class gameMash extends Controls {

    public gameMash(Context context) {
        super(context);
        timer(true);
    }

    public void onDraw(Canvas canvas) {
        drawTimer(canvas);
        gameObjective("Mash the button!", canvas);
    }

    public void makeButton(Canvas canvas) {
        paint.setColor(Color.rgb(246, 71, 71));
        canvas.drawCircle(screenWidth/2, screenHeight/2, 250, paint);
    }
}

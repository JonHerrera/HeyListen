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
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //this line will put the app into fullscreen mode (only hides the status bar)
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //these lines hide the actionBar, but just for this activity
        setContentView(R.layout.activity_home_screen);

        initializeButtonActivity();
        //so the buttons on the main screen work
    }
    
//The following handles the button activity. I spent soooo long trying to move it into another class but I couldn't get it to work
    //I think the problem lies with trying to make the other class an object then calling the method within the class
    public void initializeButtonActivity() {
        //this method handles all button clicks
        //doing it the harder way as opposed to the XML way

        //the following gets each button through their assigned ID
        final Button listenPlay = (Button) findViewById(R.id.btnPlay);
        final Button listenRecords = (Button) findViewById(R.id.btnRecords);
        final Button listenOptions = (Button) findViewById(R.id.btnOptions);
        final Button listenCredits = (Button) findViewById(R.id.btnCredits);
        final Button listenQuit = (Button) findViewById(R.id.btnQuit);

        //setting a listener for each button
        listenPlay.setOnClickListener(new View.OnClickListener() {
            //the listener is attached to a function for each button
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, Play.class);
                startActivity(i);
            }
        });

        listenRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, Records.class);
                startActivity(i);
            }
        });

        listenOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, Options.class);
                startActivity(i);
            }
        });

        listenCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(HomeScreen.class, CreditsScreen.class);
//                startActivity(i);
                Toast.makeText(HomeScreen.this, "Screen not yet made but this app was made by Jonathan Herrera", Toast.LENGTH_LONG).show();
            }
        });

        listenQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitAlertDialog();
            }
        });
    }

    public void displayToast(View view) {
        String msg = "The screen isn't made yet!";
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();
    }

//for pressing back on the main screen
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            exitAlertDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exitAlertDialog() {
        //to display the exit alert dialog
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        //.Builder(where it will appear)
        //like Toast and Intent, AlertDialog is a thing
        //we use a Builder because there could be many objects on the Alert Dialog and so we call this constructor

        //we create each button corresponding to the order they appear in
        b.setTitle("Exit");
        b.setIcon(android.R.drawable.star_big_on);
        b.setMessage("Are you sure you want to quit?");
        //the Yes button is known as the Positive Button
        b.setPositiveButton("Yeah", new DialogInterface.OnClickListener() {
            //this is a button so we need to make a listener
            //(text to display, click acceptor)
            //this is known an as Anonymous class/method cause its built into another method
            public void onClick(DialogInterface dialog, int which){
                //(passing the dialog, a binary option yes or no)
                finish();
                //this is embedded in the Builder's library to close the application
            }
        });
        //the No button is known as the Negative Button
        b.setNegativeButton("Nah", null);
        //(message, goes back to regular activity)
        AlertDialog alertDialog = b.create();
        //we are not taking all the components we made and putting it together into an Alert Dialog
        alertDialog.show();
        //actually shows what we have created
    }
}

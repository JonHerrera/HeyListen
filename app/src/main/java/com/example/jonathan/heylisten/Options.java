package com.example.jonathan.heylisten;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_options);

        initSwitch();
    }

    public void initSwitch() {
        final Switch switchSound = (Switch) findViewById(R.id.switchSound);
        final Switch switchMusic = (Switch) findViewById(R.id.switchMusic);

        //instead of an OnClick for a button, we use OnCheckedChange for a switch
        switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isOn) {
                if(isOn) {
                //when sound is turned on
                    soundNotification();
                }
                else {
                //when sound is turned off
                    soundNotification();
                }
            }
        });

        switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isOn) {
                if(isOn) {
                    musicNotification();
                }
                else {
                    musicNotification();
                }
            }
        });

    }

    public void soundNotification() {
        String msg = "Sorry, there's no sounds yet!";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void musicNotification() {
        String msg = "Sorry, there's no music yet!";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

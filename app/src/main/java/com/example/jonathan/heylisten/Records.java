package com.example.jonathan.heylisten;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Records extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_records);

        //Android documentation says that apps should not include a back button because there is one on every device
//        initButton();
        //for the back button
        //if I can figure out how to call methods from other activities then I wouldn't have to program the button every activity
    }

//    public void initButton() {
//        final Button btnBack = (Button) findViewById(R.id.btnBack);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Records.this, HomeScreen.class);
//                startActivity(i);
//                //this method is embedded in the android library
//            }
//        });
//    }
}

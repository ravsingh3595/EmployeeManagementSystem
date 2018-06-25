package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.com.androidproject.employeemanagementsystem.R;


public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.activi);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer.
             */

            @Override
            public void run() {

                Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}

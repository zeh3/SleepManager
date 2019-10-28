package com.example.sleepmanager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    /** the boolean that shows whether or not notifications are on. */
    private Switch notifications;
    /** the boolean that shows which mode the app is in (light or dark). */
    private Switch darkMode;
    /** the boolean that shows whether or not dynamic notifications are on. */
    private Switch dynamicNotifications;

    private Switch goToBedSwitch;

    private Switch putInDataSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        darkMode = findViewById(R.id.darkModeSwitch);
        notifications = findViewById(R.id.notificationsSwitch);
        dynamicNotifications = findViewById(R.id.dynamicNotificationsSwitch);
        goToBedSwitch = findViewById(R.id.goToBedSwitch);
        putInDataSwitch = findViewById(R.id.lastNightDataSwitch);

        darkMode.setTextOff("Off");
        darkMode.setTextOn("On");



        LinearLayout notificationsLayout = findViewById(R.id.notificationsLayout);
        notificationsLayout.setVisibility(View.VISIBLE);
        LinearLayout modeLayout = findViewById(R.id.modeLayout);
        modeLayout.setVisibility(View.VISIBLE);
        LinearLayout dynamicNotificationsLayout = findViewById(R.id.dynamicNotificationsLayout);
        dynamicNotificationsLayout.setVisibility(View.INVISIBLE);



        notifications.setOnClickListener(unused -> {
            //notificationsOn = notifications.isChecked();
            if (notifications.isChecked()) {
                dynamicNotificationsLayout.setVisibility(View.VISIBLE);
                if (!goToBedSwitch.isChecked()) {
                    dynamicNotifications.setVisibility(View.INVISIBLE);
                }
            } else {

                dynamicNotificationsLayout.setVisibility(View.INVISIBLE);

                dynamicNotifications.setChecked(false);
                goToBedSwitch.setChecked(false);
            }
        });

        goToBedSwitch.setOnClickListener(unused -> {
            if (goToBedSwitch.isChecked()) {
                dynamicNotifications.setVisibility(View.VISIBLE);
            } else {
                dynamicNotifications.setVisibility(View.INVISIBLE);
                dynamicNotifications.setChecked(false);
            }
        });

        Button home = findViewById(R.id.home);
        home.setOnClickListener(unused -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        darkMode.setOnClickListener(unused -> {
            if(darkMode.isChecked()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });



        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}

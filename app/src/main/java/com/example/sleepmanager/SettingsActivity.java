package com.example.sleepmanager;

import android.content.Intent;
import android.os.Bundle;

/*
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
*/

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity {

    /** the Switch that shows whether or not notifications are on. */
    private Switch notifications;
    /** the Switch that shows which mode the app is in (light or dark). */
    private Switch darkMode;
    /** the Switch that shows whether or not dynamic notifications are on. */
    private Switch dynamicNotifications;

    private Switch goToBedSwitch;

    private Switch putInDataSwitch;

    private DatabaseReference mDatabase;

    private String user;

    private Settings settings;

    LinearLayout notificationsLayout;

    LinearLayout modeLayout;

    LinearLayout dynamicNotificationsLayout;

    EditText adjustEveryXDays;

    EditText minutesEarlier;

    LinearLayout xDaysLayout;

    LinearLayout minutesEarlierLayout;


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
        adjustEveryXDays = findViewById(R.id.adjustEveryXDays);
        minutesEarlier = findViewById(R.id.minutesEarlier);
        xDaysLayout = findViewById(R.id.xDaysLayout);
        minutesEarlierLayout = findViewById(R.id.minutesEarlierLayout);


        user = "default";





        //darkMode.setTextOff("Off");
        //darkMode.setTextOn("On");

        //darkMode.setChecked(mDatabase.child("users").child(user).child("darkMode"));

        //darkMode.setChecked(true);

        notificationsLayout = findViewById(R.id.notificationsLayout);
        notificationsLayout.setVisibility(View.VISIBLE);
        modeLayout = findViewById(R.id.modeLayout);
        modeLayout.setVisibility(View.VISIBLE);
        dynamicNotificationsLayout = findViewById(R.id.dynamicNotificationsLayout);
        dynamicNotificationsLayout.setVisibility(View.INVISIBLE);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference justSettings = mDatabase.child("users").child(user).child("settings");

        ValueEventListener settingsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                settings = dataSnapshot.getValue(Settings.class);
                switchSwitches();
                Log.d("tag2", "listening");

                if (settings == null) {
                    settings = new Settings();
                    mDatabase.child("users").child(user).child("settings").setValue(settings);
                }

                //turn switches on/off accordingly
                //witchSwitches();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        justSettings.addValueEventListener(settingsListener);

        //ValueEventListener firstListener =

        //get settings object
        //String set = mDatabase.child("users").child(user).child("settings").toString();
        //System.out.println(set);
        //Log.d("tag", set);
        //if settings == null then initialize to default values and push to database


        Button home = findViewById(R.id.home);
        home.setOnClickListener(unused -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        notifications.setOnClickListener(unused -> {
            //notificationsOn = notifications.isChecked();
            if (notifications.isChecked()) {
                dynamicNotificationsLayout.setVisibility(View.VISIBLE);
                if (!goToBedSwitch.isChecked()) {
                    dynamicNotifications.setVisibility(View.INVISIBLE);
                }
                //mDatabase.child("users").child(user).child("settings").child("notifications").setValue(true);
                settings.setNotifications(true);
                mDatabase.child("users").child(user).child("settings").setValue(settings);

            } else {
                dynamicNotificationsLayout.setVisibility(View.INVISIBLE);

                dynamicNotifications.setChecked(false);
                goToBedSwitch.setChecked(false);
                putInDataSwitch.setChecked(false);

                settings.setNotifications(false);
                settings.setDynamicNotifications(false);
                settings.setGoToBedNotifications(false);
                settings.setPutInDataNotifications(false);

                mDatabase.child("users").child(user).child("settings").setValue(settings);
            }
        });

        goToBedSwitch.setOnClickListener(unused -> {
            if (goToBedSwitch.isChecked()) {
                dynamicNotifications.setVisibility(View.VISIBLE);

                settings.setGoToBedNotifications(true);

                mDatabase.child("users").child(user).child("settings").setValue(settings);
            } else {
                dynamicNotifications.setVisibility(View.INVISIBLE);
                dynamicNotifications.setChecked(false);

                settings.setGoToBedNotifications(false);
                settings.setDynamicNotifications(false);

                mDatabase.child("users").child(user).child("settings").setValue(settings);
            }
        });



        darkMode.setOnClickListener(unused -> {
            if(darkMode.isChecked()) {
                settings.setDarkMode(true);

                mDatabase.child("users").child(user).child("settings").setValue(settings);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                settings.setDarkMode(false);

                mDatabase.child("users").child(user).child("settings").setValue(settings);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        putInDataSwitch.setOnClickListener(unused -> {
            if (putInDataSwitch.isChecked()) {

                settings.setPutInDataNotifications(true);

                mDatabase.child("users").child(user).child("settings").setValue(settings);
                //mDatabase.child("users").child(user).child("settings").child("putInDataNotifications").setValue(true);
            } else {
                settings.setPutInDataNotifications(false);

                mDatabase.child("users").child(user).child("settings").setValue(settings);
                //mDatabase.child("users").child(user).child("settings").child("putInDataNotifications").setValue(true);
            }
        });

        dynamicNotifications.setOnClickListener(unused -> {
            if (dynamicNotifications.isChecked()) {
                adjustEveryXDays.setVisibility(View.VISIBLE);

                settings.setDynamicNotifications(true);
                mDatabase.child("users").child(user).child("settings").setValue(settings);
                //mDatabase.child("users").child(user).child("settings").child("dynamicNotifications").setValue(true);
            } else {
                adjustEveryXDays.setVisibility(View.INVISIBLE);

                settings.setDynamicNotifications(false);
                mDatabase.child("users").child(user).child("settings").setValue(settings);
                //mDatabase.child("users").child(user).child("settings").child("dynamicNotifications").setValue(false);
            }

        });

        minutesEarlier.setOnClickListener(unused -> {
            int set = Integer.parseInt(minutesEarlier.getText().toString());
            settings.setMinutesEarlier(set);
            mDatabase.child("users").child(user).child("settings").setValue(settings);
        });

        adjustEveryXDays.setOnClickListener(unused -> {
            int set = Integer.parseInt(adjustEveryXDays.getText().toString());
            settings.setAdjustEveryXDays(set);
            mDatabase.child("users").child(user).child("settings").setValue(settings);
        });

    }

    /*private void setSettings(boolean darkMode, boolean goToBed, boolean goToBedEarlier,
                             boolean notifications, boolean putInLastNight) {
        mDatabase.child("users").child("user").child("settings").child("settings").child("darkMode").setValue(darkMode);
    }*/
    private void switchSwitches() {
        Log.d("tag2", "switching");
        Log.d("tag2", settings.toString());
        darkMode.setChecked(settings.getDarkMode());
        dynamicNotifications.setChecked(settings.getDynamicNotifications());
        goToBedSwitch.setChecked(settings.getGoToBedNotifications());
        notifications.setChecked(settings.getNotifications());
        putInDataSwitch.setChecked(settings.getPutInDataNotifications());
        adjustEveryXDays.setText("" + settings.getAdjustEveryXDays());
        minutesEarlier.setText("" + settings.getMinutesEarlier());

        if (notifications.isChecked()) {
            dynamicNotificationsLayout.setVisibility(View.VISIBLE);
        } else {
            dynamicNotificationsLayout.setVisibility(View.INVISIBLE);
        }

        if (dynamicNotifications.isChecked()) {
            //adjustEveryXDays.setVisibility(View.VISIBLE);
            //minutesEarlier.setVisibility(View.VISIBLE);

            minutesEarlierLayout.setVisibility(View.VISIBLE);
            xDaysLayout.setVisibility(View.VISIBLE);
        } else {
            //adjustEveryXDays.setVisibility(View.INVISIBLE);
            //minutesEarlier.setVisibility(View.INVISIBLE);

            minutesEarlierLayout.setVisibility(View.INVISIBLE);
            xDaysLayout.setVisibility(View.INVISIBLE);
        }

        if (goToBedSwitch.isChecked()) {
            dynamicNotifications.setVisibility(View.VISIBLE);
        } else {
            dynamicNotifications.setVisibility(View.INVISIBLE);
        }
    }

}

package com.example.sleepmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private String id;
    private Settings appSettings;
    int adjustMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Test
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        id = FirebaseAuth.getInstance().getUid();
        getSettings();

        Button settings = findViewById(R.id.otherSettings);
        settings.setOnClickListener(unused -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("user", id);
            startActivity(intent);
        });
        Button inputpage = findViewById(R.id.inputpage);
        inputpage.setOnClickListener(unused -> {
            Intent intent = new Intent(this, inputpage.class);
            intent.putExtra("user", id);
            startActivity(intent);
        });


        Button graph = findViewById(R.id.graph);
        graph.setOnClickListener(unused -> {
            Intent intent = new Intent(this, graph.class);
            intent.putExtra("user", id);
            startActivity(intent);
        });

        Intent alarmIntent = new Intent (this, AlarmReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


        adjustMillis = 0;
        if (appSettings.getDynamicNotifications()) {
            adjustMillis = 1000 * 60 * appSettings.getMinutesEarlier();
        }

        Intent otherAlarmIntent = new Intent(this, NewAlarmReceiver.class);
        PendingIntent otherPendingIntent = PendingIntent.getBroadcast(this, 0, otherAlarmIntent, 0);
        AlarmManager newManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        newManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY - adjustMillis, otherPendingIntent);



    }

    private void getSettings() {
        DatabaseReference justSettings = FirebaseDatabase.getInstance().getReference().child("users").child(id).child("settings");

        ValueEventListener settingsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 appSettings = dataSnapshot.getValue(Settings.class);
                if (appSettings == null) {
                    appSettings = new Settings();
                    justSettings.setValue(appSettings);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        justSettings.addValueEventListener(settingsListener);


    }

}

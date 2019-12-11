package com.example.sleepmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Test
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        id = FirebaseAuth.getInstance().getUid();

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
    }

}

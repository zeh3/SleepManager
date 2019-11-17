package com.example.sleepmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Test
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        Button settings = findViewById(R.id.otherSettings);
        settings.setOnClickListener(unused -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        Button startSleeping = findViewById(R.id.startTiming);
        startSleeping.setOnClickListener(unused -> {
            Intent intent = new Intent(this, StartTiming.class);
            startActivity(intent);
        });

        Button graph = findViewById(R.id.graph);
        graph.setOnClickListener(unused -> {
            Intent intent = new Intent(this, graph.class);
            startActivity(intent);
        });
    }

}

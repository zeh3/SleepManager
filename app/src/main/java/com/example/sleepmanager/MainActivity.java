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
        setContentView(R.layout.activity_main);

        Button settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        });
    }
}

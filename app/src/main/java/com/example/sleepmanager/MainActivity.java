package com.example.sleepmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

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
    }

}

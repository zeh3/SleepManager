
package com.example.sleepmanager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class graph extends AppCompatActivity {
    BarChart b;
    Map<String, Double> data= new HashMap<>();
    List<String> today = new ArrayList<>();
    private String id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        Intent intent = getIntent();
        id = intent.getStringExtra("user");
        getData();
        b = findViewById(R.id.bar);
        ConstraintLayout m = findViewById(R.id.ab);
        Button bot = m.findViewById(R.id.bot);
        bot.setOnClickListener(unused -> {
            graph1();
        });
        Button month = m.findViewById(R.id.mon);
        month.setOnClickListener(unused -> {
            graph2();
        });
        Button week = m.findViewById(R.id.week);
        week.setOnClickListener(unused -> {
                    graph3();
        });
    }
    private void getData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(id).child("UsersData");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    data.put((dataSnapshot.getValue(UsersData.class).getToday()),(dataSnapshot.getValue(UsersData.class).getSlept()));
                    today.add((dataSnapshot.getValue(UsersData.class).getToday()));
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
    void graph1() {

        ArrayList<BarEntry> m = new ArrayList<>();
        ArrayList<String> j = new ArrayList<>();
        Toast.makeText(this, "data was pulled properly", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < today.size(); i++) {

            DateFormat dateFormat = new SimpleDateFormat("mm-dd") {
            };
            String h = today.get(i);
            double g = data.get(h);
            j.add(h);
            BarEntry n = new BarEntry((float)g, i);
            m.add(n);
        }
        BarDataSet l =new BarDataSet(m,"Dates");
        BarData k = new BarData((IBarDataSet)j, l);
        b.setData(k);
    }
    void graph2() {
        ArrayList<BarEntry> m = new ArrayList<>();
        ArrayList<String> j = new ArrayList<>();
        for (int i = today.size(); i > today.size()- 30; i--) {

            DateFormat dateFormat = new SimpleDateFormat("mm-dd") {
            };
            String h = today.get(i);
            double g = data.get(h);
            j.add(h);
            BarEntry n = new BarEntry((float) g, i);
            m.add(n);
        }
        BarDataSet l = new BarDataSet(m, "Dates");
        BarData k = new BarData((IBarDataSet) j, l);
        b.setData(k);
    }
    void graph3() {
        ArrayList<BarEntry> m = new ArrayList<>();
        ArrayList<String> j = new ArrayList<>();
        for (int i = today.size(); i > today.size()- 7; i--) {

            DateFormat dateFormat = new SimpleDateFormat("mm-dd") {
            };
            String h = today.get(i);
            double g = data.get(h);
            j.add(h);
            BarEntry n = new BarEntry((float)g, i);
            m.add(n);
        }
        BarDataSet l =new BarDataSet(m,"Dates");
        BarData k = new BarData((IBarDataSet)j, l);
        b.setData(k);
    }
}

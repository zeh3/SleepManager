
package com.example.sleepmanager;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    ImageView beginning1;
    ImageView month1;
    ImageView week1;
    private String id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        Intent intent = getIntent();
        id = intent.getStringExtra("user");
        getData();
        beginning1 = findViewById(R.id.beginning);
        month1= findViewById(R.id.month);
        week1= findViewById(R.id.week);

        ConstraintLayout m = findViewById(R.id.ab);
        Button bot = m.findViewById(R.id.bot);
        bot.setOnClickListener(unused -> {
            graph21();
        });
        Button month = m.findViewById(R.id.mon);
        month.setOnClickListener(unused -> {
            graph22();
        });
        Button week = m.findViewById(R.id.wee);
        week.setOnClickListener(unused -> {
            graph23();
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

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> theDates = new ArrayList<>();
        BarEntry barEntry;
        for (int i = 0; i < today.size(); i++) {
            DateFormat dateFormat = new SimpleDateFormat("mm-dd") {};
            String h = today.get(i);
            double g = data.get(h);
            theDates.add(h);
            barEntry= new BarEntry((float)g, i);
            barEntries.add(barEntry);
        }
        BarDataSet barDataSet =new BarDataSet(barEntries,"Dates");
        BarData k = new BarData((IBarDataSet)theDates, barDataSet);
        //b.setData(k);
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
        //b.setData(k);
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
        //b.setData(k);
    }

    void graph21() {
        beginning1.setVisibility(View.VISIBLE);
        beginning1.setImageResource(R.drawable.alltime);
        month1.setVisibility(View.INVISIBLE);
        week1.setVisibility(View.INVISIBLE);
    }
    void graph22() {
        month1.setVisibility(View.VISIBLE);
        month1.setImageResource(R.drawable.month);
        beginning1.setVisibility(View.INVISIBLE);
        week1.setVisibility(View.INVISIBLE);
    }
    void graph23() {
        week1.setVisibility(View.VISIBLE);
        week1.setImageResource(R.drawable.week);
        month1.setVisibility(View.INVISIBLE);
        beginning1.setVisibility(View.INVISIBLE);
    }
}
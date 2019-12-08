package com.example.sleepmanager;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


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
import java.util.List;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class graph extends AppCompatActivity {
    BarChart b;
    List<Double> hours = new ArrayList<>();
    List<Date> today = new ArrayList<Date>();
    private String id;
    private void getData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child(id).child("usersData");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    hours.add(dataSnapshot.getValue(UsersData.class).getSlept());
                    today.add(dataSnapshot.getValue(UsersData.class).getToday());
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
    protected void onCreate() {
        setContentView(R.layout.graph);
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        id = intent.getStringExtra("user");
        getData();
        b = b.findViewById(R.id.bar);
        ConstraintLayout m = findViewById(R.id.ab);
        Button n = m.findViewById(R.id.bot);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    graph1();
                }
                catch(Exception q) {
                }
            }
        });
        Button o = m.findViewById(R.id.mon);
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    graph2();
                }
                catch(Exception q) {
                }
            }
        });
        Button p = m.findViewById(R.id.week);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    graph3();
                }
                catch(Exception q) {
                }
            }
        });
    }
    void graph1() {

        ArrayList<BarEntry> m = new ArrayList<>();
        ArrayList<String> j = new ArrayList<>();
        for (int i = 0; i < hours.size(); i++) {

            DateFormat dateFormat = new SimpleDateFormat("mm-dd") {
            };
            String h = dateFormat.format(today.get(i));
            double g = hours.get(i);
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
        for (int i = hours.size(); i > hours.size()- 30; i--) {

            DateFormat dateFormat = new SimpleDateFormat("mm-dd") {
            };
            String h = dateFormat.format(today.get(i));
            double g = hours.get(i);
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
        for (int i = hours.size(); i > hours.size()- 7; i--) {

            DateFormat dateFormat = new SimpleDateFormat("mm-dd") {
            };
            String h = dateFormat.format(today.get(i));
            double g = hours.get(i);
            j.add(h);
            BarEntry n = new BarEntry((float)g, i);
            m.add(n);
        }
        BarDataSet l =new BarDataSet(m,"Dates");
        BarData k = new BarData((IBarDataSet)j, l);
        b.setData(k);
    }
}

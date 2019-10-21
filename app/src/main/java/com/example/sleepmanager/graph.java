package com.example.sleepmanager;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class graph extends MainActivity {
    BarChart b;
    protected void onCreate(final JsonObject c) {
        setContentView(R.layout.graph);
        b = b.findViewById(R.id.bar);
        ConstraintLayout m = findViewById(R.id.ab);
        Button n = m.findViewById(R.id.bot);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    graph1(c);
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
                    graph2(c);
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
                    graph3(c);
                }
                catch(Exception q) {
                }
            }
        });
    }
    void graph1(JsonObject c) {
        JsonArray d = c.getAsJsonArray("info");
        ArrayList<BarEntry> i = new ArrayList<>();
        ArrayList<String> j = new ArrayList<>();
        for (JsonElement e : d) {
            JsonObject f = e.getAsJsonObject();
            String g = f.get("date").getAsString();
            float h = f.get("time").getAsFloat();
            i.add(new BarEntry(h, Float.parseFloat(g)));
            j.add(g);
        }
        BarDataSet l =new BarDataSet(i,"Dates");
        BarData k = new BarData((IBarDataSet) j, l);
        b.setData(k);
    }
    void graph2(JsonObject c) {
        JsonArray d = c.getAsJsonArray("info");
        ArrayList<BarEntry> i = new ArrayList<>();
        ArrayList<String> j = new ArrayList<>();
        int count = 0;
        for (int x = d.size(); x > 0; x --) {
            JsonElement e = d.get(x);
            JsonObject f = e.getAsJsonObject();
            String g = f.get("date").getAsString();
            float h = f.get("time").getAsFloat();
            i.add(new BarEntry(h, Float.parseFloat(g)));
            j.add(g);
            count++;
            if (count > 30 ) {
                break;
            }
        }
        BarDataSet l =new BarDataSet(i,"Dates");
        BarData k = new BarData((IBarDataSet) j,l);
        b.setData(k);
    }
    void graph3(JsonObject c) {
        JsonArray d = c.getAsJsonArray("info");
        ArrayList<BarEntry> i = new ArrayList<>();
        ArrayList<String> j = new ArrayList<>();
        int count = 0;
        for (int x = d.size(); x > 0; x --) {
            JsonElement e = d.get(x);
            JsonObject f = e.getAsJsonObject();
            String g = f.get("date").getAsString();
            float h = f.get("time").getAsFloat();
            i.add(new BarEntry(h, Float.parseFloat(g)));
            j.add(g);
            count++;
            if (count > 6 ) {
                break;
            }
        }
        BarDataSet l =new BarDataSet(i,"Dates");
        BarData k = new BarData((IBarDataSet) j,l);
        b.setData(k);
    }
    protected void main(JsonObject a) {
        onCreate(a);
    }
}

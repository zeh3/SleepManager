package com.example.sleepmanager;

import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class inputpage extends MainActivity{
    public void onCreate() {
        setContentView(R.layout.inputpage);
        EditText sleep = findViewById(R.id.sleep);
        EditText wakeup = findViewById(R.id.wakeup);
        EditText date = findViewById(R.id.date);
        String sleep1 = sleep.getText().toString();
        String wakeup1 = wakeup.getText().toString();
        String date1 = date.getText().toString();
        Date sleep2 = format2(sleep1);
        Date wakeup2 = format2(wakeup1);
        Date date2 = format1(date1);
        long sleep3 = sleep2.getTime();
        long wakeup3 = wakeup2.getTime();
        long slept = wakeup3 - sleep3;
        double hrs_slept = slept/(60*60);
        input(hrs_slept, date2, sleep2, wakeup2);
    }
    public void input( double slept, Date today, Date sleep, Date wakeup) {
        // to put the data in the firebase
        Map<String, UsersData> users = new HashMap<>();
        DatabaseReference a = FirebaseDatabase.getInstance().getReference();
        DatabaseReference b = a.child("sleepWakes");
        UsersData sleepdata = new UsersData(slept, today, sleep, wakeup);
        users.put(id, sleepdata);
        b.setValueAsync(users);
    }
    public class UsersData {
        public double slept;
        public Date today;
        public Date sleep;
        public Date wakeup;
        UsersData(double a, Date b, Date c, Date d) {
            slept = a;
            today = b;
            sleep = c;
            wakeup = d;
        }
    }
    public Date format1(String a) {
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date sleep2 = new Date();
        try {
            sleep2 = format.parse(a);
        } catch (Exception z){
        }
        return sleep2;
    }
    public Date format2(String a) {
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm");
        Date sleep2 = new Date();
        try {
            sleep2 = format.parse(a);
        } catch (Exception z){
        }
        return sleep2;
    }
    public void initial() {
        id = "dhruvr4";
        List<Date> today = new ArrayList<Date>();
        for (int i = 1;i <= 30; i++) {
            today.add(format1(i+ "/10/2019"));
        }
        List<Date> sleep = new ArrayList<Date>();
        for (int i = 1;i <= 30; i++) {
            sleep.add(format2(i+ "/10/2019 10:00"));
        }
        List<Date> wakeup = new ArrayList<Date>();
        for (int i = 2;i <= 31; i++) {
            wakeup.add(format2(i + "/10/2019 6:00"));
        }
        int[] slept = new int[30];
        for (int i = 1;i <= 30; i++) {
            slept[i] = 8;
        }
        for (int i = 0; i <31 ; i++) {
            double slept1 = (double) slept[i];
            Date today1 = today.get(i);
            Date wakeup1 = wakeup.get(i);
            Date sleep1 = sleep.get(i);
            input(slept1, today1, sleep1, wakeup1);
        }
    }
    private String id;
    public void main(String a) {
        initial();
        id = a;
        onCreate();
    }
}

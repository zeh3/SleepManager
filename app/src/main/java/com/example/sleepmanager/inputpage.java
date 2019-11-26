package com.example.sleepmanager;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class inputpage extends AppCompatActivity{

    private String id;
    public void onCreate() {
        initial();
        Intent intent = getIntent();
        id = intent.getStringExtra("user");
        setContentView(R.layout.inputpage);
        Button saveData = findViewById(R.id.saveData);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    EditText sleep = findViewById(R.id.sleep);
                    EditText wakeup = findViewById(R.id.wakeup);
                    EditText datesleep = findViewById(R.id.date1);
                    EditText datewakeup = findViewById(R.id.date2);
                    String sleep1 = sleep.getText().toString();
                    String wakeup1 = wakeup.getText().toString();
                    String datesleep1 = datesleep.getText().toString();
                    String datewakeup1 = datewakeup.getText().toString();
                    Date sleep2 = format2(sleep1);
                    Date wakeup2 = format2(wakeup1);
                    Date datewakeup2 = format1(datewakeup1);
                    Date sleepfinal = format3(sleep1.concat(" " + datesleep1));
                    Date wakeupfinal = format3(wakeup1.concat(" " + datewakeup1));
                    long sleep3 = sleepfinal.getTime();
                    long wakeup3 = wakeupfinal.getTime();
                    long slept = wakeup3 - sleep3;
                    double hrs_slept = slept/(60*60);
                    input(hrs_slept, datewakeup2, sleep2, wakeup2);
                } catch (Exception q) {
                }
            }
        });
    }
    private List<UsersData> usersdata = new ArrayList<>();
    private UsersData usersData1 = new UsersData();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
    public void input( double slept, Date today, Date sleep, Date wakeup) {
        // to put the data in the firebase
        usersData1 = new UsersData();
        DatabaseReference b = databaseReference.child(id).child("usersData");
        usersData1.setToday(today);
        usersData1.setWakeup(wakeup);
        usersData1.setSleep(sleep);
        usersData1.setSlept(slept);
        usersdata.add(usersData1);
        b.push().setValue(usersData1, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {

            }
        });
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
        DateFormat format = new SimpleDateFormat("hh:mm a");
        Date sleep2 = new Date();
        try {
            sleep2 = format.parse(a);
        } catch (Exception z){
        }
        return sleep2;
    }
    public Date format3(String a) {
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm a");
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
}


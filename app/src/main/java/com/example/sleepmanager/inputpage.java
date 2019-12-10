package com.example.sleepmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private String id="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputpage);

        Intent intent = getIntent();
        id = intent.getStringExtra("user");
        Button saveData = findViewById(R.id.saveData);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = 0;
                EditText sleep = findViewById(R.id.sleep);
                EditText wakeup = findViewById(R.id.wakeup);
                EditText datesleep = findViewById(R.id.date1);
                EditText datewakeup = findViewById(R.id.date2);
                String sleep1 = sleep.getText().toString();
                String[] ar1 = sleep1.split(":");
                if (ar1.length != 2) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for time you went to sleep", Toast.LENGTH_SHORT).show();
                }
                int a1 = 0, a2 = 0;
                try {
                    a1 = Integer.parseInt(ar1[0]);
                    a2 = Integer.parseInt(ar1[1]);
                } catch (Exception e) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for time you went to sleep", Toast.LENGTH_SHORT).show();
                }
                if (a1 < 0 || a1 > 24) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for time you went to sleep", Toast.LENGTH_SHORT).show();
                }
                if (a2 < 0 || a2 > 60) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for time you went to sleep", Toast.LENGTH_SHORT).show();
                }
                String wakeup1 = wakeup.getText().toString();
                String[] ar2 = wakeup1.split(":");
                if (ar2.length != 2) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for time you got up", Toast.LENGTH_SHORT).show();
                }
                int b1 = 0, b2 = 0;
                try {
                    b1 = Integer.parseInt(ar2[0]);
                    b2 = Integer.parseInt(ar2[1]);
                } catch (Exception e) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for time you got up", Toast.LENGTH_SHORT).show();
                }
                if (b1 < 0 || b1 > 24) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for time you got up", Toast.LENGTH_SHORT).show();
                }
                if (b2 < 0 || b2 > 60) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for time you got up", Toast.LENGTH_SHORT).show();
                }
                String datesleep1 = datesleep.getText().toString();
                String[] ar3 = datesleep1.split("/");
                if (ar3.length != 3) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for date you went to sleep", Toast.LENGTH_SHORT).show();
                }
                int c1 = 0, c2 = 0, c3 = 0;
                try {
                    c1 = Integer.parseInt(ar3[0]);
                    c2 = Integer.parseInt(ar3[1]);
                    c3 = Integer.parseInt(ar3[2]);
                } catch (Exception e) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for date you went to sleep", Toast.LENGTH_SHORT).show();
                }
                if (c1 < 1 || c1 > 12) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for date you went to sleep", Toast.LENGTH_SHORT).show();
                }
                if (c1 == 1 || c1 == 3 || c1 == 5 || c1 == 7 || c1 == 8 || c1 == 10 || c1 == 12) {
                    if (c2 < 1 || c2 > 30) {
                        c++;
                        Toast.makeText(inputpage.this, "Invalid Input for date you went to sleep", Toast.LENGTH_SHORT).show();
                    }
                }
                if (c1 == 4 || c1 == 6 || c1 == 9 || c1 == 11) {
                    if (c2 < 1 || c2 > 30) {
                        c++;
                        Toast.makeText(inputpage.this, "Invalid Input for date you went to sleep", Toast.LENGTH_SHORT).show();
                    }
                }
                if (c1 == 2) {
                    if (c2 < 1 || c2 > 28) {
                        c++;
                        Toast.makeText(inputpage.this, "Invalid Input for date you went to sleep", Toast.LENGTH_SHORT).show();
                    }
                }
                if (c3 < 2000 || c3 > 2020) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for date you went to sleep", Toast.LENGTH_SHORT).show();
                }
                String datewakeup1 = datewakeup.getText().toString();
                String[] ar4 = datewakeup1.split("/");
                if (ar4.length != 3) {
                    c++;
                    Toast.makeText(inputpage.this, "Invalid Input for date you got up", Toast.LENGTH_SHORT).show();
                }
                    int d1 = 0, d2 = 0, d3 = 0;
                    try {
                        d1 = Integer.parseInt(ar4[0]);
                        d2 = Integer.parseInt(ar4[1]);
                        d3 = Integer.parseInt(ar4[2]);
                    } catch (Exception e) {
                        c++;
                        Toast.makeText(inputpage.this, "Invalid Input for date you got up", Toast.LENGTH_SHORT).show();
                    }
                    if (d1 < 1 || d1 > 12) {
                        c++;
                        Toast.makeText(inputpage.this, "Invalid Input for date you got up", Toast.LENGTH_SHORT).show();
                    }
                    if (d1 == 1 || d1 == 3 || d1 == 5 || d1 == 7 || d1 == 8 || d1 == 10 || d1 == 12) {
                        if (d2 < 1 || d2 > 30) {
                            c++;
                            Toast.makeText(inputpage.this, "Invalid Input for date you got up", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (d1 == 4 || d1 == 6 || d1 == 9 || d1 == 11) {
                        if (d2 < 1 || d2 > 30) {
                            c++;
                            Toast.makeText(inputpage.this, "Invalid Input for date you got up", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (d1 == 2) {
                        if (d2 < 1 || d2 > 28) {
                            c++;
                            Toast.makeText(inputpage.this, "Invalid Input for date you got up", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (d3 < 2000 || d3 > 2020) {
                        c++;
                        Toast.makeText(inputpage.this, "Invalid Input for date you got up", Toast.LENGTH_SHORT).show();
                    }
                    if (c == 0) {
                        sleep.setText("");
                        wakeup.setText("");
                        datesleep.setText("");
                        datewakeup.setText("");
                        Date sleep2 = format2(sleep1);
                        Date wakeup2 = format2(wakeup1);
                        Date datewakeup2 = format1(datewakeup1);
                        Date sleepfinal = format3(sleep1.concat(" " + datesleep1));
                        Date wakeupfinal = format3(wakeup1.concat(" " + datewakeup1));
                        long sleep3 = sleepfinal.getTime();
                        long wakeup3 = wakeupfinal.getTime();
                        long slept = wakeup3 - sleep3;
                        double hrs_slept = (double) slept / (60 * 60);
                        input(hrs_slept, datewakeup2, sleep2, wakeup2);
                    }
                }
            });
        }
    private List<UsersData> usersdata = new ArrayList<UsersData>();
    private List<Double> timeslept1 = new ArrayList<>();
    private List<Date> dateslept1= new ArrayList<>();
    private List<Date> datewakeup1 = new ArrayList<>();
    private List<Date> today1 = new ArrayList<>();

    private UsersData usersData1 = new UsersData();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
    public void input( double slept, Date today, Date sleep, Date wakeup) {
        // to put the data in the firebase
        usersData1 = new UsersData();
        DatabaseReference b = databaseReference.child("UserData");
        usersData1.setToday(today);
        today1.add(today);
        usersData1.setWakeup(wakeup);
        datewakeup1.add(wakeup);
        usersData1.setSleep(sleep);
        dateslept1.add(sleep);
        usersData1.setSlept(slept);
        timeslept1.add(slept);
        usersdata.add(usersData1);


        b.child("UsersData1").push().setValue(usersData1, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                Toast.makeText(getApplicationContext(), "Data has been inputted", Toast.LENGTH_SHORT).show();
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
        DateFormat format = new SimpleDateFormat("hh:mm");
        Date sleep2 = new Date();
        try {
            sleep2 = format.parse(a);
        } catch (Exception z){
        }
        return sleep2;
    }
    public Date format3(String a) {
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
}

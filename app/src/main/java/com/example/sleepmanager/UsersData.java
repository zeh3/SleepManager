package com.example.sleepmanager;

import java.util.Date;

public class UsersData {
    public double slept;
    public String today;
    public String sleep;
    public String wakeup;
    UsersData(double a, String b, String c, String d) {
        slept = a;
        today = b;
        sleep = c;
        wakeup = d;
    }
    UsersData() {
    }
    public String getSleep() {
        return sleep;
    }
    public String getToday() {
        return today;
    }
    public double getSlept() {
        return slept;
    }
    public String getWakeup() {
        return wakeup;
    }
    public void setSleep(String slee) {
        this.sleep = slee;
    }
    public void setSlept(double slep) {
        this.slept = slep;
    }
    public void setToday(String toda) {
        this.today = toda;
    }
    public void setWakeup(String wakeu) {
        this.wakeup = wakeu;
    }
}


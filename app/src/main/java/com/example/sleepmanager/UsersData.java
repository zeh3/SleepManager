package com.example.sleepmanager;

import java.util.Date;

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
    UsersData() {
    }
    public Date getSleep() {
        return sleep;
    }
    public Date getToday() {
        return today;
    }
    public double getSlept() {
        return slept;
    }
    public Date getWakeup() {
        return wakeup;
    }
    public void setSleep(Date slee) {
        this.sleep = slee;
    }
    public void setSlept(double slep) {
        this.slept = slep;
    }
    public void setToday(Date toda) {
        this.today = toda;
    }
    public void setWakeup(Date wakeu) {
        this.wakeup = wakeu;
    }
}
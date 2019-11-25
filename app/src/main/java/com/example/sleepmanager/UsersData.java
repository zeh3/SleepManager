package com.example.sleepmanager;

import java.util.Date;

public class UsersData extends Users{
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
    public void setSleep(Date sleep) {
        this.sleep = sleep;
    }
    public void setSlept(double slept) {
        this.slept = slept;
    }
    public void setToday(Date today) {
        this.today = today;
    }
    public void setWakeup(Date wakeup) {
        this.wakeup = wakeup;
    }
}


package com.example.sleepmanager;

public class Settings {
    private int adjustEveryXDays;
    private boolean darkMode;
    private boolean dynamicNotifications;
    private boolean goToBedNotifications;
    private int minutesEarlier;
    private boolean notifications;
    private boolean putInDataNotifications;
    Settings() {
        adjustEveryXDays = 1;
        darkMode = false;
        dynamicNotifications = false;
        goToBedNotifications = false;
        minutesEarlier = 10;
        notifications = false;
        putInDataNotifications = false;
    }
    public void setAdjustEveryXDays(int set) {
        adjustEveryXDays = set;
    }
    public int getAdjustEveryXDays() {
        return adjustEveryXDays;
    }
    public void setDarkMode(boolean set) {
        darkMode = set;
    }
    public boolean getDarkMode() {
        return darkMode;
    }
    public void setDynamicNotifications(boolean set) {
        dynamicNotifications = set;
    }
    public boolean getDynamicNotifications() {
        return dynamicNotifications;
    }
    public void setGoToBedNotifications(boolean set) {
        goToBedNotifications = set;
    }
    public boolean getGoToBedNotifications() {
        return goToBedNotifications;
    }
    public void setMinutesEarlier(int set) {
        minutesEarlier = set;
    }
    public int getMinutesEarlier() {
        return minutesEarlier;
    }
    public void setNotifications(boolean set) {
        notifications = set;
    }
    public boolean getNotifications() {
        return notifications;
    }
    public void setPutInDataNotifications(boolean set) {
        putInDataNotifications = set;
    }
    public boolean getPutInDataNotifications() {
        return putInDataNotifications;
    }
}

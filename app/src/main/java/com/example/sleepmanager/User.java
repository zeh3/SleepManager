package com.example.sleepmanager;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class User {
    List<UsersData> a;
    Settings b;
    String id;
    User() {
        a = new ArrayList<UsersData>();
        b = new Settings();
        id = "default";
    }
    User(String c) {
        a = new ArrayList<UsersData>();
        b = new Settings();
        id = c;
    }
}
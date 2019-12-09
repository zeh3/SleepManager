package com.example.sleepmanager;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class Users {
    List<UsersData> a;
    Settings b;
    String id;
    Users() {
        a = new ArrayList<UsersData>();
        b = new Settings();
        id = "default";
    }
    Users(String c) {
        a = new ArrayList<UsersData>();
        b = new Settings();
        id = c;
    }

}


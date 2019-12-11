package com.example.sleepmanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewAlarmReceiver extends BroadcastReceiver {
    private String id;
    Settings settings;

    @Override
    public void onReceive(Context context, Intent intent) {
        id = FirebaseAuth.getInstance().getUid();
        getSettings();
        if (!settings.getGoToBedNotifications()) {
            return;
        }

        createNotificationChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationChannel.DEFAULT_CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Put in today's data!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(1, builder.build());

    }

    private void getSettings() {
        DatabaseReference justSettings = FirebaseDatabase.getInstance().getReference().child("users").child(id).child("settings");

        ValueEventListener settingsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                settings = dataSnapshot.getValue(Settings.class);
                if (settings == null) {
                    settings = new Settings();
                    justSettings.setValue(settings);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                settings = new Settings();
            }
        };

        justSettings.addValueEventListener(settingsListener);
    }

    private void createNotificationChannel(Context context){
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "goToBed";
            String description = "for reminders to go to bed";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NotificationChannel.DEFAULT_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

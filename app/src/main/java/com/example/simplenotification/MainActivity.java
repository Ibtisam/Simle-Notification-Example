package com.example.simplenotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "NOTIF_C_1";
    private int notif_A_Id, notif_B_Id, notif_C_Id;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
        notif_A_Id = 101;
        notif_B_Id = 102;
        notif_C_Id = 103;
        notificationManager = NotificationManagerCompat.from(this);

        Button sim_b = findViewById(R.id.button_s);
        sim_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                builder.setSmallIcon(R.drawable.ic_notification);
                builder.setContentTitle("Simple Notification");
                builder.setContentText("This is a simple notification without tap action");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                // notificationId is a unique int for each notification that you must define
                //show the notification
                notificationManager.notify(notif_A_Id, builder.build());
            }
        });

        Button no_tap_b = findViewById(R.id.button_t);
        no_tap_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                builder.setSmallIcon(R.drawable.ic_notification);
                builder.setContentTitle("Notification with tap action");
                builder.setContentText("Tap me to open an Activity");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                // Create an explicit intent for an Activity in your app
                Intent tapIntent = new Intent(MainActivity.this, AlertDetails.class);
                tapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                tapIntent.putExtra("MESS", "This Activity is opened by tap action");

                //creating a pending intent
                final PendingIntent tapPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, tapIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
                //setting pending intent
                builder.setContentIntent(tapPendingIntent);
                builder.setAutoCancel(true);

                // notificationId is a unique int for each notification that you must define
                //show the notification
                notificationManager.notify(notif_B_Id, builder.build());
            }
        });

        Button act_b = findViewById(R.id.button_a);
        act_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                builder.setSmallIcon(R.drawable.ic_notification);
                builder.setContentTitle("Notification with action button");
                builder.setContentText("Tap me OR the button to open an Activity");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                // Create an explicit intent for an Activity in your app
                Intent actionIntent = new Intent(MainActivity.this, AlertDetails.class);
                actionIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                actionIntent.putExtra("MESS", "This Activity is opened by an action button");

                //creating a pending intent
                final PendingIntent actionPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, actionIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
                //adding action button
                builder.addAction(R.drawable.ic_notification, getString(R.string.notif_ac), actionPendingIntent);
                builder.setContentIntent(actionPendingIntent);
                //builder.setAutoCancel(true);
                // notificationId is a unique int for each notification that you must define
                //show the notification
                notificationManager.notify(notif_C_Id, builder.build());
            }
        });

        Button rem_b = findViewById(R.id.button_r);
        rem_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.cancelAll();
            }
        });
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            //will be used instead of priority
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}

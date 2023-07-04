package com.example.moodtrackerapp;


import static androidx.core.app.NotificationManagerCompat.IMPORTANCE_DEFAULT;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.SoundPool;
import android.os.Build;
import android.view.SoundEffectConstants;


import androidx.core.app.NotificationCompat;

import com.example.moodtrackerapp.Setting;


public class AlarmReceiver extends BroadcastReceiver{

    private static final String CHANNEL_ID = "Anhar_channelId";

    //    called when class gets triggered
    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // destination after the notification clicked (felling tracker )
        Intent notificationIntent = new Intent(context, insertMoodFace.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(Setting.class);//Parent Activity
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(100, PendingIntent.FLAG_UPDATE_CURRENT );

        // notification build and style
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setContentTitle("Mood")
                .setContentText("How are you feeling today?")
                .setSmallIcon(R.drawable.ic_star)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.bg_foreground))
                .setAutoCancel(true)
//                .setSound(sound)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "NotificationDemo",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }


        if (intent.getAction().equals("MY_NOTIFICATION_MESSAGE")) {
            notificationManager.notify(100,builder.build());
        }

    }

}
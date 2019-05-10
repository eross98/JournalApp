package com.csusm.cs481.journalapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;


//USED FOR THE NOTIFACTION
public class notifcationHere extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context,Repeating_not.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.btn_dialog)
                .setContentTitle("Journal App here")
                .setContentText("Don't forget to post today!!")
                .setAutoCancel(true);

        notificationManager.notify(100,builder.build());
    }
}

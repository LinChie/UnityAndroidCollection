package com.linyi.gamenotify;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationUtility {
    static NotificationUtility s_Instance=null;

    public static NotificationUtility getInstance(){
        if(s_Instance==null){
            s_Instance=new NotificationUtility();
        }
        return s_Instance;
    }

    public void Notify(String message,String title,String channelId,String channelName,Context context){

        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=26)
        {
            NotificationChannel channel=new NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }


        NotificationCompat.Builder builder=
                new NotificationCompat.Builder(context,channelId)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(title)
                        .setContentText(message);

        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);
        manager.notify(0, builder.build());
    }
}

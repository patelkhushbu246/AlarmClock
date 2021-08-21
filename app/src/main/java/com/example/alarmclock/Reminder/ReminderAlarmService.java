package com.example.alarmclock.Reminder;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.net.Uri;
import android.provider.Settings;

import com.example.alarmclock.AddAlarmActivity;
import com.example.alarmclock.Data.AlarmReminderContract;
import com.example.alarmclock.R;


import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

public class ReminderAlarmService extends IntentService{
    private static final String TAG = ReminderAlarmService.class.getSimpleName();

    private static final int NOTIFICATION_ID = 42;


    //This is a deep link intent, and needs the task stack
    public static PendingIntent getReminderPendingIntent(Context context, Uri uri) {
        Intent action = new Intent(context, ReminderAlarmService.class);
        action.setData(uri);
        return PendingIntent.getService(context, 0, action, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public ReminderAlarmService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Uri uri = intent.getData();

        //Display a notification to view the task details
        Intent action = new Intent(ReminderAlarmService.this, AddAlarmActivity.class);
        action.setData(uri);
        PendingIntent operation = TaskStackBuilder.create(getApplicationContext())
                .addNextIntentWithParentStack(action)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        //Grab the task description
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        String description = "";
        try {
            if (cursor != null && cursor.moveToFirst()) {
                description = AlarmReminderContract.getColumnString(cursor, AlarmReminderContract.AlarmReminderEntry.KEY_TITLE);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

      /*  Notification note = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.reminder_title))
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setContentIntent(operation)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setAutoCancel(true)
                .build();*/
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
                builder.setContentTitle(getString(R.string.reminder_title));
                builder.setContentText(description);
                builder.setSmallIcon(R.drawable.ic_baseline_add_alert_24);
                builder.setContentIntent(operation);
                builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
                builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                builder.setAutoCancel(true);
                builder.build();

       // manager.notify(NOTIFICATION_ID, builder);
    }

}

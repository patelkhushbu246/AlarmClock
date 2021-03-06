package com.example.alarmclock.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlarmReminderDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "alarmreminder.db";

    private static final int DATABASE_VERSION = 1;

    public AlarmReminderDBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ALARM_TABLE =  "CREATE TABLE " + AlarmReminderContract.AlarmReminderEntry.TABLE_NAME + " ("
                + AlarmReminderContract.AlarmReminderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_TITLE + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_DATE + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_TIME + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE + " TEXT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE + " TEXT " + " );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_ALARM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

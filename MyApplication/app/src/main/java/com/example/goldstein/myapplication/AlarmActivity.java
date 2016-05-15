package com.example.goldstein.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AlarmActivity extends AppCompatActivity {

    private static final String ACTION_BROADCAST = "";
    AlarmManager alarmMgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    }
private PendingIntent createIntent()
{
    Intent i = new Intent(ACTION_BROADCAST);
    i.setType("*/*");

    PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);
    return  pi;
}
    public  void doFiveSecondsInterval()
    {
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 30 * 1000,createIntent());
    }
}

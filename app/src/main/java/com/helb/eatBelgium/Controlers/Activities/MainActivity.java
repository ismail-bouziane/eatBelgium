package com.helb.eatBelgium.Controlers.Activities;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;

import com.helb.eatBelgium.Notification.AlarmNotificationReceiver;
import com.helb.eatBelgium.R;
import com.helb.eatBelgium.Notification.NotificationPublisher;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    TextView txtSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnSignUp = (Button)findViewById(R.id.btSignUp);
        btnSignIn = (Button)findViewById(R.id.btSignIn);



        startAlarm(true,true);

       btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);
            }
        });
       btnSignIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent signIn = new Intent(MainActivity.this,SignIn.class);
               startActivity(signIn);
           }
       });

    }


    private void startAlarm(boolean isNotification, boolean isRepeat) {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;


        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,14);
        calendar.set(Calendar.MINUTE,8);

        myIntent = new Intent(MainActivity.this, AlarmNotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);

        if(!isRepeat)
            manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+3000,pendingIntent);
        else
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
    }
}

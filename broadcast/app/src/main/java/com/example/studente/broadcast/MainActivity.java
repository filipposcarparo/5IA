package com.example.studente.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Matteo Mognato on 03/03/18.
 */

public class MainActivity extends AppCompatActivity {
    public int pendingI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickButton(View view){
        switch(view.getId()){
            case R.id.rb_FLAG_CANCEL_CURRENT:
                pendingI=PendingIntent.FLAG_CANCEL_CURRENT;
                break;
            case R.id.rb_FLAG_IMMUTABLE:
                pendingI=PendingIntent.FLAG_IMMUTABLE;
                break;
            case R.id.rb_FLAG_NO_CREATE:
                pendingI=PendingIntent.FLAG_NO_CREATE;
                break;
            case R.id.rb_FLAG_ONE_SHOT:
                pendingI=PendingIntent.FLAG_ONE_SHOT;
                break;
            case R.id.rb_FLAG_UPDATE_CURRENT:
                pendingI=PendingIntent.FLAG_UPDATE_CURRENT;
                break;
        }
    }
    public void startAlert(View view) {
        EditText text = findViewById(R.id.time);
        int delay = Integer.parseInt(text.getText().toString());
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1234567890, intent, pendingI);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (delay * 1000), pendingIntent);
        Toast.makeText(this, "... waiting " + delay + " seconds",
                Toast.LENGTH_LONG).show();
    }



}

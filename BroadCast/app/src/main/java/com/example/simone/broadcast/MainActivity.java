package com.example.simone.broadcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup radio=findViewById(R.id.radioGruppo);
        Button btn=findViewById(R.id.bt1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAlert(view);
            }
        });

    }
    public void onRadioButtonClicked(View view) {
        intent = new Intent(this, receiver.class);
        switch (view.getId()) {
            case R.id.radio_1:
                pendingIntent = PendingIntent.getBroadcast(this, 1234567890, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                break;
            case R.id.radio_2:
                pendingIntent = PendingIntent.getBroadcast(this, 1234567890, intent, PendingIntent.FLAG_IMMUTABLE);
                break;
            case R.id.radio_3:
                pendingIntent = PendingIntent.getBroadcast(this, 1234567890, intent, PendingIntent.FLAG_NO_CREATE);
                break;
            case R.id.radio_4:
                pendingIntent = PendingIntent.getBroadcast(this, 1234567890, intent, PendingIntent.FLAG_ONE_SHOT);
                break;
            case R.id.radio_5:
                pendingIntent = PendingIntent.getBroadcast(this, 1234567890, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
        }
    }


    public void startAlert(View view) {
        EditText text = findViewById(R.id.time);
        int delay = Integer.parseInt(text.getText().toString());
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (delay * 1000), pendingIntent);
        Toast.makeText(this, "... waiting " + delay + " seconds",
                Toast.LENGTH_LONG).show();
    }

}


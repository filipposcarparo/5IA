package com.example.genji.am003_receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static android.content.Context.ALARM_SERVICE;

public class MainActivity extends AppCompatActivity {

    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flag = PendingIntent.FLAG_CANCEL_CURRENT;
    }

    public void startAlert(View view) {
        EditText text = findViewById(R.id.time);
        int delay = Integer.parseInt(text.getText().toString());
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1234567890, intent, flag);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (delay * 1000), pendingIntent);
        Toast.makeText(this, "... waiting " + delay + " seconds",
                Toast.LENGTH_LONG).show();
    }

    public void onRadioClicked(View v){
        switch(v.getId()){
            case R.id.radioButton:
                flag = PendingIntent.FLAG_CANCEL_CURRENT;
                break;

            case R.id.radioButton2:
                flag = PendingIntent.FLAG_IMMUTABLE;
                break;

            case R.id.radioButton3:
                flag = PendingIntent.FLAG_NO_CREATE;
                break;

            case R.id.radioButton4:
                flag = PendingIntent.FLAG_ONE_SHOT;
                break;

            case R.id.radioButton5:
                flag = PendingIntent.FLAG_UPDATE_CURRENT;
                break;
        }
    }

}
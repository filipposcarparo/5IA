package com.example.piergiorgio.flag;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int myFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton view = findViewById(R.id.cancel_current);
        view.setChecked(true);
        myFlag = PendingIntent.FLAG_CANCEL_CURRENT;

    }

    public void startAlert(View view) {
        EditText text = findViewById(R.id.time);
        int delay = Integer.parseInt(text.getText().toString());
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1234567890, intent, myFlag);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (delay * 1000), pendingIntent);
        Toast.makeText(this, "... waiting " + delay + " seconds",
                Toast.LENGTH_LONG).show();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.cancel_current:
                if (checked) {
                    myFlag = PendingIntent.FLAG_CANCEL_CURRENT;
                }
                break;
            case R.id.immutable:
                if (checked) {
                    myFlag = PendingIntent.FLAG_IMMUTABLE;
                }
                break;
            case R.id.no_create:
                if (checked) {
                    myFlag = PendingIntent.FLAG_NO_CREATE;
                }
                break;
            case R.id.one_shot:
                if (checked) {
                    myFlag = PendingIntent.FLAG_ONE_SHOT;
                }
                break;
            case R.id.update_current:
                if (checked) {
                    myFlag = PendingIntent.FLAG_UPDATE_CURRENT;
                }
                break;
        }
    }
}


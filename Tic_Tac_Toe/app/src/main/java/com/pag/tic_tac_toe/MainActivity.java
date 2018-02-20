package com.pag.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    boolean isPvp = true;
    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLayoutInflater().inflate(R.layout.pvp, findViewById(R.id.frame));
        ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.pvp);
        frame = findViewById(R.id.frame);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pvp:
                if (((RadioButton) v).isChecked()) {
                    frame.removeAllViewsInLayout();
                    getLayoutInflater().inflate(R.layout.pvp, frame);
                    isPvp = true;
                }
                break;
            case R.id.pve:
                if (((RadioButton) v).isChecked()) {
                    frame.removeAllViewsInLayout();
                    getLayoutInflater().inflate(R.layout.pve, frame);
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                            this, R.array.aiOptions, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ((Spinner) findViewById(R.id.spinner_modes)).setAdapter(adapter);
                    isPvp = false;
                }
                break;
            case R.id.button_start:
                Intent i = new Intent(MainActivity.this, Game.class);
                String p1name = isPvp ? ((EditText) findViewById(R.id.txt_p1)).getText().toString().trim() : "giocatore";
                String p2name = isPvp ? ((EditText) findViewById(R.id.txt_p2)).getText().toString().trim() : "ai-chan";
                Player p1 = new Player(R.drawable.cross, p1name, false);
                Player p2 = new Player(R.drawable.circle, p2name, !isPvp);
                i.putExtra("isPvp", isPvp);
                i.putExtra("player1", p1);
                i.putExtra("player2", p2);
                startActivity(i);
                break;
        }
    }
}

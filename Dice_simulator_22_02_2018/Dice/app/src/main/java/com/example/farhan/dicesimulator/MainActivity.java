package com.example.farhan.dicesimulator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    static final int DICE_SIMULATOR_ACTIVITY = 1;
    static final int MIN_FACESNUMBER = 4;
    static final int MAX_FACESNUMBER = 255;
    static final String ERROR_MESSAGE = "Enter a valid number of faces!";
    static final String SIMULATION_RESULT = " simulations have been done.";
    static final String INVALID_FACESNUMBER = " Enter a number between 4 and 255.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTxt);
        button = findViewById(R.id.btnStart);

        button.setOnClickListener(view -> {

            String stFaces = editText.getText().toString();

            if (!stFaces.isEmpty()) {
                if (stFaces.length() < 4) {

                    int facesNum = Integer.parseInt(stFaces);

                    if (facesNum >= MIN_FACESNUMBER && facesNum <= MAX_FACESNUMBER) {

                        Intent i = new Intent(this, DiceSimulator.class);
                        i.putExtra("faces", facesNum);

                        startActivityForResult(i, DICE_SIMULATOR_ACTIVITY);
                    } else {

                        makeToast(INVALID_FACESNUMBER);

                    }
                } else {

                    makeToast(INVALID_FACESNUMBER);

                }
            } else {

                makeToast(ERROR_MESSAGE);

            }

        });
    }

    private void makeToast(String msg) {
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DICE_SIMULATOR_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                String simulations = String.valueOf(data.getIntExtra("simulations", 0));
                makeToast(simulations + SIMULATION_RESULT);
            }
        }
    }
}

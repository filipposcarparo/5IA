package com.example.farhan.dicesimulator;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class DiceSimulator extends AppCompatActivity {

    private static int FACES;
    private int simulations;
    private int actualNumer;
    private int touchCounter;

    private TextView simulationsTxtView;
    private DiceFragment diceFragment;
    FragmentTransaction fragmentTransaction;

    float x, y, x1, y1;
    long touchDown1, touchDown2;
    final static float MIN_DISTANCE = 150.0f;
    final static long MIN_TIME = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        simulationsTxtView = findViewById(R.id.simulationNumber);

        setParameters();
        setFragment();
        if (savedInstanceState != null) {
            simulations = savedInstanceState.getInt("simulationNumber");
            simulationsTxtView.setText("Simulation number: " + simulations);
        } else {
            addSimulation();
        }
    }


    private void addSimulation() {
        simulations++;
        simulationsTxtView.setText("Simulation number: " + simulations);
    }

    private void setActualNumer(int number) {
        actualNumer = number;
    }

    private void setParameters() {
        FACES = getIntent().getIntExtra("faces", 6);
        simulations = actualNumer = touchCounter = 0;
        x = y = x1 = y1 = 0;
        touchDown1 = touchDown2 = 0;
    }

    private void setFragment() {
        if (findViewById(R.id.fragment) != null) {
            diceFragment = DiceFragment.newInstance(actualNumer, FACES);
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment, diceFragment);
            fragmentTransaction.commit();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();

                if (touchCounter > 0) {
                    touchDown2 = System.currentTimeMillis();
                    touchCounter = 0;
                    long deltaTime = touchDown2 - touchDown1;
                    if (deltaTime < MIN_TIME) {
                        makeToast("Double tap !");
                        touchDown1 = touchDown2 = 0;

                        Intent i = new Intent();
                        i.putExtra("simulations", simulations);
                        setResult(RESULT_OK, i);
                        finish();
                    }
                } else {
                    touchDown1 = System.currentTimeMillis();
                    touchCounter++;
                }
                break;
            case MotionEvent.ACTION_UP:
                x1 = event.getX();
                y1 = event.getY();

                float deltaX = x1 - x;
                float deltaY = y1 - y;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // Left to Right swipe action
                    if (x1 > x) {
                        // left
                        changeFragmentLeft();
                        makeToast("Swiped to left");
                    } else if (x > x1) {
                        // right
                        changeFragmentRight();
                        makeToast("Swiped to right");
                    }

                } else if (Math.abs(deltaY) > MIN_DISTANCE) {
                    if (y1 > y) {
                        // down
                        changeFragmentDown();
                        makeToast("Swiped down");
                    } else if (y > y1) {
                        // up
                        changeFragmentUp();
                        makeToast("Swiped up");
                    }
                }

                break;
        }
        return super.onTouchEvent(event);
    }


    public void changeFragmentLeft() {
        if (findViewById(R.id.fragment) != null) {
            diceFragment = DiceFragment.newInstance(actualNumer, FACES);
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_linear_left, R.animator.slide_linear_right);//left
            fragmentTransaction.replace(R.id.fragment, diceFragment);
            fragmentTransaction.commit();
            addSimulation();
            setActualNumer(diceFragment.getGeneratedCasual());
        }
    }

    public void changeFragmentRight() {
        if (findViewById(R.id.fragment) != null) {
            diceFragment = DiceFragment.newInstance(actualNumer, FACES);
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_linear_from_right, R.animator.slide_linear_to_left);//right
            fragmentTransaction.replace(R.id.fragment, diceFragment);
            fragmentTransaction.commit();
            addSimulation();
            setActualNumer(diceFragment.getGeneratedCasual());
        }
    }

    public void changeFragmentUp() {
        if (findViewById(R.id.fragment) != null) {
            diceFragment = DiceFragment.newInstance(actualNumer, FACES);
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_linear_from_down, R.animator.slide_linear_to_up);//up
            fragmentTransaction.replace(R.id.fragment, diceFragment);
            fragmentTransaction.commit();
            addSimulation();
            setActualNumer(diceFragment.getGeneratedCasual());
        }
    }

    public void changeFragmentDown() {
        if (findViewById(R.id.fragment) != null) {
            diceFragment = DiceFragment.newInstance(actualNumer, FACES);
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_linear_down, R.animator.slide_linear_up);//down
            fragmentTransaction.replace(R.id.fragment, diceFragment);
            fragmentTransaction.commit();
            addSimulation();
            setActualNumer(diceFragment.getGeneratedCasual());
        }
    }

    private void makeToast(String msg) {
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("simulationNumber", simulations);
    }
}

package com.bus.flatdice;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float x1,x2,y1,y2;
    final static float MIN_DISTANCE = 150.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // portrait mode
        if(findViewById(R.id.fragment) != null){
            FlatDice f1 = new FlatDice();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, f1);
            ft.commit();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // Left to Right swipe action
                    if (x2 > x1) {
                        changeFragment("left");
                    }else {
                        changeFragment("right");
                    }
                }else if(Math.abs(deltaY) > MIN_DISTANCE){
                    //Down to Up swipe action
                    if (y2 < y1) {
                        changeFragment("down");
                    }else {
                        changeFragment("up");
                    }
                }else {
                    Toast.makeText(this, "NO STA STRUCCAR", Toast.LENGTH_SHORT).show ();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void changeFragment(String direction) {
        // act only in portrait mode
        if(findViewById(R.id.fragment) != null){
            FragmentManager fm = getFragmentManager();
            Fragment nextFragment = null;
            Fragment currentFragment = fm.findFragmentById(R.id.fragment);
            if (currentFragment instanceof FlatDice) {
                nextFragment = new FlatDice();
            } else {
                nextFragment = new FlatDice();
            }

            FragmentTransaction ft = fm.beginTransaction();
            // (enter, exit)
            if(direction.equals("left")){
                ft.setCustomAnimations(R.animator.slide_linear_in_left, R.animator.slide_linear_out_left);
            }else if(direction.equals("right")){
                ft.setCustomAnimations(R.animator.slide_linear_in_right, R.animator.slide_linear_out_right);
            }else if(direction.equals("down")){
                ft.setCustomAnimations(R.animator.slide_linear_in_down, R.animator.slide_linear_out_down);
            }else {
                ft.setCustomAnimations(R.animator.slide_linear_in_up, R.animator.slide_linear_out_up);
            }

            // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.replace(R.id.fragment, nextFragment);
            ft.commit();
        } else {
            Toast.makeText(this, "GIRA IL TELEFONO PER PIACERE", Toast.LENGTH_SHORT).show ();
        }

    }


}

package com.example.genji.am012_fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float x1, x2, y1, y2;
    final static float MIN_DISTANCE = 150.0f;
    final static float MAX_DISTANCE = 150.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // portrait mode
        if(findViewById(R.id.fragment) != null){
            Fragment1 f1 = new Fragment1();
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

                if (Math.abs(deltaX) > MIN_DISTANCE || Math.abs(deltaY) > MAX_DISTANCE) {
                    // Left to Right swipe action
                    if (x2 > x1) {
                        changeFragment();
                    }

                    // Right to left swipe action
                    else {
                        changeFragment();
                    }
                } else {
                    Toast.makeText(this, "TAP", Toast.LENGTH_SHORT).show ();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void changeFragment() {
        // act only in portrait mode
        if(findViewById(R.id.fragment) != null){
            FragmentManager fm = getFragmentManager();
            Fragment nextFragment = null;
            Fragment currentFragment = fm.findFragmentById(R.id.fragment);
            if (currentFragment instanceof Fragment1) {
                nextFragment = new Fragment2();
            } else {
                nextFragment = new Fragment1();
            }

            FragmentTransaction ft = fm.beginTransaction();
            // (enter, exit)
            ft.setCustomAnimations(R.animator.slide_linear_left, R.animator.slide_linear_right);
            // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.replace(R.id.fragment, nextFragment);
            ft.commit();
        } else {
            Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_SHORT).show ();
        }

    }
}

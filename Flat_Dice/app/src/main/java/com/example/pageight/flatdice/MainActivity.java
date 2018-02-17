package com.example.pageight.flatdice;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    ThreadLocalRandom random;
    FragmentFactory ff;
    float x1,x2;
    final static float MIN_DISTANCE = 150.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = ThreadLocalRandom.current();
        ff = new FragmentFactory();
        FragmentManager fm = getFragmentManager();
        Fragment nextFragment = ff.getFragment(random.nextInt(1,6));
        fm.beginTransaction()
                .replace(R.id.frame,nextFragment)
                .commit();
    }

    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // Left to Right swipe action
                    if (x2 > x1) {
                        changeFragment(R.animator.slide_left,R.animator.slide_right);
                    }
                    // Right to left swipe action
                    else {

                    }
                }
                else {

                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void changeFragment(int animIn, int animOut){
        FragmentManager fm = getFragmentManager();
        Fragment nextFragment = ff.getFragment(random.nextInt(1,6));
        fm.beginTransaction()
                .replace(R.id.frame,nextFragment)
                .setCustomAnimations(animIn,animOut)
                .commit();
    }
}

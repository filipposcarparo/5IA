package com.example.pageight.flatdice;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    ThreadLocalRandom random;
    final static float MIN_DISTANCE = 150.0f;
    private float[] coordDOWN, coordUP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = ThreadLocalRandom.current();
        FragmentManager fm = getFragmentManager();
        Fragment nextFragment = DiceFace.newInstance(random.nextInt(1, 6));
        fm.beginTransaction()
                .replace(R.id.frame, nextFragment)
                .commit();
        coordDOWN = new float[2];
        coordUP = new float[2];

    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                coordDOWN[0] = event.getX();
                coordDOWN[1] = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                coordUP[0] = event.getX();
                coordUP[1] = event.getY();
                float deltaY = coordDOWN[1] - coordUP[1];
                float deltaX = coordDOWN[0] - coordUP[0];

                if (Math.abs(deltaY) > MIN_DISTANCE) {
                    // Bottom to Top swipe action
                    if (coordUP[1] > coordDOWN[1]) {
                        changeFragment(R.animator.slide_in_bottom, R.animator.slide_out_top);
                    }
                    // Top to Bottom action
                    else {
                        changeFragment(R.animator.slide_in_top, R.animator.slide_out_bottom);
                    }
                } else {
                    if (Math.abs(deltaX) > MIN_DISTANCE) {
                        //Left to Right swipe action
                        if (coordUP[0] > coordDOWN[0]) {
                            changeFragment(R.animator.slide_in_left, R.animator.slide_out_right);
                        }
                        // Right to Left action
                        else {
                            changeFragment(R.animator.slide_in_right, R.animator.slide_out_left);
                        }
                    }else{
                        Toast.makeText(this, "TAP",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void changeFragment(int animIn, int animOut) {
        FragmentManager fm = getFragmentManager();
        Fragment nextFragment = DiceFace.newInstance(random.nextInt(1, 7));
        fm.beginTransaction()
                .setCustomAnimations(animIn, animOut)
                .replace(R.id.frame, nextFragment)
                .commit();
    }
}

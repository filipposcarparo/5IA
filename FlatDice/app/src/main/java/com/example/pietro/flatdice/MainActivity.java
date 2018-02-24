package com.example.pietro.flatdice;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    float x1, x2, y1, y2;

    final static float MIN_DISTANCE = 150.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // portrait mode
        if(findViewById(android.R.id.content) != null){
            HomeFragment start = HomeFragment.newInstance("0");
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.cont,start);
            ft.commit();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {

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
                    // sinistra destra
                    if (x2 > x1) {
                        changeFragment(1);
                       //Toast.makeText(this, "sinistra destra", Toast.LENGTH_SHORT).show();
                    }
                    // destra sinistra
                    else {
                        changeFragment(2);
                        //Toast.makeText(this, "destra sinistra", Toast.LENGTH_SHORT).show();
                    }
                }
                if (Math.abs(deltaY) > MIN_DISTANCE) {
                    //su giu
                    if (y2 > y1) {
                        changeFragment(3);
                        //Toast.makeText(this, "su giu", Toast.LENGTH_SHORT).show();
                    }
                    //giu su
                    else {
                        changeFragment(4);
                        //Toast.makeText(this, "giu su", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void changeFragment(int dir) {
        // act only in portrait mode
        if (findViewById(android.R.id.content) != null) {
            FragmentManager fm = getFragmentManager();
            Fragment currentFragment = fm.findFragmentById(R.id.cont);
            if (currentFragment instanceof Fragment) {
                Random ra= new Random();
                Random random= new Random();
                int rand = 0+ random.nextInt(6);
                String r = rand + "";
                MioFragment a = MioFragment.newInstance(r);


                FragmentTransaction ft = fm.beginTransaction();
                if(dir == 1) ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
                else if (dir == 2) ft.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right);
                else if (dir == 3) ft.setCustomAnimations(R.animator.slide_in_up, R.animator.slide_out_down);
                else if (dir == 4) ft.setCustomAnimations(R.animator.slide_in_down, R.animator.slide_out_up);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.replace(R.id.cont, a);
                ft.commit();
            } else {
                Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
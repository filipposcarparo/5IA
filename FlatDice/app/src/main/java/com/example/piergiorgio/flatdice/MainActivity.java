package com.example.piergiorgio.flatdice;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    float x1, x2, y1, y2;
    final static float yTop = 700f;
    final static float MIN_DISTANCE = 150.0f;
    Button indietro;
    Random r;
    FragmentManager fm;
    boolean main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = new Random();
        main = true;
        indietro = findViewById(R.id.indietro);
        indietro.setVisibility(View.INVISIBLE);
        indietro.setOnClickListener(View -> {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, StartFragment.newInstance());
            ft.commit();
            indietro.setVisibility(View.INVISIBLE);
        });
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, StartFragment.newInstance());
        ft.commit();
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
                if(y1 > yTop) {
                    if (Math.abs(deltaX) > MIN_DISTANCE) {
                        if (x2 < x1) {
                            changeFragment(R.animator.slide_right, R.animator.slide_rightext);
                        } else if (x2 > x1 && !main){
                            changeFragment(R.animator.slide_left, R.animator.slide_leftext);
                        }
                    } else if(Math.abs(deltaY) > MIN_DISTANCE){
                        if(y2 < y1 && !main){
                            changeFragment(R.animator.slide_down, R.animator.slide_downext);
                        } else if(y2 > y1 && !main){
                            changeFragment(R.animator.slide_up, R.animator.slide_upext);
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    void changeFragment(int enter, int exit){
        main = false;
        fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        indietro.setVisibility(View.VISIBLE);
        ft.setCustomAnimations(enter, exit);
        int n = r.nextInt(6);
        ft.replace(R.id.fragment, DadoFragment.newInstance(n));
        ft.commit();
    }

}


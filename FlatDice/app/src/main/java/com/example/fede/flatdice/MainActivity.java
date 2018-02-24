package com.example.fede.flatdice;

/**
 * Created by Federico Doria on 23/02/2018.
 */

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    float x1, x2, y1, y2; //punti in sistema cartesiano che determinano la gesture
    final static float MIN_DISTANCE = 150.0f; //distanza sotto la quale la gesture è un TAP
    Random r;
    int numeroFaccia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment) != null) {
            Fragment_Start f = new Fragment_Start();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, f);
            ft.commit();
        }
        r = new Random();
        numeroFaccia = 0;
    }

    public boolean onTouchEvent(MotionEvent event) { //interpreta la gesture
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //pressione
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP: //rilascio
                x2 = event.getX();
                y2 = event.getY();
                if (gesture(x1, x2, y1, y2) > 0) {
                    changeFragment(gesture(x1, x2, y1, y2));

                } else { //TAP
                    finish();
                    System.exit(0);
                }
        }
        return super.onTouchEvent(event);
    }

    public int gesture(float x1,float x2,float y1, float y2){
        float deltaX = Math.abs(x2 - x1);
        float deltaY = Math.abs(y2 - y1);
        int ris=0;
        if (deltaX > MIN_DISTANCE || deltaY > MIN_DISTANCE) { //SWIPE
            if (deltaX > deltaY) {
                return x2 > x1?1:2; //1 -> SWIPE da sx a dx | 2-> SWIPE da dx a sx
            } else {
                return y2 > y1?3:4; //3 -> SWIPE da su a giù | 4 -> SWIPE da giù a su
            }
        }
        return ris;
    }

    public void changeFragment(int diceNumber) {
        if (findViewById(R.id.fragment) != null) {
            int numero;
            do {
                numero = r.nextInt() % 7;
                numero *= numero < 0 ? -1 : 1;
            } while (numero == 0 || numeroFaccia == numero);
            numeroFaccia = numero;

            Fragment_Dice nextFragment = Fragment_Dice.newFace(numeroFaccia);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            switch (diceNumber) {
                case 1:
                    ft.setCustomAnimations(R.animator.scrolling_left, R.animator.fade);
                    break;
                case 2:
                    ft.setCustomAnimations(R.animator.scrolling_right, R.animator.fade);
                    break;
                case 3:
                    ft.setCustomAnimations(R.animator.scrolling_up, R.animator.fade);
                    break;
                case 4:
                    ft.setCustomAnimations(R.animator.scrolling_down, R.animator.fade);
                    break;
            }
            ft.replace(R.id.fragment, nextFragment);
            ft.commit();
        }
    }
}
package com.example.matteo.swipedado;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;
import java.util.Random;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    float x1,x2,y1,y2;
    Random r;
    int nFaccia;
    final static float MIN_DISTANCE = 150.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.main) != null) {
            inizioFragment f = new inizioFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main,f);
            ft.commit();
        }
        r = new Random();
        nFaccia = 0;
    }

    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2=event.getY();
                float deltaX = x2 - x1;
                float deltaY =y2-y1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    //Right to left swipe action
                    if (x2 > x1) {
                        changeFragment();
                        Toast.makeText(this, "SWIPEdx", Toast.LENGTH_SHORT).show ();
                    }
                    //  Left to Right swipe action
                    else{
                        changeFragment();
                        Toast.makeText(this, "SWIPEsx", Toast.LENGTH_SHORT).show ();
                    }
                }
                else   if (Math.abs(deltaY) > MIN_DISTANCE) {
                    // up to down
                    if (y2 > y1) {
                        changeFragment();
                        Toast.makeText(this, "SWIPEdown", Toast.LENGTH_SHORT).show ();
                    }
                    // down to up
                    else{
                        changeFragment();
                        Toast.makeText(this, "SWIPEup", Toast.LENGTH_SHORT).show ();
                    }
                }
                else {
                    Toast.makeText(this, "TAP", Toast.LENGTH_SHORT).show ();
                }
                break;
        }
        return super.onTouchEvent(event);
    }
    public void changeFragment() {
        if (findViewById(R.id.main) != null) {
            int numero;
            do {
                numero = r.nextInt() % 7;
                numero *= numero < 0 ? -1 : 1;
            } while (numero == 0 || nFaccia == numero);
            nFaccia = numero;
            BlankFragment nextFragment = BlankFragment.newInstance(nFaccia);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.main,nextFragment);
            ft.commit();
        }

       
    }
}

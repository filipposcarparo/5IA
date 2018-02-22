package com.example.manuel.dadofragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float x1,y1,x2,y2;
    final static float MIN_DISTANCE = 150.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment3 fMain= new Fragment3();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment, fMain);
        ft.commit();

        //setContentView(R.layout.fragment_main);

        // portrait mode
        if(findViewById(R.id.fragment) == null){
            Fragment1 f1 = new Fragment1();
            //FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, f1);
            ft.commit();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 =event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2=event.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;

                if (Math.abs(deltaX) > 210.0) {
                    // Left to Right swipe action
                    if (x2 > x1) {
                        changeFragmentLeftToRight();
                    }

                    // Right to left swipe action

                    if (x2 < x1) {
                        changeFragmentRightToLeft();
                    }
                    /**else {
                        Toast.makeText(this, "SWIPE", Toast.LENGTH_SHORT).show ();
                    }**/
                }else{//_______________________________________________________________________
                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                        // Up to Down swipe action
                        if (y2 > y1) {
                            changeFragmentUpToDown();
                        }

                        // Down to Up swipe action

                        if (y2 < y1) {
                            changeFragmentDownToUp();
                        }
                    }

                    break;
                }

        }
        return super.onTouchEvent(event);
    }

    public void changeFragmentLeftToRight() {
        // act only in portrait mode
        if(findViewById(R.id.fragment) != null){
            FragmentManager fm = getFragmentManager();
            Fragment nextFragment = null;
            Fragment currentFragment = fm.findFragmentById(R.id.fragment);
            if (currentFragment instanceof Fragment1) {
                nextFragment = new Fragment2();
            } else if(currentFragment instanceof Fragment2){
                nextFragment = new Fragment1();
            } else {
                nextFragment = new Fragment1();
            }

            FragmentTransaction ft = fm.beginTransaction();
            // (enter, exit) linear left-linear right
            ft.setCustomAnimations(R.animator.slide_linear_left,R.animator.fade);
            // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.replace(R.id.fragment, nextFragment);
            ft.commit();
        } else {
            Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_SHORT).show ();
        }

    }

    public void changeFragmentRightToLeft() {
        // act only in portrait mode
        if(findViewById(R.id.fragment) != null){
            FragmentManager fm = getFragmentManager();
            Fragment nextFragment = null;
            Fragment currentFragment = fm.findFragmentById(R.id.fragment);
            if (currentFragment instanceof Fragment1) {
                nextFragment = new Fragment2();
            } else if(currentFragment instanceof Fragment2){
                nextFragment = new Fragment1();
            } else {
                nextFragment = new Fragment1();
            }

            FragmentTransaction ft = fm.beginTransaction();
            // (enter, exit) opposite right-opposite left
            ft.setCustomAnimations(R.animator.slide_linear_opposite_right,R.animator.fade);
            // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.replace(R.id.fragment, nextFragment);
            ft.commit();
        } else {
            Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_SHORT).show ();
        }
    }

    public void changeFragmentUpToDown() {
        // act only in portrait mode
        if(findViewById(R.id.fragment) != null){
            FragmentManager fm = getFragmentManager();
            Fragment nextFragment = null;
            Fragment currentFragment = fm.findFragmentById(R.id.fragment);
            if (currentFragment instanceof Fragment1) {
                nextFragment = new Fragment2();
            } else if(currentFragment instanceof Fragment2){
                nextFragment = new Fragment1();
            } else {
                nextFragment = new Fragment1();
            }

            FragmentTransaction ft = fm.beginTransaction();
            // (enter, exit) linear up- linear down
            ft.setCustomAnimations(R.animator.slide_linear_up,R.animator.fade);
            // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.replace(R.id.fragment, nextFragment);
            ft.commit();
        } else {
            Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_SHORT).show ();
        }
    }

    public void changeFragmentDownToUp() {
        // act only in portrait mode
        if(findViewById(R.id.fragment) != null){
            FragmentManager fm = getFragmentManager();
            Fragment nextFragment = null;
            Fragment currentFragment = fm.findFragmentById(R.id.fragment);
            if (currentFragment instanceof Fragment1) {
                nextFragment = new Fragment2();
            } else if(currentFragment instanceof Fragment2){
                nextFragment = new Fragment1();
            } else {
                nextFragment = new Fragment1();
            }

            FragmentTransaction ft = fm.beginTransaction();
            // (enter, exit) opposite down-opposite up
            ft.setCustomAnimations(R.animator.slide_linear_opposite_down,R.animator.fade);
            // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.replace(R.id.fragment, nextFragment);
            ft.commit();
        } else {
            Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_SHORT).show ();
        }
    }
}

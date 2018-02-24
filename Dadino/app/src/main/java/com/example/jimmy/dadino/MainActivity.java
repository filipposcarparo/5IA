package com.example.jimmy.dadino;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentStart fragmentStart=new FragmentStart();
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment,fragmentStart);
        ft.commit();
    }

    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2=event.getY();
                float deltaX = Math.abs(x2 - x1),deltaY=Math.abs(y2-y1);
                if(deltaX>deltaY){
                    changeFragment(x2>x1?1:2);
                }else if(deltaY>deltaX){
                    changeFragment(y2>y1?3:4);
                }
        }
        return super.onTouchEvent(event);
    }

    public void changeFragment(int cod){

        FragmentManager fm = getFragmentManager();
        Fragment currentFragment=fm.findFragmentById(R.id.fragment);
        int x=0;
        if(currentFragment instanceof FragmentFaccia){
            x = ((FragmentFaccia)currentFragment).getN();
        }
        FragmentFaccia nextFragment=FragmentFaccia.newInstance(x);
        FragmentTransaction ft = fm.beginTransaction();
        switch(cod){
            case 1:
                ft.setCustomAnimations(R.animator.slide_left,R.animator.slide_right_out);
                break;
            case 2:
                ft.setCustomAnimations(R.animator.slide_right,R.animator.slide_left_out);
                break;
            case 3:
                ft.setCustomAnimations(R.animator.slide_down,R.animator.slide_up_out);
                break;
            case 4:
                ft.setCustomAnimations(R.animator.slide_up,R.animator.slide_down_out);
        }
        ft.replace(R.id.fragment,nextFragment);
        ft.commit();
    }
}

package com.example.pageight.flatdice;

import android.app.Fragment;
import android.widget.TextView;

public class FragmentFactory {

    public FragmentFactory() {
    }

    public Fragment getFragment(int i){
        Fragment ris = new DiceFace();
        ((TextView)ris.getView().findViewById(R.id.textDiceFace)).setText(i + "");
        return ris;
    }
}

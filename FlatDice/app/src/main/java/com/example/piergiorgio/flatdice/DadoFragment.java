package com.example.piergiorgio.flatdice;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DadoFragment extends Fragment {

    ImageView img;
    static int faccia;

    public static DadoFragment newInstance(int n) {
        DadoFragment fragment = new DadoFragment();
        faccia = n;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dado, container, false);
        v.setBackgroundResource(R.drawable.sfondo2);
        img = v.findViewById(R.id.faccia);
        switch(faccia) {
            case 0:
                img.setImageResource(R.drawable.uno);
                break;
            case 1:
                img.setImageResource(R.drawable.due);
                break;
            case 2:
                img.setImageResource(R.drawable.tre);
                break;
            case 3:
                img.setImageResource(R.drawable.quattro);
                break;
            case 4:
                img.setImageResource(R.drawable.cinque);
                break;
            case 5:
                img.setImageResource(R.drawable.sei);
                break;
        }
        return v;
    }

}

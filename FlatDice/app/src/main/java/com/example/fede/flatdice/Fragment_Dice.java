package com.example.fede.flatdice;

/**
 * Created by Federico Doria on 23/02/2018.
 */


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Fragment_Dice extends Fragment {


    public static Fragment_Dice newFace(int n) {
        Bundle bundle = new Bundle();
        bundle.putInt("numDado", n);
        Fragment_Dice fragment = new Fragment_Dice();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dado, container, false);
        ImageView img = view.findViewById(R.id.faccia);
        int numDado = getArguments().getInt("numDado");
        switch (numDado) {
            case 1:
                img.setImageResource(R.drawable.f1);
                break;
            case 2:
                img.setImageResource(R.drawable.f2);
                break;
            case 3:
                img.setImageResource(R.drawable.f3);
                break;
            case 4:
                img.setImageResource(R.drawable.f4);
                break;
            case 5:
                img.setImageResource(R.drawable.f5);
                break;
            case 6:
                img.setImageResource(R.drawable.f6);
                break;
        }
        return view;
    }
}

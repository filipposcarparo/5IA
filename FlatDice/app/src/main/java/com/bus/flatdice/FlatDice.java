package com.bus.flatdice;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Random;
import android.widget.TextView;
import com.bus.flatdice.R.layout.*;

/**
 * Created by BUS on 20/02/2018.
 */


public class FlatDice extends Fragment {

    Random random = new Random();
    public View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int num = random.nextInt(7-1)+1;
        v = inflater.inflate(R.layout.fragment_one, container, false);
        setRandomNumber(num);
        return v;
    }

    public void setRandomNumber(int i){
        TextView txt = v.findViewById(R.id.randomNumberLabel);
        txt.setText(i + "");
    }
}

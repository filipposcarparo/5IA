package com.example.manuel.dadofragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Manuel on 19/02/2018.
 */

public class Fragment1 extends Fragment{

    private TextView testo;
    private Random rand=new Random();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View ris=inflater.inflate(R.layout.fragment_one, container, false);
        // Inflate the layout for this fragment
        int val=rand.nextInt(6)+1;
        testo= ris.findViewById(R.id.TestoProva);
        testo.setText(""+val);
        return ris;
    }


}

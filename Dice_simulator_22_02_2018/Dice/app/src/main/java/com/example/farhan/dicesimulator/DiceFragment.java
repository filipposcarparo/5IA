package com.example.farhan.dicesimulator;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class DiceFragment extends Fragment {
    // the fragment initialization parameters
    private static final String ARG_ACTUALNUMBER = "actual";
    private static final String ARG_FACESNUMBER = "faces";

    private int actualNumeber;
    private int facesNumber;
    private int generatedCasual;

    private TextView generatedNumber;
    private LinearLayout fragment_layout;


    public DiceFragment() {
        // Required empty public constructor
    }

    private int generateCasual() {
        Random r = new Random();
        int ris = 0;
        do {
            ris = r.nextInt(facesNumber);
        } while (ris == actualNumeber);
        return ris;
    }

    public int getGeneratedCasual() {
        return this.generatedCasual;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param actualNumber current face number.
     * @param facesNumber  total faces number.
     * @return A new instance of fragment DiceFragment.
     */
    public static DiceFragment newInstance(int actualNumber, int facesNumber) {
        DiceFragment fragment = new DiceFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_ACTUALNUMBER, actualNumber);
        args.putInt(ARG_FACESNUMBER, facesNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            actualNumeber = getArguments().getInt(ARG_ACTUALNUMBER);
            facesNumber = getArguments().getInt(ARG_FACESNUMBER);
            generatedCasual = generateCasual();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dice, container, false);

        fragment_layout = (LinearLayout) view.findViewById(R.id.fragment_layout);
        generatedNumber = (TextView) view.findViewById(R.id.generatedNumber);

        if (generatedCasual % 2 == 0) {
            fragment_layout.setBackgroundColor(getResources().getColor(R.color.colorEaven));
        } else {
            fragment_layout.setBackgroundColor(getResources().getColor(R.color.colorOdd));
        }

        generatedNumber.setText(String.valueOf(generatedCasual));

        return view;
    }

}

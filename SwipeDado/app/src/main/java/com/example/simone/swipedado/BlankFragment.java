package com.example.simone.swipedado;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    int mParam1;
    public BlankFragment() {
        // Required empty public constructor
    }


    public static BlankFragment newInstance(int numeri) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt("numero", numeri);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt("numero");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank2, container, false);
        ImageView img = view.findViewById(R.id.facce);
        int numero = getArguments().getInt("numero");
        switch (numero) {
            case 1:
                img.setImageResource(R.drawable.uno);
                break;
            case 2:
                img.setImageResource(R.drawable.due);
                break;
            case 3:
                img.setImageResource(R.drawable.tre);
                break;
            case 4:
                img.setImageResource(R.drawable.quattro);
                break;
            case 5:
                img.setImageResource(R.drawable.cinque);
                break;
            case 6:
                img.setImageResource(R.drawable.sei);
                break;
        }
        return view;
    }
}



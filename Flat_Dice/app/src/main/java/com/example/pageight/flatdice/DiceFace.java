package com.example.pageight.flatdice;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DiceFace extends Fragment {
    private static final String ARG_PARAM = "face";
    private int mParam;

    public DiceFace() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param faceNumber Parameter 1.
     * @return A new instance of fragment DiceFace.
     */
    public static DiceFace newInstance(int faceNumber) {
        DiceFace fragment = new DiceFace();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, faceNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getInt(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dice_face, container, false);
        ((TextView)v.findViewById(R.id.faceNumber)).setText(Integer.toString(mParam));
        int imgid;
        switch (mParam){
            case 1:
                imgid = R.drawable.one;
                break;
            case 2:
                imgid = R.drawable.two;
                break;
            case 3:
                imgid = R.drawable.three;
                break;
            case 4:
                imgid = R.drawable.four;
                break;
            case 5:
                imgid = R.drawable.five;
                break;
            case 6:
                imgid = R.drawable.six;
                break;
            default:
                imgid = R.drawable.placeholder;
        }
        ((ImageView)v.findViewById(R.id.face)).setImageDrawable(getActivity().getDrawable(imgid));
        return v;
    }
}

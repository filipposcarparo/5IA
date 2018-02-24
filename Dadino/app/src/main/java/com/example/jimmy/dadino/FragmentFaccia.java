package com.example.jimmy.dadino;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentFaccia extends Fragment {

    private int n;
    TextView caso;

    public static FragmentFaccia newInstance(int x){
        int n=0;
        do{
            n=(int)(Math.random()*6+1);
        }while(n==x);
        Bundle bundle=new Bundle();
        bundle.putInt("n",n);
        FragmentFaccia ris=new FragmentFaccia();
        ris.setArguments(bundle);
        return ris;
    }

    public int getN(){
        return n;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View ris= inflater.inflate(R.layout.fragment_faccia, container, false);
        caso = ris.findViewById(R.id.numero);

        Bundle args=getArguments();
        n=args.getInt("n");
        switch(n){
            case 1:
                caso.setText(1+"");
            case 2:
                caso.setText(2+"");
                break;
            case 3:
                caso.setText(3+"");
                break;
            case 4:
                caso.setText(4+"");
                break;
            case 5:
                caso.setText(5+"");
                break;
            case 6:
                caso.setText(6+"");
                break;
        }
        return ris;
    }

}

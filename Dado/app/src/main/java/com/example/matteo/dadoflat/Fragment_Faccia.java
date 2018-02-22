package com.example.matteo.dadoflat;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
/**
 * Created by Matteo Mognato on 20/02/18.
 */
public class Fragment_Faccia extends Fragment {

    /**
     * Crea una nuova istanza della classe Fragment_Faccia
     * @param n è l'intero del numero della faccia del dado verra passato a onCreate con un Bundle
     * @return fragment che è un oggetto Fragment_Faccia
     */
    public static Fragment_Faccia newinstance(int n) {
        Bundle bundle = new Bundle();
        bundle.putInt("numero", n);
        Fragment_Faccia fragment = new Fragment_Faccia();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * Crea la vista del fragment inserendo in base al numero di faccia richiesto l'opportuna immagine
     * da impostare come faccia del dado
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dado, container, false);
        ImageView img = view.findViewById(R.id.faccia);
        int numero = getArguments().getInt("numero");
        switch (numero) { //a seconda del caso mette immagine diversa
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

package com.example.matteo.dadoflat;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;
import java.util.Random;
/**
 * Created by Matteo Mognato on 20/02/18.
 */
public class MainActivity extends AppCompatActivity {

    float x1, x2, y1, y2; //coordinate dei vari punti per gestire i click sullo schermo
    final static float MIN_DISTANCE = 150.0f; //distanza minima per considerare fatto uno swipe
    Random r;
    int numeroFaccia; //è il numero della faccia uscita per ultima x evitare che esca la stessa faccia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment) != null) { //metto il fragment iniziale
            Fragment_Start f = new Fragment_Start();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, f);
            ft.commit();
        }
        r = new Random();
        numeroFaccia = 0;
    }

    public boolean onTouchEvent(MotionEvent event) { //serve a gestire i vari casi di pressione sullo schermo
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //preme
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP: //non più schiaggiato
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = Math.abs(x2 - x1);
                float deltaY = Math.abs(y2 - y1);
                if (deltaX > MIN_DISTANCE || deltaY > MIN_DISTANCE) { //swipe
                    if (deltaX > deltaY) {
                        if (x2 > x1) { //da sx a dx
                            changeFragment(1);
                        } else { //da dx a sx
                            changeFragment(2);
                        }
                    } else {
                        if (y2 > y1) { //da su a giù
                            changeFragment(3);
                        } else { //da giù a su
                            changeFragment(4);
                        }
                    }
                } else { //TAP sullo schermo
                    Toast.makeText(this, "TAP", Toast.LENGTH_SHORT).show();
                }
        }
        return super.onTouchEvent(event);
    }

    public void changeFragment(int caso) {
        if (findViewById(R.id.fragment) != null) {
            //genero numero della nuova faccia del dado
            int numero;
            do {
                numero = r.nextInt() % 7;
                numero *= numero < 0 ? -1 : 1;
            } while (numero == 0 || numeroFaccia == numero);
            numeroFaccia = numero;

            Fragment_Faccia nextFragment = Fragment_Faccia.newinstance(numeroFaccia);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            switch (caso) { //a seconda del caso e quindi dello swipe fatto ci saranno animazioni diverse
                case 1:
                    ft.setCustomAnimations(R.animator.slide_in_left, R.animator.fade);
                    break;
                case 2:
                    ft.setCustomAnimations(R.animator.slide_in_right, R.animator.fade);
                    break;
                case 3:
                    ft.setCustomAnimations(R.animator.slide_in_up, R.animator.fade);
                    break;
                case 4:
                    ft.setCustomAnimations(R.animator.slide_in_down, R.animator.fade);
                    break;
            }
            ft.replace(R.id.fragment, nextFragment);
            ft.commit();
        }
    }
}
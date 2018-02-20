package com.pag.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Game extends AppCompatActivity implements Observer {

    Board board;
    TableLayout grid;
    int size = 3;
    int[] lastMove;
    boolean isPvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        lastMove = new int[2];
        Bundle bundle = getIntent().getExtras();
        isPvp = bundle.getBoolean("isPvp");

    }

    @Override
    public void update(Observable observable, Object o) {

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r0c0:
                lastMove = new int[]{0, 0};
                break;
            case R.id.r0c1:
                lastMove = new int[]{0, 1};
                break;
            case R.id.r0c2:
                lastMove = new int[]{0, 2};
                break;
            case R.id.r1c0:
                lastMove = new int[]{1, 0};
                break;
            case R.id.r1c1:
                lastMove = new int[]{1, 1};
                break;
            case R.id.r1c2:
                lastMove = new int[]{1, 2};
                break;
            case R.id.r2c0:
                lastMove = new int[]{2, 0};
                break;
            case R.id.r2c1:
                lastMove = new int[]{2, 1};
                break;
            case R.id.r2c2:
                lastMove = new int[]{2, 2};
                break;
        }
    }

    /*private boolean check(Player current) {
        int idCheck = current.getDrawable();
        int[][] values = board.getBoard();
        boolean isFinished = false;
        if (board.getTurn() > 4) {
            for (int y = 0; y < size && !isFinished; y++) {
                int x = 0;
                while (x < 3 && values[y][x] == idCheck) { //rows
                    x++;
                }
                isFinished = (x == 3);
                x = 0;
                while (x < 3 && values[x][y] == idCheck && !isFinished) { //columns
                    x++;
                }
                isFinished = (x == 3) || isFinished;
            }
            if (isFinished) {
                Toast.makeText(this, current.getName() + " ha vinto", Toast.LENGTH_LONG).show();
                return isFinished;
            }
            int dAdBs = 0;
            int dAsBd = 0;
            for (int i = 0; i < 3; i++) {
                if (values[i][i] == idCheck) { //diagonal \
                    dAdBs++;
                }
                if (values[i][3 - i - 1] == idCheck) { //diagonal /
                    dAsBd++;
                }
            }
            return isFinished || dAdBs == 3 || dAsBd == 3;
        } else {
            return false;
        }
    }*/

    private void updateGridCell(int[] pos, int drawableId) {
        TableRow tr = (TableRow) grid.getChildAt(pos[0]);
        ImageView v = (tr.getChildAt(pos[1]).findViewById(R.id.icon));
        v.setImageDrawable(getDrawable(drawableId));
    }

}

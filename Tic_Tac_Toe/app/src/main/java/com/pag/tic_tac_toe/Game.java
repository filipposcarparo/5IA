package com.pag.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Observable;
import java.util.Observer;

public class Game extends AppCompatActivity implements Observer {

    Board board;
    TableLayout grid;
    final int size = 3;
    boolean isPvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent i = getIntent();
        isPvp = i.getExtras().getBoolean("isPvp");
        Player p1 = (Player) i.getSerializableExtra("player1");
        Player p2 = (Player) i.getSerializableExtra("player2");

        board = new Board(size, p1, p2);
        board.addObserver(this);

        grid = findViewById(R.id.tictactoe);
    }

    @Override
    public void update(Observable observable, Object o) {
        Object[] data = (Object[]) o;
        int[] cellToUpdate = (int[]) data[0];
        Player requestingUpdate = (Player) data[1];
        TableRow tr = (TableRow) grid.getChildAt(cellToUpdate[0]);
        ImageView v = (tr.getChildAt(cellToUpdate[1]).findViewById(R.id.icon));
        v.setImageDrawable(getDrawable(board.getCurrentPlayer().getIconId()));
        if (board.check()) {
            requestingUpdate.victory();
        } else {
            board.gotoNextPlayer();
        }
    }

    public void onClick(View v) {
        int[] lastMove = new int[2];
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
        board.updateBoard(lastMove);
        if (!isPvp) {
            board.updateBoard(board.getComPlayer().doMove(board));
        }
    }

}

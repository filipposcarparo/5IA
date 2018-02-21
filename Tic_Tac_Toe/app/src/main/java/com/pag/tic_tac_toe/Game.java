package com.pag.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

        updatePlayerData(p1);
        updatePlayerData(p2);
        updateTurn();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o == null) {
            for(int i = 0; i < size; i++){
                TableRow tr = (TableRow) grid.getChildAt(i);
                for(int j = 0; j < size; j++){
                    ImageView v = (tr.getChildAt(j).findViewById(R.id.icon));
                    v.setImageResource(android.R.color.transparent);
                }
            }
        }else{
            Object[] data = (Object[]) o;
            int[] cellToUpdate = (int[]) data[0];
            Player requestingUpdate = (Player) data[1];
            TableRow tr = (TableRow) grid.getChildAt(cellToUpdate[0]);
            ImageView v = (tr.getChildAt(cellToUpdate[1]).findViewById(R.id.icon));
            v.setImageDrawable(getDrawable(board.getCurrentPlayer().getIconId()));
            if (board.check()) {
                requestingUpdate.victory();
                updatePlayerData(requestingUpdate);
                board.resetBoard();
            } else {
                board.gotoNextPlayer();
                updateTurn();
            }
        }
    }

    private void updatePlayerData(Player p) {
        View v = findViewById(getIdByPlayer(p));
        ((TextView) v.findViewById(R.id.player_name)).setText(p.getName());
        ((TextView) v.findViewById(R.id.player_score)).setText(Integer.toString(p.getScore()));
    }

    private void updateTurn() {
        View p1 = findViewById(R.id.p1);
        View p2 = findViewById(R.id.p2);
        if(board.getCurrentPlayer().getCellState() == CellState.X){
            ((ImageView) p1.findViewById(R.id.player_turn)).setImageDrawable(getDrawable(R.drawable.turn));
            ((ImageView) p2.findViewById(R.id.player_turn)).setImageResource(android.R.color.transparent);
        }else{
            ((ImageView) p2.findViewById(R.id.player_turn)).setImageDrawable(getDrawable(R.drawable.turn));
            ((ImageView) p1.findViewById(R.id.player_turn)).setImageResource(android.R.color.transparent);
        }
    }

    private int getIdByPlayer(Player p) {
        int id;
        if (p.getCellState() == CellState.X) id = R.id.p1;
        else id = R.id.p2;
        return id;
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

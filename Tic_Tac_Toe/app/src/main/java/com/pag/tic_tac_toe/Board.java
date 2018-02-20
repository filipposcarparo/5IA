package com.pag.tic_tac_toe;

import android.content.Context;
import java.util.Observable;

public class Board extends Observable {

    private final int size;
    private int turn;
    private Player p1, p2;
    private Player[][] board;

    //constructor for pvp
    Board(Context context, int size, String p1Name, String p2Name) {
        this.size = size;
        p1 = new Player(context.getDrawable(R.drawable.cross), p1Name);
        p2 = new Player(context.getDrawable(R.drawable.circle),p2Name);
        board = new Player[this.size][this.size];
        turn = 0;
    }

    //constructor for pve
    Board(int size) {
        this.size = size;
        board = new Player[this.size][this.size];
    }

    private void reset(){
        board = new Player[size][size];
    }

    Player[][] getBoard() {
        return board;
    }

    void updateBoard(int[] move, Player p) {
        int y = move[0];
        int x = move[1];
        if (board[y][x] == null) {
            board[y][x] = p;
            turn++;
            setChanged();
            notifyObservers();
            clearChanged();
        }
    }

    int getTurn() {
        return turn;
    }

    Player getP1() {
        return p1;
    }

    Player getP2() {
        return p2;
    }
}

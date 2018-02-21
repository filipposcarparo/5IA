package com.pag.tic_tac_toe;

import java.io.Serializable;

public class Player implements Serializable {

    private int iconID;
    private String name;
    private int score;
    private boolean isCOM;
    private CellState cs;


    public Player(int iconID, String name, boolean isCom) {
        this.iconID = iconID;
        this.name = name;
        this.score = 0;
        this.isCOM = isCom;
        if(iconID == R.drawable.cross){
            cs = CellState.X;
        }else if(iconID == R.drawable.circle){
            cs = CellState.O;
        }else{
            cs = CellState.EMPTY;
        }
    }

    public CellState getCellState(){return cs;}

    public int getIconId() {
        return iconID;
    }

    public void victory() {
        this.score++;
    }

    public boolean isCOM() {
        return isCOM;
    }

    public String getName() {
        return name;
    }

    public int getScore(){return score;}

    public boolean equals(Player other){
        return this.cs == other.getCellState();
    }

    int[] doMove(Board board) {
        return nextRandomMove(board);
    }

    private int[] nextRandomMove(Board board) {
        CellState[][] cells = board.getBoard();
        int[][] preferredMoves = {
                {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
                {0, 1}, {1, 0}, {1, 2}, {2, 1}};
        for (int[] move : preferredMoves) {
            if (cells[move[0]][move[1]] == CellState.EMPTY) return move;
        }
        return new int[2];
    }

    //TODO implement heuristic method to calculate next best move
    private int[] nextBestMove(Board board) {
        throw new UnsupportedOperationException(String.valueOf(R.string.unsupportedException));
    }
}

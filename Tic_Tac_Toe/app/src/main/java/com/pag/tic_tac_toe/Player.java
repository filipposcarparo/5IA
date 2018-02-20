package com.pag.tic_tac_toe;
import android.graphics.drawable.Drawable;

public class Player {

    private Drawable icon;
    private String name;
    private int score;

    public Player(Drawable icon, String name) {
        this.icon = icon;
        this.name = name;
        this.score = 0;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void victory() {
        this.score++;
    }

    /*
    int[] doMove() {
        return isHeuristic?nextBestMove():nextRandomMove();
    }

    private int[] nextRandomMove() {
        int[][] preferredMoves = {
                {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
                {0, 1}, {1, 0}, {1, 2}, {2, 1}};
        for (int[] move : preferredMoves) {
            if (boardCells[move[0]][move[1]] == Player.PLAYER_NONE.getDrawable()) return move;
        }
        return new int[2];
    }

    //TODO implement heuristic method to calculate next best move
    private int[] nextBestMove() {
        throw new UnsupportedOperationException(String.valueOf(R.string.unsupportedException));
    }
    */
}

package com.pag.tic_tac_toe;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class Board extends Observable {

    private final int size;
    private Player p1, p2, current;
    private CellState[][] board;
    private int turn;

    Board(int size, Player p1, Player p2) {
        this.size = size;
        this.p1 = p1;
        this.p2 = p2;
        board = new CellState[this.size][this.size];
        resetBoard();
        if (p2.isCOM()) {
            current = p1;
        } else {
            current = (ThreadLocalRandom.current().nextBoolean()) ? p1 : p2;
        }
    }

    CellState[][] getBoard() {
        return board;
    }

    void resetBoard() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = CellState.EMPTY;
            }
        }
        turn = 0;
        setChanged();
        notifyObservers();
    }

    void updateBoard(int[] move) {
        Object[] data = new Object[3];
        data[0] = move;
        data[1] = current;
        int y = move[0];
        int x = move[1];
        if (board[y][x] == CellState.EMPTY) {
            board[y][x] = current.getCellState();
            turn++;
            setChanged();
            notifyObservers(data);
        }
    }

    Player getComPlayer() {
        return p2;
    }

    Player getCurrentPlayer() {
        return current;
    }

    void gotoNextPlayer() {
        current = (current.equals(p1)) ? p2 : p1;
    }

    boolean check() {
        boolean isFinished = false;
        int dAdBs = 0;
        int dAsBd = 0;
        if(turn > 4) {
            for (int y = 0; y < size && !isFinished; y++) {
                int x = 0;
                while (x < 3 && board[y][x] == current.getCellState()) { //rows
                    x++;
                }
                isFinished = (x == 3);
                x = 0;
                while (x < 3 && board[x][y] == current.getCellState()) { //columns
                    x++;
                }
                isFinished = (x == 3) || isFinished;
            }
            if (isFinished) {
                return isFinished;
            }
            for (int i = 0; i < 3; i++) {
                if (board[i][i] == current.getCellState()) { //diagonal \
                    dAdBs++;
                }
                if (board[i][3 - i - 1] == current.getCellState()) { //diagonal /
                    dAsBd++;
                }
            }
        }
        return isFinished || dAdBs == 3 || dAsBd == 3;
    }

    int getTurn(){return turn;}
}

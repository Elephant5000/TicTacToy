package ru.job4j.tictactoy;

/**
 * Класс реализующий логику игры крестики нолики
 * whoseMove - чей ход. false - первый игрок, true - второй
 * playerPC - с кем играем. false - c человеком, true - с компьютером
 * board - игровое поле. 0 - пусто. 1 - крестик, 2 - нолик.

 */

public class Logic {
    private boolean whoseMove = false;
    private boolean playerPC = false;
    private byte[][] board = {{0,0,0}, {0,0,0}, {0,0,0}};

    public boolean isWhoseMove() {
        return whoseMove;
    }

    public void setWhoseMove(boolean whoseMove) {
        this.whoseMove = whoseMove;
    }

    public boolean isPlayerPC() {
        return playerPC;
    }

    public void setPlayerPC(boolean playerPC) {
        this.playerPC = playerPC;
    }

    public byte[][] getBoard() {
        return board;
    }

    public void setBoard(byte[][] board) {
        this.board = board;
    }

    public void setMove(int move) {
        this.board[move / 3][move % 3] = (byte) (whoseMove ? 2 : 1);
        this.whoseMove = !this.whoseMove;
    }


}

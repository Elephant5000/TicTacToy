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
    private byte moveCounter = 0;
    private byte[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0};


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

    public byte getMoveCounter() {
        return this.moveCounter;
    }

    public void setMoveCounter(byte moveCounter) {
        this.moveCounter = moveCounter;
    }

    public void incMoveCounter () {
        this.moveCounter++;
    }

    public byte[] getBoard() {
        return board;
    }

    public void setBoard(byte[] board) {
        this.board = board;
    }

    public void setMove(int move) {
        if (this.board[move] != 0) return;
        this.board[move] = (byte) (whoseMove ? 2 : 1);
        this.whoseMove = !this.whoseMove;
        incMoveCounter();
    }

    public byte checkWin() {
        byte win = 0;
        if (this.board[0] != 0 && this.board[0] == this.board[1] && this.board[0] == this.board[2])  {
            win = this.board[0];
            return win;
        }
        if (this.board[3] != 0 && this.board[3] == this.board[4] && this.board[3] == this.board[5])  {
            win = this.board[3];
            return win;
        }
        if (this.board[6] != 0 && this.board[6] == this.board[7] && this.board[6] == this.board[8])  {
            win = this.board[6];
            return win;
        }
        if (this.board[0] != 0 && this.board[0] == this.board[3] && this.board[0] == this.board[6])  {
            win = this.board[0];
            return win;
        }
        if (this.board[1] != 0 && this.board[1] == this.board[4] && this.board[1] == this.board[7])  {
            win = this.board[1];
            return win;
        }
        if (this.board[2] != 0 && this.board[2] == this.board[5] && this.board[2] == this.board[8])  {
            win = this.board[2];
            return win;
        }
        if (this.board[0] != 0 && this.board[0] == this.board[4] && this.board[0] == this.board[8])  {
            win = this.board[0];
            return win;
        }
        if (this.board[2] != 0 && this.board[2] == this.board[4] && this.board[2] == this.board[6])  {
            win = this.board[2];
            return win;
        }
        return win;
    }

    public void newGame() {
        this.setWhoseMove(false);
        this.setMoveCounter((byte) 0);
        for (int index = 0; index <9; index ++) {
            this.board[index] = 0;
        }
    }

}

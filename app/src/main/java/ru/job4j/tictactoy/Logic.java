package ru.job4j.tictactoy;

/**
 * Класс реализующий логику игры крестики нолики
 * whoseMove - чей ход. false - первый игрок (X), true - второй (O)
 * playerPC - с кем играем. false - c человеком, true - с компьютером
 * nextFirstMove - кто ходит первым в игре. false - первый игрок (X), true - второй (O)
 * endGame - флвг окончания игры (Нужен для блокировки кнопок в законченной партии)
 * moveCounter - счетчик ходов. Для определеня окончания игры в случае отсутствия победителя
 * board - игровое поле. 0 - пусто. 1 - крестик, 2 - нолик.

 */

public class Logic {
    private static Logic instance;
    private boolean whoseMove;
    private boolean playerPC;
    private boolean nextFirstMove;
    private boolean endGame;
    private byte moveCounter;
    private byte[] board = new byte[9];

    private Logic() {
        this.whoseMove = false;
        this.playerPC = false;
        this.nextFirstMove = false;
        this.endGame = false;
        this.moveCounter = 0;
        for (int index = 0; index <9; index ++) {
            this.board[index] = 0;
        }
    }

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

    public boolean isNextFirstMove() {
        return nextFirstMove;
    }

    public void setNextFirstMove(boolean nextFirstMove) {
        this.nextFirstMove = nextFirstMove;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public static Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
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

    public void newGame(boolean firstMove) {
        this.setWhoseMove(firstMove);
        this.setMoveCounter((byte) 0);
        for (int index = 0; index <9; index ++) {
            this.board[index] = 0;
        }
        this.endGame = false;
    }

    public void aiMove() {
        if (board[4] == 0) {
            setMove(4);
        } else {
            for (int index = 0; index <= 8; index++) {
                if (board[index] == 0) {
                    setMove(index);
                    break;
                }
            }
        }
    }
}

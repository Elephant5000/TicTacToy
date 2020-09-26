package ru.job4j.tictactoy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LogicTest {

    @Test
    public void checkWinHor1() {
        Logic game = Logic.getInstance();
        byte[] actual = {1,1,1,2,2,0,0,0,0};
        game.setBoard(actual);
        byte expected = 1;
        assertThat(game.checkWin(), is(expected));
    }

    @Test
    public void checkWinHor2() {
        Logic game = Logic.getInstance();
        byte[] actual = {1,0,1,2,2,2,1,0,0};
        game.setBoard(actual);
        byte expected = 2;
        assertThat(game.checkWin(), is(expected));
    }

    @Test
    public void checkWinVert1() {
        Logic game = Logic.getInstance();
        byte[] actual = {1,0,1,1,2,0,1,0,0};
        game.setBoard(actual);
        byte expected = 1;
        assertThat(game.checkWin(), is(expected));
    }

    @Test
    public void checkWinVert2() {
        Logic game = Logic.getInstance();
        byte[] actual = {1,2,1,0,2,2,1,2,0};
        game.setBoard(actual);
        byte expected = 2;
        assertThat(game.checkWin(), is(expected));
    }

    @Test
    public void checkWinDiag1() {
        Logic game = Logic.getInstance();
        byte[] actual = {1,2,1,0,1,2,1,2,1};
        game.setBoard(actual);
        byte expected = 1;
        assertThat(game.checkWin(), is(expected));
    }


}
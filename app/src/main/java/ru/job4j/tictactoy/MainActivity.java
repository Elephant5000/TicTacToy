package ru.job4j.tictactoy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Logic game = Logic.getInstance();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            game.setBoard(savedInstanceState.getByteArray("GameBoard"));
            game.setWhoseMove(savedInstanceState.getBoolean("WhoseMove"));
            game.setPlayerPC(savedInstanceState.getBoolean("PlayerPC"));
            game.setMoveCounter(savedInstanceState.getByte("MoveCounter"));
            findViewById(R.id.buttonNewGame).setVisibility(savedInstanceState.getInt("ButtonNewGame"));
        }
        paintBoard(game);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putByteArray("GameBoard", game.getBoard());
        outState.putBoolean("WhoseMove", game.isWhoseMove());
        outState.putBoolean("PlayerPC", game.isPlayerPC());
        outState.putByte("MoveCounter", game.getMoveCounter());
        outState.putInt("ButtonNewGame", findViewById(R.id.buttonNewGame).getVisibility());
    }

    /**
     * Обработчик нажатия кнопок
     * Все кнопки имеют заполненное поле tag заполненное соответствующим значением от 0 до 8.
     * Передаем это значение в метод game.setMove(int index) и перерисовываем игровое поле.
     * @param view
     */

    public void onButtonClick(View view) {
        if (game.isEndGame()) return;
        int buttonIndex = Integer.parseInt(view.getTag().toString());
        if (game.getBoard()[buttonIndex] == 0) {
            game.setMove(buttonIndex);
            paintBoard(game);
            if (game.checkWin() > 0 || game.getMoveCounter() > 8) {
                endGame();
            } else if (game.isPlayerPC()) {
                pcMove();
            }
        }
    }

    public void pcMove() {
        if (game.isPlayerPC() && game.isWhoseMove()) {
            for (int index = 0; index <= 8; index++) {
                // Тут реализация алгоритма AI (пока отсутствует)
                if (game.getBoard()[index] == 0) {
                    game.setMove(index);
                    break;
                }
            }
            paintBoard(game);
            if (game.checkWin() > 0 || game.getMoveCounter() > 8) {
                endGame();
            }
        }
    }


    public void onClickPcHuman(View view) {
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchPCHuman = findViewById(R.id.switchPCHuman);
        game.setPlayerPC(switchPCHuman.isChecked());
        pcMove();
    }


    public void paintBoard(Logic board) {
        for (int index = 0; index <= 8; index++) {
            Button button = findViewById(getResources().getIdentifier("button" + String.valueOf(index), "id", this.getPackageName()));
            if (board.getBoard()[index] == 0) {
                button.setText("");
            } else if (board.getBoard()[index] == 1) {
                button.setText("X");
            } else if (board.getBoard()[index] == 2) {
                button.setText("O");
            }
        }
    }

    public void endGame() {
        String message;
        game.setNextFirstMove(game.isWhoseMove());
        switch (game.checkWin()) {
            case 0:
                message = getString(R.string.draw);
                break;
            case 1:
                message = getString(R.string.winer) + " " + getString(R.string.x);;
                game.setNextFirstMove(true);
                break;
            case 2:
                message = getString(R.string.winer) + " " + getString(R.string.o);
                game.setNextFirstMove(false);
                break;
            default:
                message = "-------";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        findViewById(R.id.buttonNewGame).setVisibility(View.VISIBLE);
        game.setEndGame(true);
    }

    public void onClickNewGame(View view) {
        game.newGame(game.isNextFirstMove());
        paintBoard(game);
        findViewById(R.id.buttonNewGame).setVisibility(View.INVISIBLE);
        pcMove();
    }
}
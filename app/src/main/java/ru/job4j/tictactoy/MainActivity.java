package ru.job4j.tictactoy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Logic game = new Logic();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            game.setBoard(savedInstanceState.getByteArray("GameBoard"));
            game.setWhoseMove(savedInstanceState.getBoolean("WhoseMove"));
            game.setWhoseMove(savedInstanceState.getBoolean("PlayerPC"));
            game.setMoveCounter(savedInstanceState.getByte("MoveCounter"));
            Log.d(TAG, "Restore - MoveCounter - " + String.valueOf(savedInstanceState.getByte("MoveCounter")));
            Log.d(TAG, "Restore - WhoseMove - " + savedInstanceState.getBoolean("WhoseMove"));
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
        Log.d(TAG, "Save MoveCounter - " + String.valueOf(game.getMoveCounter()));
        Log.d(TAG, "Save WhoseMove - " + game.isWhoseMove());
    }

    /**
     * Обработчик нажатия кнопок
     * Все кнопки имеют заполненное поле tag заполненное соответствующим значением от 0 до 8.
     * Передаем это значение в метод game.setMove(int index) и перерисовываем игровое поле.
     * @param view
     */

    public void onButtonClick(View view) {
        int buttonIndex = Integer.parseInt(view.getTag().toString());
        if (game.getBoard()[buttonIndex] == 0) {
            game.setMove(buttonIndex);
            paintBoard(game);
            if (game.checkWin() > 0 || game.getMoveCounter() > 8) {
                endGame();
            }
        }
        Log.d(TAG, "Click MoveCounter - " + String.valueOf(game.getMoveCounter()));
        Log.d(TAG, "Click WhoseMove - " + game.isWhoseMove());
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
            } else {
                button.setText("E");
            }
        }
    }

    public void endGame() {
        game.newGame();
    }

}
package ru.job4j.tictactoy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Logic game = new Logic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paintBoard(game);
    }

    public void onButtonClick(View view) {
        String g =  getResources().getResourceName(view.getId());
        g = g.substring(g.length()-1);
        int i = Integer.parseInt(g);
        game.setMove(i);
        paintBoard(game);
    }

    public void paintBoard(Logic board) {
        for (int index = 0; index <= 8; index++) {
            Button button = findViewById(getResources().getIdentifier("button" + String.valueOf(index), "id", this.getPackageName()));
            if (board.getBoard()[index / 3][index % 3] == 0) {
                button.setText("");
            } else if (board.getBoard()[index / 3][index % 3] == 1) {
                button.setText("X");
            } else if (board.getBoard()[index / 3][index % 3] == 2) {
                button.setText("O");
            } else {
                button.setText("E");
            }
        }
    };

}
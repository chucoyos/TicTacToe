package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    ImageView top_left, top_center, top_right, mid_left,
            mid_center, mid_right, bottom_left, bottom_center, bottom_right;
    boolean gameOver = false;
    int activePlayer = 1;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                 {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                 {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top_left = findViewById(R.id.top_left);
        top_center = findViewById(R.id.top_center);
        top_right = findViewById(R.id.top_right);

        mid_left = findViewById(R.id.mid_left);
        mid_center = findViewById(R.id.mid_center);
        mid_right = findViewById(R.id.mid_right);

        bottom_left = findViewById(R.id.bottom_left);
        bottom_center = findViewById(R.id.bottom_center);
        bottom_right = findViewById(R.id.bottom_right);

    }

    public void play(View view){
        ImageView counter = (ImageView) view;
        if (!gameOver){
            counter.setAlpha(1f);
            counter.setTranslationY(-1500);
            counter.setClickable(false);
        }

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        gameState[tappedCounter] = activePlayer;

        if (activePlayer == 1){
            counter.setImageResource(R.drawable.red_x);
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            activePlayer = 0;
        } else if (activePlayer == 0){
            counter.setImageResource(R.drawable.red_v);
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            activePlayer = 1;
        }

        for (int[] winningPosition : winningPositions){
            if (
                    gameState[winningPosition[0]] == gameState[winningPosition[1]]
                    && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2 ) {
                String winner = "";
                if (activePlayer == 1){
                    winner = "V";
                } else if(activePlayer == 0){
                    winner = "X";
                }

                if (!gameOver){
                    Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();
                }

                gameOver = true;
            }
        }
    }
}
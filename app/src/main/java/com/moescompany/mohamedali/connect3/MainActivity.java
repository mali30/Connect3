package com.moescompany.mohamedali.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: yellow
    // 1: red
    // 2: empty
    int activePlayer = 0;

    int [] gameState = {2,2,2,2,2,2,2,2,2};

    // these are the positions in which a user would win the game
    int [][] winningPositions = {{0,1,2}, {3,4,5} , {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};

    boolean gameActive = true;

    String winner = "";



    public void dropIn(View view){


        // this is the view thats touched or box thats touched
        ImageView counter = (ImageView) view;


        // updating game state
        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        // if we tap counter already there nothing happens. This way you cant keep choosing the same spot.
        if(gameState[tappedCounter] == 2 && gameActive) {

            // updating value based on the active player
            gameState[tappedCounter] = activePlayer;

            // displaying the tag when the image view is clicked
            Log.i("Tag", "the tag clicked is " + counter.getTag().toString());

            // this will drop the image down the screen
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }

            counter.animate().rotation(3600).translationYBy(1500).setDuration(300);

            // looping through 2d array
            // check if the first coin is equal to the next and equal to the next and the its not equal to 2
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]]
                        == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    if (activePlayer == 1) {
                        winner = "yellow";
                    } else {
                        winner = "red";
                    }
                    // someone has won the game
                    Button playAgain = findViewById(R.id.playAgainButton);
                    TextView textView = findViewById(R.id.winnerTextView);

                    textView.setText("Congrats " + winner);
                    playAgain.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);

//                    gameActive = false;
                }
            }
        }


    }

    public void playAgain(View view){

        // someone has won the game
        Button playAgain = findViewById(R.id.playAgainButton);
        TextView textView = findViewById(R.id.winnerTextView);

        playAgain.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.myGridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            // do stuff with child view
            child.setImageDrawable(null);

        }

        // change back to default
        activePlayer = 0;

        for(int i =0; i < gameState.length; i++){
            gameState[i] = 2;
        }

         gameActive = true;




    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

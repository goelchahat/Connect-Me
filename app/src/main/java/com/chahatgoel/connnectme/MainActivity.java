package com.chahatgoel.connnectme;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
int activePlayer=0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int tappedCounter;
    String winner="RED";
    Boolean gameIsActive=true;
   // AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        public void dropIn(View view)  {
            ImageView counter = (ImageView) view;
            tappedCounter = Integer.parseInt(counter.getTag().toString());

            if (gameState[tappedCounter] == 2 && gameIsActive )
            {

                gameState[tappedCounter] = activePlayer;

               //counter.setTranslationY(-1000f);

                if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;

            }

            // counter.animate().translationYBy(1000f).rotation(360).setDuration(3000);
            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2)
                { gameIsActive= false;
                winner="RED won!";



                    if (gameState[winningPosition[0]] == 0) {

                         winner = "YELLOW won!";

                    }
//                     dlgAlert.setMessage(winner+" won!")
//                    .setTitle("congratulations")
//                    .setCancelable(true)
//                    .setPositiveButton("Ok",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                   // finish();
//                                }
//                            })
//                    .create().show();
                  Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
                    //Thread.sleep(5000);
//                    long time= System.currentTimeMillis();
//                    while (System.currentTimeMillis()<time+5000);
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);
                }
                    else {

                        boolean gameIsOver = true;

                        for (int counterState : gameState) {

                            if (counterState == 2) gameIsOver = false;

                        }  if (gameIsOver) {
//                        dlgAlert.setMessage("DRAW")
//                        .setTitle("congratulations")
//                        .setCancelable(true)
//                        .setPositiveButton("Ok",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                       // finish();
//                                    }
//                                })
//                        .create().show();


                        Toast.makeText(this, "Sigh! It's a draw!", Toast.LENGTH_SHORT).show();
//                        long time= System.currentTimeMillis();
//                        while (System.currentTimeMillis()<time+5000);

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }

                }
                }
            }
    }
    public void playAgain(View view)  {

        gameIsActive = true;



        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }
    public void exit(View view)
    {ImageButton btn= findViewById(R.id.btn);
    btn.setOnClickListener(new View.OnClickListener() {


        @Override
        public void onClick(View v) { Toast.makeText(MainActivity.this, "Tap again to exit", Toast.LENGTH_SHORT).show();


           // finish();;
            android.os.Process.killProcess(android.os.Process.myPid());
            Toast.makeText(MainActivity.this, "Tap again to exit", Toast.LENGTH_SHORT).show();
            System.exit(1);
            Toast.makeText(MainActivity.this, "Tap again to exit", Toast.LENGTH_SHORT).show();
        }
    });
    }
}

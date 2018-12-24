package com.example.shivam.oreoexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

//    Random rand = new Random();
//    int randomNum = rand.nextInt((20 - 1) + 1) + 1;
    int activeplayer = 0;
    int[] gamestate = {2,2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {0,3,6}, {1,4,7}, {2,5,8}, {2,4,6}};
    boolean flag = false;

    // 0-red; 1-yellow; 2-blank

    public void dropin(View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (flag == false ) {

            if (gamestate[tappedCounter] == 2) {

                gamestate[tappedCounter] = activeplayer;
                counter.setTranslationY(-1000);

                if (activeplayer == 0) {

                    activeplayer = 1;
                    counter.setImageResource(R.drawable.red);
                }
                else {

                    activeplayer = 0;
                    counter.setImageResource(R.drawable.yellow);
                }

                counter.animate().translationYBy(1000).rotation(3600).setDuration(400);

                Log.i("Info", "Image clicked! ");

                for (int[] winningPosition : winningPositions) {

                    if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] && gamestate[winningPosition[1]] == gamestate[winningPosition[2]] && gamestate[winningPosition[0]] != 2) {

                        String message = "";
                        if (activeplayer == 0)
                            message = "Yellow has won";
                        else
                            message = "Red has won";

                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                        flag = true;
                        TextView textView = (TextView) findViewById(R.id.textView);
                        Button button = (Button) findViewById(R.id.button);

                        textView.setText(message);
                        textView.setVisibility(View.VISIBLE);

                        button.setVisibility(View.VISIBLE);
                    }
                }
            }
            else
                Toast.makeText(this, "Please tap on empty tiles!!!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Game Over!!!", Toast.LENGTH_SHORT).show();
    }

    public void playAgain(View view) {

        TextView textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);

        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);

        for (int i=0; i < gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i=0; i<gamestate.length; i++)
            gamestate[i] = 2;

        activeplayer = 0;
        flag = false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}

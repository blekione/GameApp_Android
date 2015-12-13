package com.blekione.wordgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridLayout.*;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private static int attempts;
    private static int attemptsCounter;
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        attemptsCounter = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.buttons_layout);

        // create reference to game difficulty level and list of games words
        int difficultyLvl = DifficultyLevelActivity.getDifficultyLvl();
        final List<String> gameWords = DifficultyLevelActivity.getGameWords();

        // randomly chose one word from list to be password
        Random random = new Random();
        final String password = gameWords.get(random.nextInt(gameWords.size()));

        // calculate how many attepts based on difficulty lvl
        attempts = difficultyLvl * 3 -1;

        // create layout dynamical based on difficulty level
        for (int i = 0; i < gameWords.size(); i++) {
            final int index = i; // to be used in OnClickListener anonymous classes
            // display words from list of gameWords
            TextView textView = new TextView(this);
            textView.setText(gameWords.get(i));
            textView.setId(i);
            LayoutParams paramTextView = new LayoutParams();
            paramTextView.columnSpec = GridLayout.spec(0);
            paramTextView.rowSpec = GridLayout.spec(i);
            gridLayout.addView(textView, paramTextView);
            // display buttons to check assigned word
            Button btnCheck = new Button(this);
            btnCheck.setText("Check");
            LayoutParams paramBtnCheck = new LayoutParams();
            paramBtnCheck.columnSpec = GridLayout.spec(1);
            paramBtnCheck.rowSpec = GridLayout.spec(i);
            gridLayout.addView(btnCheck, paramBtnCheck);
            btnCheck.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (gameWords.get(index).equals(password)) {
                        showPopupWindow(GameActivity.this, "You guess correctly");
                    } else {
                        if (attempts == ++attemptsCounter) {
                            gameOver = true;
                            showPopupWindow(GameActivity.this,
                                    "Attempts: " + attemptsCounter + "/" + attempts +".\n Game over!");
                        } else {
                            String msg = "Attempts: " + attemptsCounter + "/" + attempts + "\n";
                            msg += numberOfCorrectLetters(gameWords.get(index), password);
                            showPopupWindow(GameActivity.this, msg);
                        }
                    }
                }
            });
            // display buttons to cross the word
            Button btnCross = new Button(this);
            btnCross.setText("Cross Word");
            LayoutParams paramBtnCross = new LayoutParams();
            paramBtnCross.columnSpec = GridLayout.spec(2);
            paramBtnCross.rowSpec = GridLayout.spec(i);
            gridLayout.addView(btnCross, paramBtnCross);
            btnCross.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    TextView textView = (TextView) findViewById(index);
                    textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            });
        }



    }

    private void showPopupWindow(final Activity context, String msg) {

        // inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

        // create the popup window
        int popupWidth = 500;
        int popupHeight = 450;
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);
        popup.setOutsideTouchable(false);
        popup.setBackgroundDrawable(null);


        // display the popup at the center of the screen
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // get a reference to element in popup layout
        TextView testText = (TextView) layout.findViewById(R.id.test_popup);
        testText.setText(msg);

        // get a reference to Close button
        Button closeBtn = (Button) layout.findViewById(R.id.close);
        closeBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss(); // close popup window when close button pressed
                if (gameOver) {
                    Intent intent = new Intent(GameActivity.this, DifficultyLevelActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * return how many letters in input words are on same positions
     * @param guessWord
     * @param passwordWord
     * @return
     */
    public static String numberOfCorrectLetters(String guessWord, String passwordWord) {
        int correctLetters = 0;
        for (int i = 0; i < guessWord.length(); i++)
            if (guessWord.charAt(i) == passwordWord.charAt(i))
                correctLetters++;
        return correctLetters + "/" + guessWord.length() + " correct" + "\n";
    }

}

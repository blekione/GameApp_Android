package com.blekione.wordgame;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridLayout.*;
import android.widget.TextView;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.buttons_layout);

        int difficultyLvl = DifficultyLevelActivity.getDifficultyLvl();
        List<String> gameWords = DifficultyLevelActivity.getGameWords();
        Button btns[] = new Button[difficultyLvl*2 + 3];
        for (int i = 0; i < btns.length; i++) {
            final int index = i;
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
            btnCheck.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                        
                }
            });
            // display buttons to cross the word
            Button btnCross = new Button(this);
            btnCross.setText("Cross Word");
            LayoutParams paramBtnCross = new LayoutParams();
            paramBtnCross.columnSpec = GridLayout.spec(2);
            paramBtnCross.rowSpec = GridLayout.spec(i);
            gridLayout.addView(btnCross, paramBtnCross);
            btnCross.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    TextView textView = (TextView) findViewById(index);
                    textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            });
        }



    }

}

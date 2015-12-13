package com.blekione.wordgame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DifficultyLevelActivity extends Activity {

    private static List<String> gameWords;
    private static int difficultyLvl = 1;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificulty_level);
        this.spinner =(Spinner) findViewById(R.id.game_difficult_lvl);
        Integer[] levels = new Integer[]{1,2,3,4,5};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, levels);
        spinner.setAdapter(adapter);
    }

    public void onClickDifficultyStartGame(View view) {
        difficultyLvl = (int) spinner.getSelectedItem();
        gameWords = new ArrayList<>();
        TextView test = (TextView) findViewById(R.id.test);
        // create sublist of words depends of set difficulty

        int wordLength = difficultyLvl * 2 + 2;
        int wordsNumber = difficultyLvl * 2 + 3;
        Random random = new Random();
        CharSequence testChar = "";
        // populate gameWords with wordsNumber number of words from dictionary
        for (int i = 0; i < wordsNumber; i++) {
            String word;
            // find random word of length wordLength in dictionary
            do {
                // get random word from dictionary
                word = StartActivity.getDictionary()
                        .get(random.nextInt(StartActivity.getDictionary().size()));

            } while (word.length() != wordLength);
            gameWords.add(word);
        }
        Intent intent  = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public static int getDifficultyLvl() {
        return difficultyLvl;
    }

    public void setDifficultyLvl(int difficultyLvl) {
        this.difficultyLvl = difficultyLvl;
    }

    public static List<String> getGameWords() {
       return gameWords;
    }
}
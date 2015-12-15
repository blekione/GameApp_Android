package com.blekione.wordgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
        // populate spinner with difficulty levels
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, levels);
        spinner.setAdapter(adapter);
    }

    /**
     * when system back button is pressed it returns to start activity instead to previous displayed
     * screen
     */
    @Override
    public void onBackPressed() {
        Toast toast  = Toast.makeText(this, "Back to start screen", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return;
    }


    public void onClickDifficultyStartGame(View view) {
        difficultyLvl = (int) spinner.getSelectedItem();

        getWords();// create a sublist of words depends of set difficulty

        Intent intent  = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    /**
     * create a sublist of words based on game difficulty
     */
    private void getWords() {
        gameWords = new ArrayList<>();
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
                word = MainActivity.getDictionary()
                        .get(random.nextInt(MainActivity.getDictionary().size()));

            } while (word.length() != wordLength);
            gameWords.add(word);
        }
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
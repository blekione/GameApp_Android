package com.blekione.wordgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DificultyLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificulty_level);
        Spinner mspin=(Spinner) findViewById(R.id.game_difficult_lvl);
        Integer[] levels = new Integer[]{1,2,3,4,5};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, levels);
        mspin.setAdapter(adapter);
    }
}

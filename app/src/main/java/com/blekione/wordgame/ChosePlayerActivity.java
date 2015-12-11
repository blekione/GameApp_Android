package com.blekione.wordgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blekione.wordgame.com.blekione.wordgame.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class ChosePlayerActivity extends AppCompatActivity {

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_player);

        listView = (ListView) findViewById(R.id.list_players);
        List<String> nicksAndScore = new ArrayList<String>();
        for (Player player : StartActivity.getPlayers()) {
            nicksAndScore.add(player.getNick() + " score: " + player.getScore());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, nicksAndScore
        );
        listView.setAdapter(arrayAdapter);
    }
}

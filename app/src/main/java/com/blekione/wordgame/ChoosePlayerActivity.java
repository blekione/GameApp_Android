package com.blekione.wordgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blekione.wordgame.com.blekione.wordgame.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class ChoosePlayerActivity extends Activity {

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player);
        // populate ListView by entries from list of players
        listView = (ListView) findViewById(R.id.list_players);

        List<String> nicksAndScore = new ArrayList<String>();
        for (Player player : MainActivity.getPlayers()) {
            nicksAndScore.add(player.getNick() + " score: " + player.getScore());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, nicksAndScore
        );
        listView.setAdapter(arrayAdapter);
        // add listener for item click
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ChoosePlayerActivity.this, MainActivity.class);
                MainActivity.setLastPlayer(MainActivity.getPlayer(position + 1));
                startActivity(intent);
            }
        };
        // add the listener to the list view
        listView.setOnItemClickListener(itemClickListener);
    }
}

package com.blekione.wordgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blekione.wordgame.com.blekione.wordgame.domain.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private static List<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        TextView welcomeMsg = (TextView) findViewById(R.id.welcome_message);
        if (StartActivity.players.isEmpty()){
            welcomeMsg.setText("Welcome Guest!");
        } else {
            welcomeMsg.setText("Welcome " + getPlayer(0).getNick() + "!");
        }
    }


    public void onClickAddPlayer(View view) {
        Intent intent = new Intent(this, AddNewPlayerActivity.class);
        startActivity(intent);
    }

    public void onClickChosePlayer(View view) {
        Intent intent = new Intent(this, ChosePlayerActivity.class);
        startActivity(intent);
    }

    public static Player getPlayer(int id) {
        Iterator<Player> iterator = StartActivity.players.iterator();
        boolean playerFound = false;
        while (iterator.hasNext() && !playerFound) {
            Player player = iterator.next();
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }


    public static List<Player> getPlayers() {
        return players;
    }

    public static void setPlayer(String nick) {
        StartActivity.players.add(new Player(nick));
    }

}

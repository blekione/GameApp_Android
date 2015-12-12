package com.blekione.wordgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blekione.wordgame.com.blekione.wordgame.domain.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private static List<Player> players = new ArrayList<>();
    private static Player lastPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // reading resourced for dictionary from text file
        InputStream fstream = getResources().openRawResource(R.raw.enable1);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        List<String> dictionary = new ArrayList<>();
        String line;
        try {
            while((line = br.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException e) {
            System.out.print("problem with reading resource file");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // setting up welcome message
        TextView welcomeMsg = (TextView) findViewById(R.id.welcome_message);
        if (StartActivity.players.isEmpty()){
            welcomeMsg.setText("Welcome Guest!");
        } else {
            welcomeMsg.setText("Welcome " + lastPlayer.getNick() + "!");
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

    public void onClickStartGame(View view) {
        if (lastPlayer == null) {
            // add message promt user to chose player
        } else {
            Intent intent = new Intent(this, DificultyLevelActivity.class);
            startActivity(intent);
        }
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

    public Player getLastPlayer() {
        return lastPlayer;
    }

    public static void setLastPlayer(Player lastPlayer) {
        StartActivity.lastPlayer = lastPlayer;
    }
}

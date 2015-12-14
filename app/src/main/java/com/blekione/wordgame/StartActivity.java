package com.blekione.wordgame;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blekione.wordgame.com.blekione.wordgame.domain.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StartActivity extends Activity {

    private static List<Player> players;
    private static Player lastPlayer;
    private static List<String> dictionary = new ArrayList<>();
    private static SQLiteDatabase db;
    private static StartActivity activityRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityRef = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // reading resourced for dictionary from text file
        InputStream fstream = getResources().openRawResource(R.raw.enable1);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String line;
        try {
            while((line = br.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException e) {
            System.out.print("problem with reading resource file");
        }
        players = new ArrayList<>();

        // creating or opening database
        try {
            SQLiteOpenHelper playerDatabaseHelper = new PlayerDatabaseHelper(this);
            db = playerDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("PLAYERS", new String[] {"_id", "NICK", "SCORE"}, null, null
                    , null, null, null);
            Toast toast  = Toast.makeText(this, "records " + cursor.getCount(), Toast.LENGTH_SHORT);
            toast.show();
            while(cursor.moveToNext()) {
                Player defaultPlayer = new Player(cursor.getString(1));
                defaultPlayer.setId(cursor.getInt(0));
                defaultPlayer.setScore(cursor.getInt(2));
                players.add(defaultPlayer);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast  = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        // setting up welcome message
        TextView welcomeMsg = (TextView) findViewById(R.id.welcome_message);
        Toast toast  = Toast.makeText(this, "player: " + lastPlayer, Toast.LENGTH_SHORT);
        toast.show();
        if (lastPlayer == null ){
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
            // add message prompt user to chose player
            TextView msg = (TextView) findViewById(R.id.select_player_msg);
            msg.setVisibility(View.VISIBLE);
        } else {
            Intent intent = new Intent(this, DifficultyLevelActivity.class);
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

    public void addPlayer(String nick) {
        try {
            SQLiteOpenHelper playerDatabaseHelper = new PlayerDatabaseHelper(this);
            db = playerDatabaseHelper.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("NICK", nick);
            db.insert("PLAYERS", null, values);
            db.close();
            StartActivity.players.add(new Player(nick));
            Toast toast  = Toast.makeText(activityRef, "Added new player " + nick , Toast.LENGTH_SHORT);
            toast.show();
        } catch (SQLiteException e) {
            Toast toast  = Toast.makeText(activityRef, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public static void updatePlayerScore(Player player, int newScore) {
        ContentValues playerValues = new ContentValues();
        playerValues.put("SCORE", newScore);

        db.update("PLAYERS", playerValues,"_id = ?",
                new String[] { Integer.toString(player.getId()) });

    }

    public Player getLastPlayer() {
        return lastPlayer;
    }

    public static void setLastPlayer(Player lastPlayer) {
        StartActivity.lastPlayer = lastPlayer;
    }

    public static List<String> getDictionary() {
        return dictionary;
    }

    public static void setDictionary(List<String> dictionary) {
        StartActivity.dictionary = dictionary;
    }

    public static StartActivity getActvityRef() {return activityRef;}
}

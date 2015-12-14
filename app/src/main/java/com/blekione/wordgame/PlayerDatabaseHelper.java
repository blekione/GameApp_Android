package com.blekione.wordgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by blekione on 13/12/15.
 */
public class PlayerDatabaseHelper  extends SQLiteOpenHelper{

    private static final String DB_NAME = "word_game";
    private  static final int DB_VERSION = 1;


    public PlayerDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE PLAYERS ("
                        + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "NICK TEXT, "
                        + "SCORE INTEGER);"
        );
      insertPlayer(db, "defaultPlayer");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertPlayer(SQLiteDatabase db, String nick) {
        ContentValues playerValues = new ContentValues();
        playerValues.put("NICK", nick);
        playerValues.put("SCORE", 0);
        db.insert("PLAYERS",null,playerValues);
    }
}

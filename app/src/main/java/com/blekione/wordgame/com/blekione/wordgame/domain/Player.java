package com.blekione.wordgame.com.blekione.wordgame.domain;

/**
 * Created by blekione on 10/12/15.
 */
public class Player {
    private static int idCounter = 0;
    private String nick;
    private int score;
    private int id;


    public Player(String nick) {
        this.score = 0;
        this.nick = nick;
        this.id = idCounter++;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public String getNick() {
        return nick;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }
}

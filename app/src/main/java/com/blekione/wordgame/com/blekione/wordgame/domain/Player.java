package com.blekione.wordgame.com.blekione.wordgame.domain;

/**
 * Created by blekione on 10/12/15.
 */
public class Player {
    static int idCOunter = 0;
    String nick;
    int score;
    int id;


    public Player(String nick) {
        this.score = 0;
        this.nick = nick;
        this.id = idCOunter++;
    }

    public static int getIdCOunter() {
        return idCOunter;
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

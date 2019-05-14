package com.example.flori.android_multi_game.manager;

import com.example.flori.android_multi_game.model.Player;

public class PlayerManager {

    private static final PlayerManager instance = new PlayerManager();

    private Player player;
    private int score;
    private String name;
    private String firstname;


    public static PlayerManager getInstance(){
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}

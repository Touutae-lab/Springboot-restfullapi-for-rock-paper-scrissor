package com.oopfinal.restfulapi.userdata;

import java.util.HashMap;


public class SessionData {
    protected String winner;
    protected String player1userName;
    protected String player2userName;
    protected String player1ID;
    protected String player2ID;
    protected String session;
    //round: ID: choice
    protected HashMap<Integer, HashMap<String, Integer>> round = new HashMap<Integer, HashMap<String, Integer>>();
    protected Integer current;
    
    public HashMap<String, Integer> createHash(Integer choose, String userId) {
        HashMap<String, Integer> hashTemp = new HashMap<>();
        hashTemp.put(userId, choose);
        return hashTemp;
    }
    public void putHash(String userId, Integer choose) {
        
    }
    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getPlayer1ID() {
        return player1ID;
    }

    public void setPlayer1ID(String player1ID) {
        this.player1ID = player1ID;
    }

    public String getPlayer2ID() {
        return player2ID;
    }

    public void setPlayer2ID(String player2ID) {
        this.player2ID = player2ID;
    }

    public HashMap<Integer, HashMap<String, Integer>> getRound() {
        return round;
    }

    public void setRound(Integer round , HashMap<String, Integer> Arr) {
        this.round.put(round, Arr);
    }
    public void setCurrent(Integer string) {
        this.current = string;
    }

    public Integer getCurrent() {
        return current;
    }

    public String getPlayer1userName() {
        return player1userName;
    }

    public void setPlayer1userName(String player1userName) {
        this.player1userName = player1userName;
    }

    public String getPlayer2userName() {
        return player2userName;
    }

    public void setPlayer2userName(String player2userName) {
        this.player2userName = player2userName;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "SessionData{" +
                "winner='" + winner + '\'' +
                ", player1ID='" + player1ID + '\'' +
                ", player2ID='" + player2ID + '\'' +
                ", round=" + round +
                '}';
    }
}
package com.oopfinal.restfulapi.userdata;

import java.util.HashMap;


public class SessionData {
    protected String winner;
    protected String player1ID;
    protected String player2ID;
    protected HashMap<String, HashMap<String, Integer>> round = new HashMap<String, HashMap<String, Integer>>();
    protected String current;
    
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

    public HashMap<String, HashMap<String, Integer>> getRound() {
        return round;
    }

    public void setRound(String round , HashMap<String, Integer> Arr) {
        this.round.put(round, Arr);
    }
    public void setCurrent(String round) {
        this.current = round;
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
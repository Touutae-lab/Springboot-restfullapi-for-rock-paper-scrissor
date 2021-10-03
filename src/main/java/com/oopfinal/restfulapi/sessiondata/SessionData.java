package com.oopfinal.restfulapi.sessiondata;

import java.util.HashMap;

public class SessionData {
    private boolean status = true;
    private String winner = "";
    private PlayerData Player1;
    private PlayerData Player2;
    private String session;
    //round: ID: choice
    private HashMap<Integer, HashMap<String, Integer>> round = new HashMap<Integer, HashMap<String, Integer>>();
    private Integer current;
    
    public HashMap<String, Integer> createHash(Integer choose, String userId) {
        HashMap<String, Integer> hashTemp = new HashMap<>();
        hashTemp.put(userId, choose);
        return hashTemp;
    }
    public void setChoice(String Id, Integer round, Integer choose) {
        HashMap<String, Integer> tmpDas = this.round.get(round);
        tmpDas.put(Id, choose);
    }

    public PlayerData getPlayer1() {
        return Player1;
    }

    public void setPlayer1(String id, String username, String score) {
        Player1 = new PlayerData(id, username, score);
    }

    public PlayerData getPlayer2() {
        return Player2;
    }

    public void setPlayer2(String id, String username, String score) {
        Player2 = new PlayerData(id, username, score);
    }

    public void putHash(String userId, Integer choose) {
        
    }
    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
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


    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }


    public void setRound(HashMap<Integer, HashMap<String, Integer>> round) {
        this.round = round;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void resetRound() {
        round = null;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "SessionData{" +
                "winner='" + winner + '\'' +
                ", Player1=" + Player1 +
                ", Player2=" + Player2 +
                ", session='" + session + '\'' +
                ", round=" + round +
                ", current=" + current +
                '}';
    }

    public void setPlayer2Null() {
        this.Player2 = null;
    }
}

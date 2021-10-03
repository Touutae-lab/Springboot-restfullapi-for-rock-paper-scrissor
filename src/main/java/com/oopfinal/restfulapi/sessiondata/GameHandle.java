package com.oopfinal.restfulapi.sessiondata;

import com.oopfinal.restfulapi.requesthandle.Reminder;
import com.oopfinal.restfulapi.userdata.UserData;

import java.util.ArrayList;
import java.util.HashMap;



public class GameHandle {
    public HashMap<Integer, HashMap<String, Integer>> generateData(String player1, String player2) {
        HashMap<Integer, HashMap<String, Integer>> result = new HashMap<>();
        for (int i=1;i<=5;i++) {
            HashMap<String, Integer> tmp = new HashMap<>();
            tmp.put(player1, 0);
            tmp.put(player2, 0);
            result.put(i, tmp);
        }
        return result;
    }
    public void checkWin(SessionData SesData, Integer CurrentRound, UserData Player1, UserData Player2) {
        String winner = SesData.getWinner();

        HashMap<String, Integer> RoundData = SesData.getRound().get(CurrentRound);
        PlayerData player1 = SesData.getPlayer1();
        PlayerData player2 = SesData.getPlayer2();
        
        Integer Player1notchoose = RoundData.get(player1.getId());
        Integer Player2notchoose = RoundData.get(player2.getId());
        if ( Player1notchoose == 0 && Player2notchoose != 0) {
            Reminder reminder = new Reminder(SesData, 1, Player1, Player2);
        }
        else if (Player2notchoose == 0 && Player1notchoose != 0) {
            Reminder reminder = new Reminder(SesData, 2, Player1, Player2);
        }
    }

    public boolean checkCondition(HashMap<String, Integer> round) {
        ArrayList<String> playerList = new ArrayList<>();
        for (String i : round.keySet()) {
            playerList.add(i);
        }
        if (round.get(playerList.get(0)) == 4) {
            return true;
        }
        else {
            return  false;
        }

    }
}

package com.oopfinal.restfulapi.userdata;

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
    public void checkWin(SessionData SesData, Integer CurrentRound) {
        String winner = SesData.getWinner();

        HashMap<String, Integer> Data = SesData.getRound().get(CurrentRound);
        HashMap<String, String> player1 = SesData.getPlayer1();
        HashMap<String, String> player2 = SesData.getPlayer2();
        
        Integer Con1 = Data.get(player1.get("Id"));
        Integer Con2 = Data.get(player2.get("Id"));
// SAD
        if ( Con1 == 4 && winner.equals("")) {
            SesData.setWinner(player2.get("Id"));
        }
        else if ( Con2 == 4 && winner.equals("")) {
            SesData.setWinner(player1.get("Id"));
        }
        else if ( Con1 == 0 && Con2 != 0) {
            Reminder reminder = new Reminder(SesData, 2);
        }
        else if (Con2 == 0 && Con1 != 0) {
            Reminder reminder = new Reminder(SesData, 1);
        }
        else if (Con1 == Con2) {
            
        }
        else if (Con1 == 1 && Con2 == 2) {
            String value = player1.get("score");
            Integer i = Integer.parseInt(value);
            value = Integer.toString(i);
            player1.put("score", value);
        }
        else if (Con1 == 1 && Con2 == 3) {
            String value = player2.get("score");
            Integer i = Integer.parseInt(value);
            value = Integer.toString(i);
            player2.put("score", value);
        }
        else if ( Con1 == 2 && Con2 == 1) {
            String value = player2.get("score");
            Integer i = Integer.parseInt(value);
            value = Integer.toString(i);
            player2.put("score", value);
        }
        else if ( Con1 == 2 && Con2 == 3) {
            String value = player1.get("score");
            Integer i = Integer.parseInt(value);
            value = Integer.toString(i);
            player1.put("score", value);
        }
        else if ( Con1 == 3 && Con2 == 1) {
            String value = player2.get("score");
            Integer i = Integer.parseInt(value);
            value = Integer.toString(i);
            player2.put("score", value);
        }
        else if ( Con1 == 3 && Con2 == 2) {
            String value = player1.get("score");
            Integer i = Integer.parseInt(value);
            value = Integer.toString(i);
            player1.put("score", value);
        }
        if (CurrentRound==5) {
            Integer p1 = Integer.parseInt(player1.get("score"));
            Integer p2 = Integer.parseInt(player2.get("score"));
            if (p1 == p2) {
                SesData.setWinner("Tie");
            }
            else if (p1 > p2) {
                SesData.setWinner(player1.get("Id"));
            }
            else {
                SesData.setWinner(player2.get("Id"));
            }
        }
    }
}

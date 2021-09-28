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
    public void checkWin(HashMap<String, Integer> Data, HashMap<String, String> player1, HashMap<String, String> player2) {
         if (Data.get(player1.get("Id")) == 0), Data.get {

         }
    }
    
}

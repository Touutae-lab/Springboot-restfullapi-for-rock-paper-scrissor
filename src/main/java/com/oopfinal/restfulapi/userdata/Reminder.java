package com.oopfinal.restfulapi.userdata;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple demo that uses java.util.Timer to schedule a task 
 * to execute once 5 seconds have passed.
 */

public class Reminder {
    Timer timer;

    public Reminder(SessionData data, Integer state) {
        timer = new Timer();
        RemindTask remind = new RemindTask();
        remind.setCurrent(data.getCurrent());
        if (state == 1) {
            remind.setId(data.getPlayer1().get("Id"));
        }
        if (state == 2) {
            remind.setId(data.getPlayer2().get("Id"));
        }
        remind.setRound(data.getRound());
        timer.schedule(remind, 20000);
	}

    class RemindTask extends TimerTask {
        HashMap<Integer, HashMap<String, Integer>> round;
        Integer current;
        String Id;
        public HashMap<Integer, HashMap<String, Integer>> getRound() {
            return round;
        }

        public void setRound(HashMap<Integer, HashMap<String, Integer>> round) {
            this.round = round;
        }

        public Integer getCurrent() {
            return current;
        }

        public void setCurrent(Integer current) {
            this.current = current;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public void run() {
            this.round.get(current).put(Id, 4);
            timer.cancel(); //Terminate the timer thread
        }

    }
}
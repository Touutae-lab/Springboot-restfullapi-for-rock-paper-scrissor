package com.oopfinal.restfulapi.requesthandle;
import com.oopfinal.restfulapi.sessiondata.SessionData;
import com.oopfinal.restfulapi.userdata.UserData;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple demo that uses java.util.Timer to schedule a task 
 * to execute once 5 seconds have passed.
 */

public class Reminder {
    Timer timer;

    public Reminder(SessionData data, Integer state, UserData player1, UserData player2) {
        timer = new Timer();
        RemindTask remind = new RemindTask();
        remind.setCurrent(data.getCurrent());
        if (state == 1) {
            remind.setId(data.getPlayer1().getId());
            remind.setPlayer(player1);
        }
        if (state == 2) {
            remind.setId(data.getPlayer2().getId());
            remind.setPlayer(player2);
        }
        remind.setSession(data);
        timer.schedule(remind, 20000);
	}

    class RemindTask extends TimerTask {
        UserData Player;
        SessionData Session;
        Integer current;
        String Id;

        public SessionData getSession() {
            return Session;
        }

        public void setSession(SessionData session) {
            Session = session;
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

        public UserData getPlayer() {
            return Player;
        }

        public void setPlayer(UserData player) {
            Player = player;
        }

        public void run() {
            if (Session.getRound() == null) {
                timer.cancel();
            }
            else{
                Player.setChallenge(false);
                this.Session.getRound().get(current).put(Id, 4);
                timer.cancel(); //Terminate the timer thread
            }

        }
    }
}
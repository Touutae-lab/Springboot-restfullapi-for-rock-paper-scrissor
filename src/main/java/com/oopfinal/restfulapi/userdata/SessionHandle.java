package com.oopfinal.restfulapi.userdata;

public class SessionHandle extends SessionData{
    public boolean checkIfYourself(SessionData session, String id) {
        return session.getPlayer1().get("Id").equals(id);
    }

    public boolean checkRoom(SessionData session, String id) {
        return session.getPlayer2() == null;
    }
    // SessionHandle(SessionData session, String tmRound) {
        
    // }
}

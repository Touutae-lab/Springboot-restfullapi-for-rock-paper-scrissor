package com.oopfinal.restfulapi.sessiondata;

public class SessionHandle extends SessionData{
    public boolean checkIfYourself(SessionData session, String id) {
        return session.getPlayer1().getId().equals(id);
    }

    public boolean checkRoom(SessionData session, String id) {
        return session.getPlayer2() == null;
    }
    // SessionHandle(SessionData session, String tmRound) {
        
    // }
}

package com.oopfinal.restfulapi.userdata;

public class SessionHandle extends SessionData{
    public boolean checkIfYourself(SessionData session, String id) {
        return session.getPlayer1ID().equals(id);
    }

    public boolean checkRoom(SessionData session, String id) {
        return session.getPlayer2ID().equals("");
    }
    // SessionHandle(SessionData session, String tmRound) {
        
    // }
}

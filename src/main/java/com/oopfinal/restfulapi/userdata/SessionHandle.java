package com.oopfinal.restfulapi.userdata;

public class SessionHandle extends SessionData{
    public boolean checkRoom(SessionData session, String id) {
        String check = session.getPlayer2ID();
        if (check.equals("")) {
            session.setPlayer2ID(id);
            return true;
        }
        else {
            return false;
        }
    }
    // SessionHandle(SessionData session, String tmRound) {
        
    // }
}

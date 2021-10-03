package com.oopfinal.restfulapi.requesthandle;

import java.util.HashMap;

import com.oopfinal.restfulapi.sessiondata.GameHandle;
import com.oopfinal.restfulapi.sessiondata.SessionData;
import com.oopfinal.restfulapi.sessiondata.SessionHandle;
import com.oopfinal.restfulapi.userdata.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
public class Requesthandle {
    private HashMap<String, UserData> Data= new HashMap<String, UserData>();
    private HashMap<String, SessionData> SessionControl = new HashMap<String, SessionData>();

//TESTING PART
    @GetMapping(path="/testSession")
    public @ResponseBody HashMap<String, SessionData> getTest(){
        return SessionControl;
    }
    //----------------------------------Finished------------------------------
    @PostMapping(path="/register")
    public @ResponseBody List<UserData> addNewUser (@RequestParam String username ) {
        LoggingController.log(
                String.format("\nPOST To /register: username = %s", username));

        UserData dataController = new UserData(username);
        IdGenerator password = new IdGenerator();
        String userId = password.randomString(16);
        String userSession = password.randomString(8);
        dataController.setId(userId);
        dataController.setSession(userSession);
        Data.put(userId, dataController);

        SessionData sessionTemp = new SessionData();
        sessionTemp.setPlayer1(userId, username, "0");
        sessionTemp.setPlayer2Null();
        SessionControl.put(userSession, sessionTemp);
        return List.of(dataController);
    }

    //----------------------------------Finished------------------------------
    @GetMapping(path="/leaderboard")
    public ArrayList<UserData> getUserData() {
        LoggingController.log(String.format("\nGET To /leaderboard"));
        ArrayList<UserData> playerList = new ArrayList<>();
        for (UserData i : Data.values()) {
            playerList.add(i);
        }
        return playerList;
    }
    //----------------------------------Finished------------------------------
    @PostMapping(path="/playerstatus")
    public @ResponseBody
    UserData getStatus(@RequestParam String Id) {
        LoggingController.log(
                String.format("\nPOST To /playerstatus: Id = %s", Id));
        UserData response = Data.get(Id);
        return response;
    }

    //----------------------------------Finished------------------------------
    @PostMapping(path="/join")
    public @ResponseBody
    MinorRequest joinSession(@RequestParam String Id, @RequestParam String Session/*, @RequestBody String Round*/) {
        LoggingController.log(
                String.format("\nPOST To /join: Id = %s | Session = %s",
                        Id, Session));
        boolean isKeyExist = SessionControl.containsKey(Session);
        MinorRequest data = new MinorRequest();
        UserData myData = Data.get(Id);
        if (isKeyExist) {
            SessionData sessioncontrol =  SessionControl.get(Session);
            SessionHandle hander = new SessionHandle();

            if (hander.checkIfYourself(sessioncontrol, Id)) {
                data.setStatus("yourself");
                return data;
            }
            else if (hander.checkRoom(sessioncontrol, Id) ){
                
                GameHandle handler = new GameHandle();
                String opponentId = sessioncontrol.getPlayer1().getId();
                
                sessioncontrol.setStatus(true);
                sessioncontrol.setRound(handler.generateData(opponentId, Id));
                UserData opponentData = Data.get(opponentId);
                opponentData.setChallenge(true);
                sessioncontrol.setCurrent(1);
                sessioncontrol.setPlayer2(Id, myData.getUsername(), "0"); //Must be "Id": {Id}//Must be "UserName": {myData.getUsername}
                data.setId(opponentData.getId());
                data.setUsername(opponentData.getUsername());
                data.setStatus("Joined");
                return data;
            }
            else {
                data.setStatus("Session is full");
                return data;
            }
        }
        else {
            data.setStatus("Room is not found");
            return data;
        }
    }
    @PostMapping(path="/sessionstatus")
    public @ResponseBody SessionData SessionStatus(@RequestParam String sessionid) {
        //ServerLog
        LoggingController.log(
                String.format("\nPOST To /sessionstatus: sessionId = %s",
                        sessionid));


        GameHandle handler = new GameHandle();
        SessionData sessiontmp = SessionControl.get(sessionid);
        Integer current = sessiontmp.getCurrent();
        if (sessiontmp.getPlayer2() != null) {
            handler.checkWin(sessiontmp, sessiontmp.getCurrent(), Data.get(sessiontmp.getPlayer1().getId()), Data.get(sessiontmp.getPlayer2().getId()));
        }
        boolean check = handler.checkCondition(sessiontmp.getRound().get(current));
        if (!sessiontmp.getWinner().equals("") || check) {
            sessiontmp.setStatus(false);
            sessiontmp.setPlayer2Null();
            sessiontmp.resetRound();
            sessiontmp.setCurrent(1);
            sessiontmp.setWinner("");
            return sessiontmp;
        }
        LoggingController.log("response with " + sessiontmp.toString());
        return sessiontmp;
    }
    @PostMapping(path="/winner")
    public @ResponseBody String winnerHandle(@RequestParam String State, @RequestParam String Id) {
            UserData data = Data.get(Id);
            data.setPlayed(data.getPlayed()+1);
            if (State.equals("win")) {
                data.setScore(data.getScore()+1);
            }
        return "done";
        

        } 
    @PostMapping(path="/forcejoin")
    public @ResponseBody
    MinorRequest forceJoin(@RequestParam String session) {
        LoggingController.log("session = " + session);

        SessionData sestmp = SessionControl.get(session);
        MinorRequest datatmp = new MinorRequest();

        if (sestmp.getPlayer2() == null) {
            Data.get(sestmp.getPlayer1().getId()).setChallenge(false);
            datatmp.setStatus("out of update");
            return datatmp;
        }

        datatmp.setStatus("Joined");
        String id = sestmp.getPlayer2().getId();
        String username = sestmp.getPlayer2().getUsername();
        datatmp.setId(id);
        datatmp.setUsername(username);
        LoggingController.log("datatmp = " + datatmp.toString());
        return datatmp;
    }

    @PostMapping(path="/choose")
    public @ResponseBody String selectingChoice(@RequestParam String session, @RequestParam String Id, @RequestParam Integer round, @RequestParam Integer choose ) {
        //ServerLog
        LoggingController.log(
                String.format("\nPOST To /choose: Id = %s, round = %s, choose = %d",
                        Id, round, choose.intValue()));
        
        SessionData sessiontmp = SessionControl.get(session);
        if (sessiontmp.getCurrent() < round) {
            sessiontmp.setCurrent(round);
        }
        sessiontmp.setChoice(Id, round, choose);  
        return "done";
    }
}

package com.oopfinal.restfulapi.userdata;

import java.util.HashMap;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;



@RestController
public class userDataController {
    private HashMap<String, userData> Data= new HashMap<String, userData>();
    private HashMap<String, SessionData> SessionControl = new HashMap<String, SessionData>();

    @PostMapping(path="/register")
    public @ResponseBody List<userData> addNewUser (@RequestParam String username ) {
        LoggingController.log(
                String.format("\nPOST To /register: username = %s", username));

        userData dataController = new userData(username);
        RandomString password = new RandomString();
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
    @GetMapping(path="/leaderboard")
    public ArrayList<userData> getuserData() {
        LoggingController.log(String.format("\nGET To /leaderboard"));
        ArrayList<userData> playerList = new ArrayList<>();
        for (userData i : Data.values()) {
            playerList.add(i);
        }
        return playerList;
    }
    @PostMapping(path="/playerstatus")
    public @ResponseBody userData getStatus(@RequestParam String Id) {
        LoggingController.log(
                String.format("\nPOST To /playerstatus: Id = %s", Id));
        userData response = Data.get(Id);
        return response;
    }

    @PostMapping(path="/join")
    public @ResponseBody MiniData joinSession(@RequestParam String Id, @RequestParam String Session/*, @RequestBody String Round*/) {
        LoggingController.log(
                String.format("\nPOST To /join: Id = %s | Session = %s",
                        Id, Session));
        boolean isKeyExist = SessionControl.containsKey(Session);
        MiniData data = new MiniData();
        userData myData = Data.get(Id);
        if (isKeyExist) {
            SessionData sessioncontrol =  SessionControl.get(Session);
            SessionHandle hander = new SessionHandle();

            if (hander.checkIfYourself(sessioncontrol, Id)) {
                data.setStatus("yourself");
                return data;
            }
            else if (hander.checkRoom(sessioncontrol, Id) ){
                
                GameHandle handler = new GameHandle();
                String opponentId = sessioncontrol.getPlayer1().get("Id");


                sessioncontrol.setRound(handler.generateData(opponentId, Id));
                userData opponentData = Data.get(opponentId);
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
    public @ResponseBody SessionData SessionStat(@RequestParam String sessionid) {
        LoggingController.log(
                String.format("\nPOST To /sessionstatus: sessionId = %s",
                        sessionid));
        SessionData sessiontmp = SessionControl.get(sessionid);
        return sessiontmp;
    }
    
    @PostMapping(path="/forcejoin")
    public @ResponseBody MiniData forcejoin(@RequestParam String session) {
        MiniData datatmp = new MiniData();
        datatmp.setStatus("Joined");
        SessionData sestmp = SessionControl.get(session);
        String id = sestmp.getPlayer2().get("Id");
        String username = sestmp.getPlayer2().get("username");
        datatmp.setId(id);
        datatmp.setUsername(username);
        return datatmp;
    }

    @PostMapping(path="/choose")
    public @ResponseBody String datadog(@RequestParam String Id, @RequestParam Integer round, @RequestParam Integer choose ) {
        LoggingController.log(
                String.format("\nPOST To /choose: Id = %s, round = %s, choose = %d",
                        Id, round, choose.intValue()));
        
        SessionData sessiontmp = SessionControl.get(Id);
        GameHandle handler = new GameHandle();
        
        HashMap<Integer, HashMap<String, Integer>> sessionRound = sessiontmp.getRound();
        sessionRound.get(round);
        return "done";
    }
}

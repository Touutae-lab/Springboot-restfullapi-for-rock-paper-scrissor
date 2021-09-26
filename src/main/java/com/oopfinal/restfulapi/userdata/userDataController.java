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
        userData dataController = new userData(username);
        RandomString password = new RandomString();
        String userId = password.randomString(16);
        String userSession = password.randomString(8);
        dataController.setId(userId);
        dataController.setSession(userSession);
        Data.put(userId, dataController);

        SessionData sessionTemp = new SessionData();
        sessionTemp.setPlayer1ID(userId);
        SessionControl.put(userSession, sessionTemp);

        return List.of(dataController);
    }
    @GetMapping(path="/leaderboard")
    public ArrayList<userData> getuserData() {
        ArrayList<userData> playerList = new ArrayList<>();
        for (userData i : Data.values()) {
            playerList.add(i);
        }
        return playerList;
    }
    @PostMapping(path="/playerstatus")
    public @ResponseBody userData getStatus(@RequestBody String Id) {
        userData response = Data.get(Id);
        return response;
    }

    @PostMapping(path="/join")
    public @ResponseBody String joinSession(@RequestBody String Id, @RequestBody String Session, @RequestBody String Round) {
        userData datacontroller = Data.get(Id);
        boolean isKeyExist = SessionControl.containsKey(Session);
        if (isKeyExist) {
            SessionData sessioncontrol =  SessionControl.get(Session);
            SessionHandle hander = new SessionHandle();
            if (hander.checkRoom(sessioncontrol, Id) ){
                datacontroller.setChallenge("yes");
                sessioncontrol.setCurrent("1");
                return "Joined";
            }
            else {
                return "Session is full";
            }

        }
        else {
            return "Room is not found";
        }
    }
    @PostMapping(path="/sessionstatus")
    public @ResponseBody SessionData SessionStat(@RequestBody String sessionid) {
        SessionData sessiontmp = SessionControl.get(sessionid);
        return sessiontmp;
    }

    @PostMapping(path="/choose")
    public @ResponseBody String datadog(@RequestBody String Id, @RequestBody String round, @RequestBody Integer choose ) {
        SessionData sessiontmp = SessionControl.get(Id);
        sessiontmp.setRound(round, sessiontmp.createHash(choose,Id));
        HashMap<String, HashMap<String, Integer>> nxtround = sessiontmp.getRound();
        if (checkRoom(round, sessiontmp)) { 
            int i = Integer.parseInt(round) + 1;
            round = Integer.toString(i);
            sessiontmp.setCurrent(round);
        }
        return "done";
        
    }
    public boolean checkRoom(String currentRound,SessionData checker) {
        HashMap<String, HashMap<String, Integer>> HashCheck = checker.getRound();
        if (HashCheck.get(currentRound).size() == 2){
            return true;
        }   
        return false;
        
    }
}

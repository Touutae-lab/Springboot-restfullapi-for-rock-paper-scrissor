package com.oopfinal.restfulapi.userdata;

import java.util.HashMap;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.None;
@RestController
public class userDataController {
    HashMap<String, userData> Data= new HashMap<String, userData>();
    @PostMapping(path="/register")
    public @ResponseBody List<userData> addNewUser (@RequestParam String username ) {
        userData dataController = new userData(username);
        RandomString password = new RandomString();
        String userId = password.randomString(16);
        dataController.setId(userId);
        Data.put(userId, dataController);
        return List.of(dataController);
    }

    @GetMapping(path="{id}/requestfight/{opponent}")
    public @ResponseBody String requestFight(@PathVariable String id, @PathVariable String opponent) {
        userData tempKey = Data.get(opponent);
        HashMap<String, String> tempData = new HashMap<>();
        tempData.put("target", opponent);
        tempData.put("choose", null);
        tempData.put("status", null);
        tempData.put("roomScore", null);
        HashMap<String, HashMap<String, String>> requestType = new HashMap<>();
        requestType.put(id, tempData);
        tempKey.setRequest(requestType);
        Data.put(opponent, tempKey);
        return "Success";
    }
    @GetMapping(path="{Id}/createsession")
    public String getSession(@PathVariable String Id) {
        RandomString sessionGen = new RandomString();
        String sessionId =  sessionGen.randomString(8);
        return "Joined";
    }

    @GetMapping(path="{id}/{sessionId}")
    public String getUserId(@PathVariable String id, @PathVariable String sessionId) {
        return id+sessionId;
    }
    @GetMapping(path="{id}/playerstatus")
    public ArrayList<userData> getuserData() {
        ArrayList<userData> playerList = new ArrayList<>();
        for (userData i : Data.values()) {
            playerList.add(i);
        }
        return playerList;
        
    }
    @GetMapping(path="status/{id}")
    public void check() {

    }
}
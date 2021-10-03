package com.oopfinal.restfulapi.sessiondata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerData {
    private String id;
    private String username;
    private String score;

    public PlayerData(String id, String username, String score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }
    @JsonProperty("username")
    public String getUsername() {
        return username;
    }
    @JsonProperty("score")
    public String getScore() {
        return score;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}

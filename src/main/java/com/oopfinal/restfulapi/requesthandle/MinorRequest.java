package com.oopfinal.restfulapi.requesthandle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MinorRequest {
    private String status;
    private String id;
    private String username;

    public MinorRequest() {

    }

    public MinorRequest(String status, String id, String username) {
        this.status = status;
        this.id = id;
        this.username = username;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @java.lang.Override
    public java.lang.String toString() {
        return "MiniData{" +
                "status='" + status + '\'' +
                ", id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

package com.oopfinal.restfulapi.userdata;

public class MiniData {
    private String status;
    private String id;
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

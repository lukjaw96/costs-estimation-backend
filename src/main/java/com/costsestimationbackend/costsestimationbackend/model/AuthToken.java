package com.costsestimationbackend.costsestimationbackend.model;

public class AuthToken {

    private String token;
    private String username;
    private Integer userId;
    private String role;

    public AuthToken() {

    }

    public AuthToken(String token) {
        this.token = token;
    }

    public AuthToken(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public AuthToken(String token, String username, Integer userId) {
        this.token = token;
        this.username = username;
        this.userId = userId;
    }

    public AuthToken(String token, String username, Integer userId, String role) {
        this.token = token;
        this.username = username;
        this.userId = userId;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

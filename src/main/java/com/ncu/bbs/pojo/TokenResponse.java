package com.ncu.bbs.pojo;

public class TokenResponse {
    private final User user;
    private final String token;

    public TokenResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}

package com.app.auth;

public class AuthResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public AuthResponse withToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}

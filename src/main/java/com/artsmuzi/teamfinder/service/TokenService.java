package com.artsmuzi.teamfinder.service;

public interface TokenService {
    String generateToken(String username);
    String getUsernameFromJWT(String token);
}

package com.artsmuzi.teamfinder.service.implementation;

import com.artsmuzi.teamfinder.service.KeyService;
import com.artsmuzi.teamfinder.service.TokenService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class TokenServiceImpl implements TokenService {
    private final KeyService keyService;

    @Autowired
    TokenServiceImpl(KeyService keyService) {
        this.keyService = keyService;
    }
    @Override
    public String generateToken(String username) {
        JwtBuilder jwt = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Calendar.getInstance().getTime())
                .signWith( SignatureAlgorithm.HS256, keyService.getSecretKey() );

        return jwt.compact();
    }

    @Override
    public String getUsernameFromJWT(String token) {
        String username = Jwts.parser()
                        .setSigningKey(keyService.getSecretKey()).parseClaimsJws(token)
                        .getBody().getSubject();

        return username;
    }


}

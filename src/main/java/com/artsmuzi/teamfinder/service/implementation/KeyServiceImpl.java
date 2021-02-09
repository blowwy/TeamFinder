package com.artsmuzi.teamfinder.service.implementation;

import com.artsmuzi.teamfinder.service.KeyService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Service
public class KeyServiceImpl implements KeyService {

    private SecretKey key;

    @PostConstruct
    private void initSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            key = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(); //TODO Questionable
        }
    }

    @Override
    public SecretKey getSecretKey() {
        return key;
    }
}

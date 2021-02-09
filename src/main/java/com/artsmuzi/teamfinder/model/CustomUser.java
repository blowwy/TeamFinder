package com.artsmuzi.teamfinder.model;

public class CustomUser extends org.springframework.security.core.userdetails.User {
    public CustomUser(AppUser user) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}

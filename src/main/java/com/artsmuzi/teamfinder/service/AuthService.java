package com.artsmuzi.teamfinder.service;

import com.artsmuzi.teamfinder.dto.SignupRequest;
import com.artsmuzi.teamfinder.model.AppUser;

public interface AuthService {
    void signup(SignupRequest signupRequest);
}

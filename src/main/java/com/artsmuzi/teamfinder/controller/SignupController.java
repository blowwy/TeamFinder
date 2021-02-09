package com.artsmuzi.teamfinder.controller;

import com.artsmuzi.teamfinder.dto.SignupRequest;
import com.artsmuzi.teamfinder.model.AppUser;
import com.artsmuzi.teamfinder.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/signup")
    public void signup(@RequestBody SignupRequest signupRequest) {

        authService.signup(signupRequest);
    }
}

package com.artsmuzi.teamfinder.service.implementation;

import com.artsmuzi.teamfinder.dto.SignupRequest;
import com.artsmuzi.teamfinder.model.AppUser;
import com.artsmuzi.teamfinder.model.Player;
import com.artsmuzi.teamfinder.model.PlayerPersonalInfo;
import com.artsmuzi.teamfinder.model.PlayerPosition;
import com.artsmuzi.teamfinder.repository.PlayerRepository;
import com.artsmuzi.teamfinder.repository.UserRepository;
import com.artsmuzi.teamfinder.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PlayerRepository playerRepository;

    @Autowired
    AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.playerRepository = playerRepository;
    }

    @Override
    public void signup(SignupRequest signupRequest) {
        AppUser user = new AppUser();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setEmail(signupRequest.getEmail());

        Player player = new Player();
        PlayerPersonalInfo info = new PlayerPersonalInfo("","","","", PlayerPosition.ATTACKER);
        player.setInfo(info);
        player.setUser(user);

        userRepository.save(user);
        playerRepository.save(player);
    }
}

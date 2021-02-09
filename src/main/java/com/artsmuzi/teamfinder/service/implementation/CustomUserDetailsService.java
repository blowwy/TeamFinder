package com.artsmuzi.teamfinder.service.implementation;

import com.artsmuzi.teamfinder.model.CustomUser;
import com.artsmuzi.teamfinder.model.AppUser;
import com.artsmuzi.teamfinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow( () ->
                        new UsernameNotFoundException("Can't find user with name " + username));
        return new CustomUser(user);
    }
}

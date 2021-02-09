package com.artsmuzi.teamfinder;

import com.artsmuzi.teamfinder.model.*;
import com.artsmuzi.teamfinder.repository.FieldRepository;
import com.artsmuzi.teamfinder.repository.PlayerRepository;
import com.artsmuzi.teamfinder.repository.TeamRepository;
import com.artsmuzi.teamfinder.service.*;
import com.artsmuzi.teamfinder.service.KeyService;
import com.artsmuzi.teamfinder.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@SpringBootApplication
public class TeamfinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamfinderApplication.class, args);
	}

}

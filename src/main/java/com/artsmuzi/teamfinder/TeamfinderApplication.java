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

	/*@EventListener(ApplicationReadyEvent.class)
	private void test() {
		Player player = new Player();
		PlayerPersonalInfo info = new PlayerPersonalInfo("A","B","www","102", PlayerPosition.ATTACKER);
		player.setInfo(info);
		playerRepository.save(player);
		Team team = new Team();
		team.setName("aaa");
		teamRepository.save(team);
		Field field = new Field();
		Coordinates coordinates = new Coordinates();
		coordinates.setX(12.1);
		coordinates.setY(12.2);
		field.setCoordinates(coordinates);
		fieldRepository.save(field);

		teamService.addPlayerToTeam(team.getId(), player.getId());
		fieldService.addTeamToField(field.getId(), team);
		List<Team> fieldTeams = fieldService.getFieldTeams(field.getId());
		fieldTeams.forEach(this::getPlayers);

		//teamService.deletePlayerFromTeam(team.getId(),player);

		//fieldTeams.forEach(this::getPlayers);
		//teamService.getTeamPlayers(team.getId()).forEach(System.out::println);

		try {
			String t = tokenService.generateToken( "222");
			System.out.println( t );
		} catch (UsernameNotFoundException ex) {
			ex.printStackTrace();
		}



	}

	private void getPlayers(Team team) {
		System.out.println(teamService.getTeamPlayers(team.getId()));
	}*/

}

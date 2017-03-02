package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@Controller
class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
}

class Player {

	private String name;

	private String position;

	public Player(String name, String position) {
		this.name = name;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}

@XmlRootElement
class Team {
	private String name;

	private String location;

	private Set<Player> players;

	public Team(){
		super();
	}

	public Team(String name, String location, Set<Player> players) {
		this.name = name;
		this.location = location;
		this.players = players;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Player> getPlayers() {
		return players;

	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
}

@RestController
class JsonController {

	private Team team;

	@PostConstruct
	public void init() {
		Set<Player> players = new HashSet<>();
		players.add(new Player("Name 1", "Position 1"));
		players.add(new Player("Name 2", "Position 2"));
		players.add(new Player("Name 3", "Position 3"));

		team = new Team("Team1", "Xi'an", players);
	}

	@RequestMapping("/json")
	public Team json() {
		return team;
	}
}

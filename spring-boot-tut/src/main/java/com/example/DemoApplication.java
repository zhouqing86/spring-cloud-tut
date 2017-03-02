package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private TeamDao teamDao;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void init() {
		Set<Player> players = new HashSet<>();
		players.add(new Player("Name 1", "Position 1"));
		players.add(new Player("Name 2", "Position 2"));
		players.add(new Player("Name 3", "Position 3"));

		Team team = new Team("Team1", "Xi'an", players);

		teamDao.save(team);
	}

}

@Controller
class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
}

@Entity
class Player {

	@Id @GeneratedValue
	private Long id;

	private String name;

	private String position;

	public Player() {
		super();
	}

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
@Entity
class Team {

	@Id @GeneratedValue
	private  Long Id;

	private String name;

	private String location;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "teamId")
	private Set<Player> players;

	public Team(){
		super();
	}

	public Team(String name, String location, Set<Player> players) {
		this.name = name;
		this.location = location;
		this.players = players;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

@RestController
class JpaController {

	@Autowired
	private TeamDao teamDao;

	@RequestMapping("/team/{name}")
	public Team findTeamByName(@PathVariable("name") String name) {
		return teamDao.findByName(name);
	}
}

@Repository
interface TeamDao extends CrudRepository<Team, Long> {
	List<Team> findAll();

	Team findByName(String name);
}
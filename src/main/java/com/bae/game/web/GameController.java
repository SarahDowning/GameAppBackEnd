package com.bae.game.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.game.domain.Game;
import com.bae.game.service.GameService;

@RestController
public class GameController {

	private GameService service;

	@Autowired
	public GameController(GameService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Game> createGame(@RequestBody Game game) {
		Game created = this.service.createGame(game);
		ResponseEntity<Game> response = new ResponseEntity<Game>(created, HttpStatus.CREATED); // 201 status
		return response;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Game>> getAllGames() {
		return ResponseEntity.ok(this.service.getAllGames());
	}

	@GetMapping("/get/{id}")
	public Game getGame(@PathVariable Integer id) {
		return this.service.getGame(id);
	}

	@PutMapping("/replace/{id}")
	public ResponseEntity<Game> replaceGame(@PathVariable Integer id, @RequestBody Game newGame) {
		Game body = this.service.replaceGame(id, newGame);
		ResponseEntity<Game> response = new ResponseEntity<Game>(body, HttpStatus.ACCEPTED); // 202 status
		return response;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Game> deleteGame(@PathVariable Integer id) {
		ResponseEntity<Game> response = new ResponseEntity<Game>(HttpStatus.NO_CONTENT);
		this.service.deleteGame(id.intValue());
		return response;
	}

}
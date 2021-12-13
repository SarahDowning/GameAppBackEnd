package com.bae.game.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.game.domain.Game;
import com.bae.game.repo.GameRepo;

@Service
public class GameServiceDB implements GameService {

	private GameRepo repo;

	// Constructor Method
	@Autowired
	public GameServiceDB(GameRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Game createGame(Game game) {
		Game created = this.repo.save(game);
		return created;
	}

	@Override
	public List<Game> getAllGames() {
		return this.repo.findAll();
	}

	@Override
	public Game getGame(Integer id) {
		Optional<Game> found = this.repo.findById(id);
		return found.get();
	}

	@Override
	public Game replaceGame(Integer id, Game newGame) {
		Game existing = this.repo.findById(id).get();

		existing.setId(newGame.getId());
		existing.setGameTitle(newGame.getGameTitle());
		existing.setGenre(newGame.getGenre());
		existing.setPublisher(newGame.getPublisher());
		existing.setPlatform(newGame.getPlatform());
		existing.setReleaseYear(newGame.getReleaseYear());

		Game updated = this.repo.save(existing);
		return updated;
	}

	@Override
	public void deleteGame(Integer id) {
		this.repo.deleteById(id);

	}

}

package com.bae.game.service;

import java.util.List;

import com.bae.game.domain.Game;

public interface GameService {

	// Created
	Game createGame(Game game);

	// Get All
	List<Game> getAllGames();

	// Get By ID
	Game getGame(Integer id);

	// Replace by ID
	Game replaceGame(Integer id, Game newGame);

	// Delete by ID
	void deleteGame(Integer id);

}

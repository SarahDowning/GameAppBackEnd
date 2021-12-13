package com.bae.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.game.domain.Game;

public interface GameRepo extends JpaRepository<Game, Integer> {
	
}

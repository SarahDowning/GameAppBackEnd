package com.bae.game.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.game.domain.Game;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:game-schema.sql",
		"classpath:game-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class GameControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {

		Game testGame = new Game(1, "Final Fantasy I", "RPG", "Square Enix", "Steam", 2021);
		String testGameAsJSON = this.mapper.writeValueAsString(testGame);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testGameAsJSON);

		Game testGameCreated = new Game(1, "Final Fantasy I", "RPG", "Square Enix", "Steam", 2021);
		String testGameCreatedAsJSON = this.mapper.writeValueAsString(testGameCreated);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testGameCreatedAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testFindAll() throws Exception {
		List<Game> testGame = List.of(new Game(1, "Final Fantasy I", "RPG", "Square Enix", "Steam", 2021));
		String testGameAsJSON = this.mapper.writeValueAsString(testGame);
		RequestBuilder req = get("/getAll");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testGameAsJSON); // does the body match
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testFindById() throws Exception {
		String testGameAsJSON = this.mapper
				.writeValueAsString(new Game(1, "Final Fantasy I", "RPG", "Square Enix", "Steam", 2021));
		RequestBuilder req = get("/get/1");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testGameAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testReplaceGame() throws Exception {
		Game testGame = new Game(1, "Final Fantasy I", "RPG", "Square Enix", "Steam", 2021);
		String testGameAsJSON = this.mapper.writeValueAsString(testGame);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testGameAsJSON);

		Game testCreatedGame = new Game(1, "Final Fantasy I", "RPG", "Square Enix", "Steam", 2021);
		String testCreatedGameAsJSON = this.mapper.writeValueAsString(testCreatedGame);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testCreatedGameAsJSON);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDeleteGame() throws Exception {

		RequestBuilder req = delete("/delete/1").contentType(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatus = status().isNoContent();
		this.mvc.perform(req).andExpect(checkStatus);
	}

}

# Game List Application Project: Back-End
## Introduction
The purpose of this project was to create a functioning front-end CRUD application as a consolidation of learning over the last seven weeks of training. This repository contains the back-end technology and documentation utilised to create and support the creation of the application.

### Back-End Technologies
* **Database:** A relational database in MySQL to store persistent data.
* **Java SE:** Functional back-end created with exception handling of CRUD functionality.
* **Spring Boot:** Application framework and inversion-of-control container for Java project.
* **Integration Tests:** MockMVC used to test the fundamentals of the application.
* **Version Control:** GitHub for code integration.

## Architecture
The diagram below represents the architecture of this project.
* **Presentation Layer:** The front-end, what is seen visually and what the user interfaces with.
* **Business Layer:** The back-end, applies business rules to data sent and received from the user.
* **Data Layer:** The database, persists data by taking queries and returning data back.

![Architecture](https://github.com/SarahDowning/GameAppBackEnd/blob/38830bdcb873e62e77196af4dbee691feda0d101/Documentation/Architecture%20Diagram.png)

## Database
A database was created in MySQL to store persistant data, using spring to automatically populate the table with rows and specifics defined in Java.

![ERD](https://github.com/SarahDowning/GameAppBackEnd/blob/43e031e73b6aea4576b83a582b12dfa8822f6345/Documentation/Game%20Application%20ERD.png)

_Entity-relationship diagram showing the table created and each fields properties._

![SQL-Data](https://github.com/SarahDowning/GameAppBackEnd/blob/e4713b678d6ea212b864e071ef96940216a60710/Documentation/SQL-Result-Grid.png)
_SQL Results showing data that has been stored persistently from user input._

### Database Testing
In order to test that data was able to be stored, Postman and a H2 database were used to test each CRUD functionality end-point responded as expected. I was also able to check whether the exception handling status codes were correct.

![Postman](https://github.com/SarahDowning/GameAppBackEnd/blob/e4713b678d6ea212b864e071ef96940216a60710/Documentation/postman.png)
_Postman create function test, showing post request has worked, returning correct status code for created (201)._

## Back-End Functionality
The back-end functionality was created in the Eclipse IDE using Java alongside the Spring Boot Framework. Here, entities for the database tables were annotated to represent the field properties. The general package structure of the back-end is as follows:
* A domain class where entities were created as well as getters and setters and constructor methods.
* A controller was also created to allow external access to the spring application via HTTP requests and annotations such as @GetMapping and @PathVariable.
* A service interface where the CRUD functionality can be called from.
* A repository interface to provide a way of interact with the database and handles persistance. In this instance using JPARepository.

## Testing
_Test coverage: 76.9%_
  
MockMVC integration tests were used to perform mock HTTP requests to test the HTTP endpoint outcome as defined in the controller class. This alongside postman was an effective way to test some of the back-end functionality. The code below shows an example of a MockMVC test for the create functionality.

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

  
This test firstly mocks our controller class, then converts a game object to a game data access object (DAO), and finally converts the request to a JSON format.

## Version Control

This project uses [Git](https://git-scm.com///) for versioning to ensure code is backed-up and should other developers need to work on the project, branches can be made to avoid conflicts, and general changes can be tracked over time.

## Style Guide

To allow for consistency, the code style conventions that have been followed are:

**Brackets:** One True Brace STyle (1TBS)

**Comments:** Comments should only be used where a piece of code is not cognitively complex, and where possible, most code should be readable with appropriate variable and method names where possible. Camelcase should be used to define variables and methods and Pascalcase to define classes, test classes, interfaces.

For more information on style and best practice, please see the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

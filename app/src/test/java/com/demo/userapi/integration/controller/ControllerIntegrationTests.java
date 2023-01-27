package com.demo.userapi.integration.controller;

import com.demo.userapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTests {

    @MockBean
    UserRepository repository;

    @LocalServerPort
    private Integer appPort;

    @BeforeEach
    void setup() {
        port = appPort;
    }

    @Test
    void testGetAllMoviesControllerService() {
        // cole aqui o código
    }
}

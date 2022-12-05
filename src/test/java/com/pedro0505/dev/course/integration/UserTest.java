package com.pedro0505.dev.course.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedro0505.dev.course.entities.User;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTest {
    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    @Autowired
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/users");
    }

	@Test
	public void testPostUser() throws Exception {
		User user = new User(1L, "Pedro", "pedro@gmail.com", "8198763242", "password");
		this.mockMvc.perform(post(this.baseUrl).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user)))
		.andExpect(status().is(HttpStatus.CREATED.value()))
		.andExpect(jsonPath("$.name").value("Pedro"))
		.andExpect(jsonPath("$.email").value("pedro@gmail.com"))
		.andExpect(jsonPath("$.phone").value("8198763242"))
		.andExpect(jsonPath("$.password").value("password"));
	}
}

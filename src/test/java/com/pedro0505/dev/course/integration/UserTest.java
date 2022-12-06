package com.pedro0505.dev.course.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
	@Order(1)
	public void testPostUser() throws Exception {
		User user = new User(null, "Pedro", "pedro@gmail.com", "8198763242", "password");
		this.mockMvc
				.perform(post(this.baseUrl).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user)))
				.andExpect(status().is(HttpStatus.CREATED.value())).andExpect(jsonPath("$.name").value("Pedro"))
				.andExpect(jsonPath("$.email").value("pedro@gmail.com"))
				.andExpect(jsonPath("$.phone").value("8198763242")).andExpect(jsonPath("$.password").value("password"));
	}

	@Test
	@Order(2)
	public void testGeAlltUser() throws Exception {
		this.mockMvc.perform(get(this.baseUrl).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.OK.value())).andExpect(jsonPath("$[0].name").value("Maria Brown"))
				.andExpect(jsonPath("$[1].name").value("Alex Green")).andExpect(jsonPath("$.length()").value(3));
	}

	@Test
	@Order(3)
	public void testGetUserById() throws Exception {
		this.mockMvc.perform(get(this.baseUrl.concat("/1")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.OK.value())).andExpect(jsonPath("$.name").value("Maria Brown"))
				.andExpect(jsonPath("$.email").value("maria@gmail.com"))
				.andExpect(jsonPath("$.phone").value("988888888")).andExpect(jsonPath("$.password").value("123456"));
	}

	@Test
	@Order(4)
	public void testDeleteUser() throws Exception {
		this.mockMvc.perform(delete(this.baseUrl.concat("/1")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.NO_CONTENT.value()));

		this.mockMvc.perform(get(this.baseUrl.concat("/1")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
}

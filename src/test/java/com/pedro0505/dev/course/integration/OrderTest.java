package com.pedro0505.dev.course.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderTest {
	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/orders");
	}

	@Test
	public void testGetAllOrders() throws Exception {
		this.mockMvc.perform(get(this.baseUrl).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$[0].client").exists())
				.andExpect(jsonPath("$[0].item").exists())
				.andExpect(jsonPath("$[0].item[0].product").exists())
				.andExpect(jsonPath("$[0].total").value(1431.0))
				.andExpect(jsonPath("$[0].client.name").value("Maria Brown"));
	}

	@Test
	public void testGetOrderById() throws Exception {
		this.mockMvc.perform(get(this.baseUrl.concat("/2")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.client").exists())
				.andExpect(jsonPath("$.item").exists())
				.andExpect(jsonPath("$.item[0].product").exists())
				.andExpect(jsonPath("$.total").value(2500.0))
				.andExpect(jsonPath("$.client.name").value("Alex Green"));
	}
}

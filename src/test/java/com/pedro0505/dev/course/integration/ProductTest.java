package com.pedro0505.dev.course.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductTest {
	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/products");
	}

	@Test
	public void testGetAllProducts() throws Exception {
		this.mockMvc.perform(get(this.baseUrl).contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name").value("The Lord of the Rings"))
		.andExpect(jsonPath("$[0].price").value(90.5))
		.andExpect(jsonPath("$[0].categories[0].name").value("Books"));
	}
}

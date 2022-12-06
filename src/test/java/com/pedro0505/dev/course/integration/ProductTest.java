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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
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
				.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$[0].name").value("The Lord of the Rings"))
				.andExpect(jsonPath("$[0].price").value(90.5))
				.andExpect(jsonPath("$[0].categories[0].name").value("Books"))
				.andExpect(jsonPath("$[1].name").value("Smart TV"))
				.andExpect(jsonPath("$[1].price").value(2190.0))
				.andExpect(jsonPath("$[1].categories[0].name").value("Electronics"))
				.andExpect(jsonPath("$[1].categories[1].name").value("Computers"));
	}

	@Test
	public void testGetProductById() throws Exception {
		this.mockMvc.perform(get(this.baseUrl.concat("/1")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.name").value("The Lord of the Rings"))
				.andExpect(jsonPath("$.price").value(90.5))
				.andExpect(jsonPath("$.description").value("Lorem ipsum dolor sit amet, consectetur."))
				.andExpect(jsonPath("$.categories[0].name").value("Books"));
	}
}

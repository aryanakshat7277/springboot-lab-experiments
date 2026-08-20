package com.example.SpringSecurityDemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringSecurityDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testPublicEndpoint() throws Exception {
		mockMvc.perform(get("/welcome"))
				.andExpect(status().isOk())
				.andExpect(content().string("Welcome to Spring Security"));
	}

	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	void testUserEndpointWithUserRole() throws Exception {
		mockMvc.perform(get("/user"))
				.andExpect(status().isOk())
				.andExpect(content().string("Welcome User"));
	}

	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	void testAdminEndpointWithUserRoleForbidden() throws Exception {
		mockMvc.perform(get("/admin"))
				.andExpect(status().isForbidden()); // 403 Forbidden matching lab manual error detection phase!
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void testAdminEndpointWithAdminRole() throws Exception {
		mockMvc.perform(get("/admin"))
				.andExpect(status().isOk())
				.andExpect(content().string("Welcome Admin"));
	}
}

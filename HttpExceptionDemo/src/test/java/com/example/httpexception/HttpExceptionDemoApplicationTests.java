package com.example.httpexception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("HTTP Exception Handling & Status Code Test Suite")
class HttpExceptionDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test 200 OK Status Code")
    void test200Ok() throws Exception {
        mockMvc.perform(get("/api/test/200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    @DisplayName("Test 400 Bad Request Exception Handling")
    void test400BadRequest() throws Exception {
        mockMvc.perform(get("/api/test/400"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }

    @Test
    @DisplayName("Test 401 Unauthorized Exception Handling")
    void test401Unauthorized() throws Exception {
        mockMvc.perform(get("/api/test/401"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.error").value("Unauthorized Access"));
    }

    @Test
    @DisplayName("Test 403 Forbidden Exception Handling")
    void test403Forbidden() throws Exception {
        mockMvc.perform(get("/api/test/403"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status").value(403))
                .andExpect(jsonPath("$.error").value("Forbidden Resource"));
    }

    @Test
    @DisplayName("Test 404 Not Found Exception Handling")
    void test404NotFound() throws Exception {
        mockMvc.perform(get("/api/test/404"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Resource Not Found"));
    }

    @Test
    @DisplayName("Test 409 Conflict Exception Handling")
    void test409Conflict() throws Exception {
        mockMvc.perform(get("/api/test/409"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.error").value("Data Conflict"));
    }

    @Test
    @DisplayName("Test 500 Internal Server Error Exception Handling")
    void test500ServerError() throws Exception {
        mockMvc.perform(get("/api/test/500"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.error").value("Internal Server Error"));
    }
}

package com.example.bankapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BankServicesAppApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // Requirement 2: Test 1st URL (/branches)
    @Test
    @DisplayName("Test 1st URL: GET /branches returns HTML page with Pune Branches table")
    void testGetPuneBranches() throws Exception {
        mockMvc.perform(get("/branches"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("State Bank of India - Pune Branches")))
                .andExpect(content().string(containsString("Pune Main Branch")))
                .andExpect(content().string(containsString("SBIN0000454")));
    }

    // Requirement 3: Test 2nd URL (/services)
    @Test
    @DisplayName("Test 2nd URL: GET /services returns HTML page with Bank Services table")
    void testGetBankServices() throws Exception {
        mockMvc.perform(get("/services"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("State Bank of India - Services Provided")))
                .andExpect(content().string(containsString("Savings Account")))
                .andExpect(content().string(containsString("Fixed Deposit (FD)")));
    }

    // Requirement 5: Test Health Parameter (/health)
    @Test
    @DisplayName("Test Health Parameter: GET /health returns application status as UP")
    void testGetHealthStatus() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}

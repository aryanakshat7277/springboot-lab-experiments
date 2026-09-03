package com.example.bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.bank.controller.BankController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankController.class)
@DisplayName("Bank Controller Unit & Integration Tests")
class BankControllerTests {

    @Autowired
    private MockMvc mockMvc;

    // Requirement 2: Test 1st RESTful URL - Bank Name
    @Test
    @DisplayName("Test 1st RESTful URL: GET /bank/name returns Bank Name String")
    void testGetBankName() throws Exception {
        mockMvc.perform(get("/bank/name"))
                .andExpect(status().isOk())
                .andExpect(content().string("State Bank of India"));
    }

    // Requirement 3: Test 2nd RESTful URL - Bank Address
    @Test
    @DisplayName("Test 2nd RESTful URL: GET /bank/address returns Bank Address String")
    void testGetBankAddress() throws Exception {
        mockMvc.perform(get("/bank/address"))
                .andExpect(status().isOk())
                .andExpect(content().string("123 Financial District, Nariman Point, Mumbai, India - 400021"));
    }

    // Requirement 4: Test Beans Endpoint: GET /bank/beans
    @Test
    @DisplayName("Test GET /bank/beans returns Auto-Configured Beans")
    void testGetAllBeans() throws Exception {
        mockMvc.perform(get("/bank/beans"))
                .andExpect(status().isOk());
    }
}

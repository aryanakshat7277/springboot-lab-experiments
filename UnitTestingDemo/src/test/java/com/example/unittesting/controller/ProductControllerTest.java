package com.example.unittesting.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.unittesting.entity.Product;
import com.example.unittesting.service.ProductService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@DisplayName("Product Controller MockMvc Unit Tests")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Wireless Mouse", "Accessories", 29.99, 100);
    }

    @Test
    @DisplayName("Test Create Product Endpoint - POST /api/products")
    void testCreateProduct() throws Exception {
        given(productService.saveProduct(any(Product.class))).willReturn(product);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Wireless Mouse")))
                .andExpect(jsonPath("$.category", is("Accessories")));
    }

    @Test
    @DisplayName("Test Get All Products Endpoint - GET /api/products")
    void testGetAllProducts() throws Exception {
        Product product2 = new Product(2L, "Mechanical Keyboard", "Accessories", 89.99, 40);
        given(productService.getAllProducts()).willReturn(List.of(product, product2));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    @DisplayName("Test Get Product By ID Endpoint - GET /api/products/{id}")
    void testGetProductById() throws Exception {
        given(productService.getProductById(1L)).willReturn(product);

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Wireless Mouse")))
                .andExpect(jsonPath("$.price", is(29.99)));
    }

    @Test
    @DisplayName("Test Delete Product Endpoint - DELETE /api/products/{id}")
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Product deleted successfully!"));
    }
}

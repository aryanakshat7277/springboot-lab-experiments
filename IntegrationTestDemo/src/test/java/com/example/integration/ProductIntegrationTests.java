package com.example.integration;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import com.example.integration.entity.Product;
import com.example.integration.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Spring Boot TDD Integration Tests with H2 & TestRestTemplate (JavaTechie Tutorial)")
public class ProductIntegrationTests {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository h2Repository;

    @BeforeAll
    public static void init() {
        System.out.println("=== Starting JavaTechie TDD Integration Test Suite ===");
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl + ":" + port + "/products";
        h2Repository.deleteAll();
    }

    // 1. Integration Test for POST /products/addProduct
    @Test
    @DisplayName("Integration Test: Add Product via REST API and verify in H2 Database")
    public void testAddProduct() {
        Product product = new Product("headphone", 2, 600.0);
        Product response = restTemplate.postForObject(baseUrl + "/addProduct", product, Product.class);

        assertNotNull(response);
        assertEquals("headphone", response.getName());
        assertEquals(1, h2Repository.findAll().size());
    }

    // 2. Integration Test for GET /products
    @Test
    @DisplayName("Integration Test: Get All Products via REST API")
    public void testGetProducts() {
        Product product = new Product("headphone", 2, 600.0);
        h2Repository.save(product);

        List<Product> products = restTemplate.getForObject(baseUrl, List.class);

        assertNotNull(products);
        assertEquals(1, products.size());
    }

    // 3. Integration Test for GET /products/{id}
    @Test
    @DisplayName("Integration Test: Find Product By ID via REST API")
    public void testFindProductById() {
        Product product = new Product("headphone", 2, 600.0);
        Product savedProduct = h2Repository.save(product);

        Product response = restTemplate.getForObject(baseUrl + "/{id}", Product.class, savedProduct.getId());

        assertNotNull(response);
        assertEquals("headphone", response.getName());
        assertEquals(600.0, response.getPrice());
    }

    // 4. Integration Test for PUT /products/update/{id}
    @Test
    @DisplayName("Integration Test: Update Product via REST API")
    public void testUpdateProduct() {
        Product product = new Product("headphone", 2, 600.0);
        Product savedProduct = h2Repository.save(product);

        Product updatedProduct = new Product(savedProduct.getId(), "headphone-pro", 5, 800.0);
        restTemplate.put(baseUrl + "/update/{id}", updatedProduct, savedProduct.getId());

        Product fromDb = h2Repository.findById(savedProduct.getId()).orElse(null);
        assertNotNull(fromDb);
        assertEquals("headphone-pro", fromDb.getName());
        assertEquals(5, fromDb.getQuantity());
        assertEquals(800.0, fromDb.getPrice());
    }

    // 5. Integration Test for DELETE /products/delete/{id}
    @Test
    @DisplayName("Integration Test: Delete Product via REST API")
    public void testDeleteProduct() {
        Product product = new Product("headphone", 2, 600.0);
        Product savedProduct = h2Repository.save(product);

        assertEquals(1, h2Repository.findAll().size());

        restTemplate.delete(baseUrl + "/delete/{id}", savedProduct.getId());

        assertEquals(0, h2Repository.findAll().size());
    }
}

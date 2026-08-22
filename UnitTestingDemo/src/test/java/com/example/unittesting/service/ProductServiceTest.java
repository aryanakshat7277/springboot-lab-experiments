package com.example.unittesting.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.unittesting.entity.Product;
import com.example.unittesting.exception.ResourceNotFoundException;
import com.example.unittesting.repository.ProductRepository;
import com.example.unittesting.service.impl.ProductServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Product Service Unit Tests (JUnit 5 & Mockito)")
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeAll
    static void initAll() {
        System.out.println("--- Starting ProductServiceTest Suite ---");
    }

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Smartphone", "Electronics", 699.99, 25);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test executed successfully.");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("--- Completed ProductServiceTest Suite ---");
    }

    // 1. Test saveProduct() success
    @Test
    @DisplayName("Test Save Product - Success")
    void testSaveProduct() {
        given(productRepository.save(product)).willReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("Smartphone");
        verify(productRepository, times(1)).save(product);
    }

    // 2. Test getAllProducts() success
    @Test
    @DisplayName("Test Get All Products - Success")
    void testGetAllProducts() {
        Product product2 = new Product(2L, "Tablet", "Electronics", 399.99, 10);
        given(productRepository.findAll()).willReturn(List.of(product, product2));

        List<Product> productList = productService.getAllProducts();

        assertThat(productList).hasSize(2);
        verify(productRepository, times(1)).findAll();
    }

    // 3. Test getProductById() success
    @Test
    @DisplayName("Test Get Product By ID - Success")
    void testGetProductById() {
        given(productRepository.findById(1L)).willReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1L);

        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(1L);
        verify(productRepository, times(1)).findById(1L);
    }

    // 4. Test getProductById() - ResourceNotFoundException
    @Test
    @DisplayName("Test Get Product By ID - ResourceNotFoundException")
    void testGetProductById_ThrowsResourceNotFoundException() {
        given(productRepository.findById(99L)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(99L);
        });

        verify(productRepository, times(1)).findById(99L);
    }

    // 5. Test updateProduct() success
    @Test
    @DisplayName("Test Update Product - Success")
    void testUpdateProduct() {
        given(productRepository.findById(1L)).willReturn(Optional.of(product));

        Product updatedDetails = new Product("Pro Smartphone", "Electronics", 799.99, 30);
        given(productRepository.save(any(Product.class))).willReturn(product);

        Product updatedProduct = productService.updateProduct(1L, updatedDetails);

        assertThat(updatedProduct.getName()).isEqualTo("Pro Smartphone");
        assertThat(updatedProduct.getPrice()).isEqualTo(799.99);
        verify(productRepository, times(1)).save(product);
    }

    // 6. Test deleteProduct() void method success
    @Test
    @DisplayName("Test Delete Product (Void Method Mocking) - Success")
    void testDeleteProduct() {
        given(productRepository.findById(1L)).willReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(product);
    }
}

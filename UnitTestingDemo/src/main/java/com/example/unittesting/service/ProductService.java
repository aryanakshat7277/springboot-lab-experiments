package com.example.unittesting.service;

import java.util.List;
import com.example.unittesting.entity.Product;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
}

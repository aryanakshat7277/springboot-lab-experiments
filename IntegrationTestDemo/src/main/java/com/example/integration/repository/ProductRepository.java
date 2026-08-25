package com.example.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.integration.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

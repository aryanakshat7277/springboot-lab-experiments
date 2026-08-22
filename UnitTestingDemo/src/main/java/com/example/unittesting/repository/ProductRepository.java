package com.example.unittesting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.unittesting.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

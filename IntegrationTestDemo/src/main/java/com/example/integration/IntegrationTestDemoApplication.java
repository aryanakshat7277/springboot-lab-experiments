package com.example.integration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import com.example.integration.entity.Product;
import com.example.integration.repository.ProductRepository;

@SpringBootApplication
public class IntegrationTestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationTestDemoApplication.class, args);
    }

    @Bean
    @ConditionalOnBean(ProductRepository.class)
    public CommandLineRunner initData(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.save(new Product("Mobile", 10, 500.0));
                productRepository.save(new Product("Laptop", 5, 1200.0));
            }
        };
    }
}

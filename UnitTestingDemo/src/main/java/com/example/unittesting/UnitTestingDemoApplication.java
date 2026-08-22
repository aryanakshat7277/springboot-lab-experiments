package com.example.unittesting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import com.example.unittesting.entity.Product;
import com.example.unittesting.repository.ProductRepository;

@SpringBootApplication
public class UnitTestingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitTestingDemoApplication.class, args);
    }

    @Bean
    @ConditionalOnBean(ProductRepository.class)
    public CommandLineRunner initData(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.save(new Product("Gaming Laptop", "Electronics", 1299.99, 15));
                productRepository.save(new Product("Wireless Headphones", "Accessories", 199.99, 50));
            }
        };
    }
}

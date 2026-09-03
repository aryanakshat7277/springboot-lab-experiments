package com.example.bank;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BankServicesDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankServicesDemoApplication.class, args);
    }

    // 1st RESTful URL: Displays Bank Name
    @GetMapping("/name")
    public String getBankName() {
        return "State Bank of India";
    }

    // 2nd RESTful URL: Displays Bank Address
    @GetMapping("/address")
    public String getBankAddress() {
        return "Mumbai, India";
    }

    // List all default Spring Boot beans in the console
    @Bean
    public CommandLineRunner printBeans(ApplicationContext ctx) {
        return args -> {
            System.out.println("\n-------------------------------------------");
            System.out.println("  DEFAULT BEANS CREATED BY SPRING BOOT:");
            System.out.println("-------------------------------------------");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            System.out.println("-------------------------------------------\n");
        };
    }
}

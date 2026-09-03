package com.example.bank;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankServicesDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankServicesDemoApplication.class, args);
    }

    // Prints all default Spring Boot auto-configured beans to the console
    @Bean
    public CommandLineRunner printBeans(ApplicationContext ctx) {
        return args -> {
            System.out.println("==========================================================================");
            System.out.println("  LIST OF DEFAULT BEANS CREATED AUTOMATICALLY BY SPRING BOOT (" + ctx.getBeanDefinitionCount() + " beans):");
            System.out.println("==========================================================================");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            int count = 1;
            for (String beanName : beanNames) {
                System.out.println(String.format("%3d. %s", count++, beanName));
            }
            System.out.println("==========================================================================");
        };
    }
}

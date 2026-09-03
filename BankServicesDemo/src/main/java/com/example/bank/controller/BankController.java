package com.example.bank.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final ApplicationContext applicationContext;

    @Value("${bank.name:State Bank of India}")
    private String bankName;

    @Value("${bank.address:123 Financial District, Nariman Point, Mumbai, India - 400021}")
    private String bankAddress;

    public BankController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // 1st RESTful URL: Displays Bank Name as a String
    @GetMapping("/name")
    public String getBankName() {
        return bankName;
    }

    // 2nd RESTful URL: Displays Bank Address as a String
    @GetMapping("/address")
    public String getBankAddress() {
        return bankAddress;
    }

    // Additional Endpoint: Returns all Spring Boot Auto-Configured Beans
    @GetMapping("/beans")
    public List<String> getAllBeans() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        return Arrays.asList(beanNames);
    }
}

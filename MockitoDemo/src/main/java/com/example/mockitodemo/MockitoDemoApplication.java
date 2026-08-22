package com.example.mockitodemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import com.example.mockitodemo.entity.Employee;
import com.example.mockitodemo.repository.EmployeeRepository;

@SpringBootApplication
public class MockitoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockitoDemoApplication.class, args);
    }

    @Bean
    @ConditionalOnBean(EmployeeRepository.class)
    public CommandLineRunner initData(EmployeeRepository employeeRepository) {
        return args -> {
            if (employeeRepository.count() == 0) {
                employeeRepository.save(new Employee("Ramesh", "Fadatare", "ramesh@gmail.com"));
                employeeRepository.save(new Employee("Tony", "Stark", "tony@gmail.com"));
            }
        };
    }
}

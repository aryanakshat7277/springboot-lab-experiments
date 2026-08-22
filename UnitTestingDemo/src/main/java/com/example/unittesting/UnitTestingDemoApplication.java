package com.example.unittesting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import com.example.unittesting.entity.Employee;
import com.example.unittesting.repository.EmployeeRepository;

@SpringBootApplication
public class UnitTestingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitTestingDemoApplication.class, args);
    }

    @Bean
    @ConditionalOnBean(EmployeeRepository.class)
    public CommandLineRunner initData(EmployeeRepository employeeRepository) {
        return args -> {
            if (employeeRepository.count() == 0) {
                employeeRepository.save(new Employee("Alex", "Mercer", "alex.mercer@example.com"));
                employeeRepository.save(new Employee("Sophia", "Chen", "sophia.chen@example.com"));
            }
        };
    }
}

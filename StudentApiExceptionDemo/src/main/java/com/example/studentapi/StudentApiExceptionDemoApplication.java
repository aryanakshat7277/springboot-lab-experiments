package com.example.studentapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import com.example.studentapi.entity.Student;
import com.example.studentapi.repository.StudentRepository;

@SpringBootApplication
public class StudentApiExceptionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiExceptionDemoApplication.class, args);
    }

    @Bean
    @ConditionalOnBean(StudentRepository.class)
    public CommandLineRunner initData(StudentRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Student("Aryan Sharma", "aryan@example.com", "Computer Science", 21));
                repository.save(new Student("Rohan Verma", "rohan@example.com", "Information Technology", 22));
            }
        };
    }
}

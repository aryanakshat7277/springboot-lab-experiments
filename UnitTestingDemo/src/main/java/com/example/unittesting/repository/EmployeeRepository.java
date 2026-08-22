package com.example.unittesting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.unittesting.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}

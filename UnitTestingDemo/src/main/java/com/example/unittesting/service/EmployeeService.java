package com.example.unittesting.service;

import java.util.List;
import java.util.Optional;

import com.example.unittesting.entity.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee updateEmployee(Employee updatedEmployee);
    void deleteEmployee(Long id);
}

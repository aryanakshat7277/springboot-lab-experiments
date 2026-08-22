package com.example.mockitodemo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.mockitodemo.entity.Employee;
import com.example.mockitodemo.exception.ResourceNotFoundException;
import com.example.mockitodemo.repository.EmployeeRepository;
import com.example.mockitodemo.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito Advanced Unit Tests for Service Layer (Part 2)")
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = new Employee(1L, "Ramesh", "Fadatare", "ramesh@gmail.com");
    }

    // Mockito test for saveEmployee method
    @DisplayName("Mockito test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        // given - precondition or setup
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);

        // when - action or testing
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isEqualTo(1L);
        verify(employeeRepository, times(1)).save(employee);
    }

    // Mockito test for saveEmployee method which throws Exception
    @DisplayName("Mockito test for saveEmployee method which throws Exception")
    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException() {
        // given - precondition or setup
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        // when - action or testing
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.saveEmployee(employee);
        });

        // then - verify repository save was never called
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    // Mockito test for getAllEmployees method
    @DisplayName("Mockito test for getAllEmployees method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() {
        // given - precondition or setup
        Employee employee1 = new Employee(2L, "Tony", "Stark", "tony@gmail.com");
        given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));

        // when - action or testing
        List<Employee> employeeList = employeeService.getAllEmployees();

        // then - verify output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
        verify(employeeRepository, times(1)).findAll();
    }

    // Mockito test for getEmployeeById method
    @DisplayName("Mockito test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() {
        // given
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        // when
        Employee savedEmployee = employeeService.getEmployeeById(1L).get();

        // then
        assertThat(savedEmployee).isNotNull();
        verify(employeeRepository, times(1)).findById(1L);
    }

    // Mockito test for updateEmployee method
    @DisplayName("Mockito test for updateEmployee method")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        // given - precondition or setup
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmail("ram@gmail.com");
        employee.setFirstName("Ram");

        // when - action or testing
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        // then - verify output
        assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ram");
        verify(employeeRepository, times(1)).save(employee);
    }

    // Mockito test for deleteEmployee method (Mocking Void Method)
    @DisplayName("Mockito test for deleteEmployee method (Mocking Void Method)")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenNothing() {
        // given - precondition or setup
        long employeeId = 1L;
        willDoNothing().given(employeeRepository).deleteById(employeeId);

        // when - action or testing
        employeeService.deleteEmployee(employeeId);

        // then - verify void method invocation
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}

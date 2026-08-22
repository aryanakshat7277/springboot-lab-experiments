package com.example.junitdemo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.junitdemo.entity.Employee;

@DataJpaTest
@DisplayName("JUnit 5 DataJpaTest Suite for EmployeeRepository (Part 1)")
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeAll
    static void initAll() {
        System.out.println("=== Starting JUnit 5 DataJpaTest Suite ===");
    }

    @BeforeEach
    public void setup() {
        employee = new Employee("Ramesh", "Fadatare", "ramesh@gmail.com");
    }

    @AfterEach
    public void tearDown() {
        employeeRepository.deleteAll();
        System.out.println("Test executed and clean database restored.");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("=== Completed JUnit 5 DataJpaTest Suite ===");
    }

    // JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        // when - action or the testing that we are going to do
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    // JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {
        // given - precondition or setup
        Employee employee1 = new Employee("John", "Cena", "john@gmail.com");
        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when - action or testing
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    // JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or testing
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
        assertEquals("Ramesh", employeeDB.getFirstName());
    }

    // JUnit test for get employee by email operation
    @DisplayName("JUnit test for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or testing
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
        assertEquals("ramesh@gmail.com", employeeDB.getEmail());
    }

    // JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or testing
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("ramesh_updated@gmail.com");
        savedEmployee.setFirstName("Ram");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        assertEquals("ramesh_updated@gmail.com", updatedEmployee.getEmail());
        assertEquals("Ram", updatedEmployee.getFirstName());
    }

    // JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or testing
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(employeeOptional).isEmpty();
        assertFalse(employeeOptional.isPresent());
    }

    // JUnit test for custom query using JPQL
    @DisplayName("JUnit test for custom query using JPQL")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or testing
        Employee savedEmployee = employeeRepository.findByJPQL("Ramesh", "Fadatare");

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertEquals("Ramesh", savedEmployee.getFirstName());
    }

    // JUnit test for custom query using Native SQL
    @DisplayName("JUnit test for custom query using Native SQL")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or testing
        Employee savedEmployee = employeeRepository.findByNativeSQL("Ramesh", "Fadatare");

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertEquals("Ramesh", savedEmployee.getFirstName());
    }
}

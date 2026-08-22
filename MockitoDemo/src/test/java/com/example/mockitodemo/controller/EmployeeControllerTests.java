package com.example.mockitodemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.mockitodemo.entity.Employee;
import com.example.mockitodemo.service.EmployeeService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@DisplayName("Mockito WebMvcTest Suite for EmployeeController (Part 2)")
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = new Employee(1L, "Ramesh", "Fadatare", "ramesh@gmail.com");
    }

    // Mockito test for createEmployee REST API
    @Test
    @DisplayName("Mockito test for createEmployee REST API")
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {
        // given - precondition or setup
        given(employeeService.saveEmployee(any(Employee.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when - action
        ResultActions response = mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // then - verify response
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    // Mockito test for Get All Employees REST API
    @Test
    @DisplayName("Mockito test for Get All Employees REST API")
    public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception {
        // given - precondition or setup
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(employee);
        listOfEmployees.add(new Employee(2L, "Tony", "Stark", "tony@gmail.com"));
        given(employeeService.getAllEmployees()).willReturn(listOfEmployees);

        // when - action
        ResultActions response = mockMvc.perform(get("/api/employees"));

        // then - verify response
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(listOfEmployees.size())));
    }

    // Mockito test for GET employee by id REST API (positive scenario)
    @Test
    @DisplayName("Mockito test for GET employee by id REST API")
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
        // given - precondition or setup
        long employeeId = 1L;
        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));

        // when - action
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employeeId));

        // then - verify response
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    // Mockito test for GET employee by id REST API (negative scenario)
    @Test
    @DisplayName("Mockito test for GET employee by id REST API (negative scenario)")
    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception {
        // given - precondition or setup
        long employeeId = 1L;
        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());

        // when - action
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employeeId));

        // then - verify response
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // Mockito test for updateEmployee REST API
    @Test
    @DisplayName("Mockito test for updateEmployee REST API")
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdateEmployeeObject() throws Exception {
        // given - precondition or setup
        long employeeId = 1L;
        Employee updatedEmployee = new Employee("Ram", "Jadhav", "ram@gmail.com");
        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));
        given(employeeService.updateEmployee(any(Employee.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when - action
        ResultActions response = mockMvc.perform(put("/api/employees/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        // then - verify response
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedEmployee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedEmployee.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedEmployee.getEmail())));
    }

    // Mockito test for deleteEmployee REST API
    @Test
    @DisplayName("Mockito test for deleteEmployee REST API")
    public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception {
        // given - precondition or setup
        long employeeId = 1L;
        willDoNothing().given(employeeService).deleteEmployee(employeeId);

        // when - action
        ResultActions response = mockMvc.perform(delete("/api/employees/{id}", employeeId));

        // then - verify response
        response.andExpect(status().isOk())
                .andDo(print());
    }
}

package com.example.studentapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.studentapi.entity.Student;
import com.example.studentapi.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Student API Global Exception Handling & Validation Tests")
class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository repository;

    // 1. Test Valid Student Creation (201 CREATED)
    @Test
    @DisplayName("Test 201 CREATED for valid student payload")
    void testCreateStudentSuccess() throws Exception {
        Student student = new Student("Neha Gupta", "neha@example.com", "Biotechnology", 20);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Neha Gupta"))
                .andExpect(jsonPath("$.email").value("neha@example.com"));
    }

    // 2. Test Input Validation Error (400 BAD REQUEST)
    @Test
    @DisplayName("Test 400 BAD REQUEST for invalid student payload")
    void testCreateStudentValidationError() throws Exception {
        Student invalidStudent = new Student("", "invalid-email", "", 15);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidStudent)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Invalid Request Payload"))
                .andExpect(jsonPath("$.validationErrors.name").exists())
                .andExpect(jsonPath("$.validationErrors.email").exists());
    }

    // 3. Test Custom DuplicateStudentException (409 CONFLICT)
    @Test
    @DisplayName("Test 409 CONFLICT for duplicate student email")
    void testCreateDuplicateStudentConflict() throws Exception {
        // Save an existing student first
        repository.save(new Student("Existing Student", "existing@example.com", "CS", 22));

        Student duplicateStudent = new Student("New Student", "existing@example.com", "CS", 22);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(duplicateStudent)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.error").value("Duplicate Student Conflict"));
    }

    // 4. Test Custom StudentNotFoundException (404 NOT FOUND)
    @Test
    @DisplayName("Test 404 NOT FOUND for non-existing student ID")
    void testGetStudentNotFound() throws Exception {
        mockMvc.perform(get("/api/students/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Student Not Found"))
                .andExpect(jsonPath("$.message").value("Student not found with ID: 9999"));
    }
}

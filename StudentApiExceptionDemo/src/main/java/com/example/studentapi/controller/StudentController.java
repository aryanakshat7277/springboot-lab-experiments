package com.example.studentapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.studentapi.entity.Student;
import com.example.studentapi.exception.DuplicateStudentException;
import com.example.studentapi.exception.StudentNotFoundException;
import com.example.studentapi.repository.StudentRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // 1. Create Student (validates payload & checks for duplicate email)
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        if (repository.existsByEmail(student.getEmail())) {
            throw new DuplicateStudentException("Student already exists with email: " + student.getEmail());
        }
        Student savedStudent = repository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // 2. Get All Students
    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // 3. Get Student By ID (throws StudentNotFoundException if missing)
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
    }

    // 4. Update Student By ID
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student studentDetails) {
        Student existingStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));

        // Check if new email is used by another student
        if (!existingStudent.getEmail().equalsIgnoreCase(studentDetails.getEmail()) &&
                repository.existsByEmail(studentDetails.getEmail())) {
            throw new DuplicateStudentException("Email already in use: " + studentDetails.getEmail());
        }

        existingStudent.setName(studentDetails.getName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setCourse(studentDetails.getCourse());
        existingStudent.setAge(studentDetails.getAge());

        return repository.save(existingStudent);
    }

    // 5. Delete Student By ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Student existingStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));

        repository.delete(existingStudent);
        return ResponseEntity.ok("Student with ID " + id + " deleted successfully!");
    }
}

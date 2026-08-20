package com.example.StudentCRUD.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.StudentCRUD.entity.Student;
import com.example.StudentCRUD.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // Create Student (POST /api/students)
    @PostMapping
    public Student save(@RequestBody Student student) {
        return repository.save(student);
    }

    // Get All Students (GET /api/students)
    @GetMapping
    public List<Student> findAll() {
        return repository.findAll();
    }

    // Get Student By ID (GET /api/students/{id})
    @GetMapping("/{id}")
    public Student findById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // Update Student (PUT /api/students/{id})
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        Student existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(student.getName());
            existing.setEmail(student.getEmail());
            existing.setAge(student.getAge());
            return repository.save(existing);
        }
        return null;
    }

    // Delete Student (DELETE /api/students/{id})
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "Student Deleted Successfully!";
    }
}

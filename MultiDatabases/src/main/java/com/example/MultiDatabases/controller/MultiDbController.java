package com.example.MultiDatabases.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.*;

import com.example.MultiDatabases.db1.entity.Book;
import com.example.MultiDatabases.db1.repository.BookRepository;
import com.example.MultiDatabases.db2.entity.Student;
import com.example.MultiDatabases.db2.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class MultiDbController implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public MultiDbController(BookRepository bookRepository, StudentRepository studentRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Seed DB1 (Books Database)
        if (bookRepository.count() == 0) {
            bookRepository.save(new Book("Spring Security 6 in Action", "Think Constructive", 49.99));
            bookRepository.save(new Book("Spring Boot 3.4 Deep Dive", "Think Constructive", 39.99));
        }

        // Seed DB2 (Students Database)
        if (studentRepository.count() == 0) {
            studentRepository.save(new Student("Alex Mercer", "alex@example.com", "Computer Science"));
            studentRepository.save(new Student("Sophia Chen", "sophia@example.com", "Information Technology"));
        }
    }

    // --- DB1 Endpoints (Book Database) ---
    @GetMapping("/db1/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/db1/books")
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // --- DB2 Endpoints (Student Database) ---
    @GetMapping("/db2/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/db2/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // --- Combined Dual Database Endpoint ---
    @GetMapping("/all")
    public Map<String, Object> getAllData() {
        Map<String, Object> data = new HashMap<>();
        data.put("DB1_Books", bookRepository.findAll());
        data.put("DB2_Students", studentRepository.findAll());
        return data;
    }
}

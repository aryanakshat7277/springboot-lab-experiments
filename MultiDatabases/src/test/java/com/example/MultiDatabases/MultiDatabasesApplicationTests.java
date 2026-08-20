package com.example.MultiDatabases;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.MultiDatabases.db1.repository.BookRepository;
import com.example.MultiDatabases.db2.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MultiDatabasesApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void contextLoadsAndDatabasesInitialized() {
        assertTrue(bookRepository.count() > 0, "DB1 (Book Database) should contain initial books");
        assertTrue(studentRepository.count() > 0, "DB2 (Student Database) should contain initial students");
    }

}
